package com.apicliente.apicliente.service;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.repository.ClienteRepository;
import com.apicliente.apicliente.repository.entity.ClienteEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuscaClienteServiceTest {

    private BuscaClienteService service;

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

        service = new BuscaClienteService(validator, clienteRepository);
    }

    @Test
    @DisplayName("Testa quando não encontra cliente por ID.")
    void testa_quandoNaoEncontraClientePorID_LancaExcecao() {
        assertNotNull(service);

        var assertThrows = assertThrows(NotFoundException.class, () ->
                service.porId(1L));

        then(clienteRepository).should(times(1)).findById(anyLong());
        assertEquals(assertThrows.getMessage(), "Não encontrado cliente com id = 1");
    }

    @Test
    @DisplayName("Testa quando encontra cliente por ID.")
    void testa_quandoEncontraClientePorId_retornaCliente() {
        assertNotNull(service);

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(3L);

        when(clienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(clienteEntity));

        ClienteVO clienteRetornado = service.porId(3L);

        then(clienteRepository).should(times(1)).findById(anyLong());

        assertNotNull(clienteRetornado);
        assertEquals(3L, clienteRetornado.getId());

    }


}