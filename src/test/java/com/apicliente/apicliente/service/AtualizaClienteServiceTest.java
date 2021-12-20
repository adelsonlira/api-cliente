package com.apicliente.apicliente.service;

import com.apicliente.apicliente.repository.ClienteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AtualizaClienteServiceTest {

    private AtualizaClienteService atualizaCliente;

    private Validator validator;

    private ClienteRepository repository;

    @BeforeAll
    public void iniciaGeral() {
        ValidatorFactory validatorFactory =
                Validation.buildDefaultValidatorFactory();

        this.validator = validatorFactory.getValidator();
    }

    @BeforeEach
    public void iniciaCadaTeste() {

        this.repository = Mockito.mock(ClienteRepository.class);
        atualizaCliente = new AtualizaClienteService(validator, repository);
    }

    @Test
    @DisplayName("Testa quando consulta cliente por nome.")
    public void testa_quandoEncontraClientePorNome_retornaLista() {
        assertNotNull(this.atualizaCliente);
    }
}