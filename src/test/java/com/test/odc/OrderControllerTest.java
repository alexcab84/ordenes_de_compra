package com.test.odc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.test.odc.services.OrderService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Test
    public void testCrearOrden() throws Exception {

        mockMvc.perform(post("/ordenes/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idUsuario\":\"usuario123\", \"items\":[{\"id_producto\":\"producto123\", \"cantidad\":25, \"precio_unitario\":1000.00}]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUsuario").value("usuario123"))
                .andExpect(jsonPath("$.items[0].id_producto").value("producto123"))
                .andExpect(jsonPath("$.items[0].cantidad").value(25))
                .andExpect(jsonPath("$.items[0].precio_unitario").value(1000.00));
    }
}
