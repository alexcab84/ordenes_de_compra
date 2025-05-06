package com.test.odc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.odc.entities.Order;
import com.test.odc.services.OrderService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/ordenes")
public class OrderController {
    @Autowired
    private OrderService ordenService;

    @PostMapping("/crear")
    public ResponseEntity<Order> crearOrden(@RequestBody Order order) {
        try {
            Order creado = ordenService.crearOrden(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<Order>> listarOrdenes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String idUsuario) {
        try {
            Page<Order> orders = ordenService.listarOrdenes(page, size, idUsuario);
            return ResponseEntity.ok(orders); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
