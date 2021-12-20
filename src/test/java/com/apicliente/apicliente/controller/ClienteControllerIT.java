package com.apicliente.apicliente.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.domain.EnderecoVO;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;;

import static javax.ws.rs.core.Response.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("/application-test.properties")
@ActiveProfiles("test")
class ClienteControllerIT {


    private static final String URI_CLIENTE = "/v1/clientes";

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup(){

        this.mockMvc = MockMvcBuilders.webAppContextSetup((this.wac)).build();
    }

    @Test
    @DisplayName("Cria cliente com retorno criado")
    public void test_criaCliente_retornoCriado() throws Exception {

        ClienteVO cliente = new ClienteVO();

        cliente.setDataCriacao(LocalDate.now());
        cliente.setNome("Projeto");
        cliente.setDocPrincipal("09600015270");
        cliente.setTelefone("81996002547");
        cliente.setEmail("Projeto@teste.com");
        cliente.setDataNascimento(LocalDate.now());

        EnderecoVO endereco = new EnderecoVO();
        endereco.setRua("Rua A");
        endereco.setNumero("2");
        endereco.setComplemento("Casa");
        endereco.setBairro("Boa Viagem");
        endereco.setCidade("Recife");
        endereco.setEstado("PE");
        endereco.setCep("55400000");

        cliente.add(endereco);
        this.mockMvc.perform(MockMvcRequestBuilders.post(URI_CLIENTE.concat("/cadastrocliente"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cliente))
        ).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Cria cliente com parametro nulo espera retorno de Bad Request")
    public void test_criaCliente_passandoParametroNulo() throws Exception {

        ClienteVO cliente = new ClienteVO();

        this.mockMvc.perform(MockMvcRequestBuilders.post(URI_CLIENTE.concat("/cadastrocliente"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cliente))
                ).andDo(print())
                .andExpect(status().isBadRequest());
    }


}