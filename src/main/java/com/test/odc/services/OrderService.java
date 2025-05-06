package com.test.odc.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.odc.entities.Order;
import com.test.odc.mongo.OrderRepository;

@Service
public class OrderService {
	@Autowired
    private OrderRepository ordenRepository;

	@Autowired
    private KafkaProducer<String, Order> kafkaProducer;

    public Order crearOrden(Order orden) {
        if (orden.getItems() == null || orden.getItems().isEmpty()) {
            throw new IllegalArgumentException("La orden debe tener al menos un ítem.");
        }

        BigDecimal total = orden.getItems().stream()
                .map(i -> i.getPrecio_unitario().multiply(BigDecimal.valueOf(i.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orden.setTotal(total);
        orden.setFecha_creacion(Date.from(Instant.now()));
        orden.setId(new ObjectId().toHexString());

        Order orden_guardada = ordenRepository.save(orden);
        
        ProducerRecord<String, Order> record = new ProducerRecord<>("ordenes_creadas", "key", orden);
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                System.err.println("Error al enviar el mensaje: " + exception.getMessage());
            } else {
                System.out.println("Mensaje enviado correctamente a " + metadata.topic() + " partición " + metadata.partition());
            }
        });
        
        System.out.println("Orden guardada: " + orden_guardada.getId());

        return orden_guardada;
    }
    
    public Page<Order> listarOrdenes(int page_number, int tamano, String idUsuario) {
        Pageable pageable = PageRequest.of(page_number, tamano);

        if (idUsuario != null && !idUsuario.isEmpty()) {
            return ordenRepository.findByIdUsuario(idUsuario, pageable);
        } else {
            return ordenRepository.findAll(pageable);
        }
    }
}
