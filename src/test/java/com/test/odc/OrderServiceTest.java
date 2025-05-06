package com.test.odc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.odc.entities.Item;
import com.test.odc.entities.Order;
import com.test.odc.mongo.OrderRepository;
import com.test.odc.services.OrderService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository ordenRepository;

    @Mock
    private KafkaProducer<String, Order> kafkaProducer;

    @InjectMocks
    private OrderService orderService;

    private Item item;

    @BeforeEach
    public void inicio() {
        item = mock(Item.class);
        when(item.getPrecio_unitario()).thenReturn(new BigDecimal("1000.0"));
        when(item.getCantidad()).thenReturn(5);
    }

    @SuppressWarnings("unchecked")
	@Test
    public void testCrearOrden() {
        Order order = new Order("usuario123", Arrays.asList(item));

        when(ordenRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order resultado = orderService.crearOrden(order);

        assertNotNull(resultado.getId());
        assertEquals(new BigDecimal("5000.0"), resultado.getTotal());
        assertNotNull(resultado.getFecha_creacion());

        verify(ordenRepository, times(1)).save(any(Order.class));
        verify(kafkaProducer, times(1)).send(any(ProducerRecord.class), any());
    }
}

