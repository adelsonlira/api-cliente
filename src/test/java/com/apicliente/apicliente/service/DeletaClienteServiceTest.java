package com.apicliente.apicliente.service;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.domain.EnderecoVO;
import com.apicliente.apicliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeletaClienteServiceTest {

    private DeletaClienteService deletaCliente;

    private Validator validator;

    private ClienteRepository clienteRepository;

    @BeforeAll
    public void iniciaGeral() {
        ValidatorFactory validatorFactory =
                Validation.buildDefaultValidatorFactory();

        this.validator = validatorFactory.getValidator();
    }

    @BeforeEach
    public void iniciaCadaTeste() {

        this.clienteRepository = Mockito.mock(ClienteRepository.class);

        deletaCliente = new DeletaClienteService(this.validator,
                this.clienteRepository);
    }


    @Test
    public void testa_quandoClienteNULL_LancarExcecao() {
        assertNotNull(deletaCliente);

        ClienteVO clienteVO = null;

        var assertThrows = assertThrows(NullPointerException.class, ()-> deletaCliente.deletar(clienteVO));

        assertNotNull(assertThrows);
    }

    @Test
    public void testa_quandoClienteEh_deletadoComSucesso() {

        assertNotNull(deletaCliente);

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

        deletaCliente.deletar(cliente);
        then(clienteRepository).should(times(1)).delete(any());
    }



}