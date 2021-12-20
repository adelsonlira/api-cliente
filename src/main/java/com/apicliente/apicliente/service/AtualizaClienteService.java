package com.apicliente.apicliente.service;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.factory.ClienteFactory;
import com.apicliente.apicliente.repository.ClienteRepository;
import com.apicliente.apicliente.repository.entity.ClienteEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import java.time.LocalDate;
import java.util.Set;


@Service
public class AtualizaClienteService {

    private Validator validator;

    private ClienteRepository clienteRepository;

    public AtualizaClienteService(Validator v, ClienteRepository repository) {
        this.validator = v;
        this.clienteRepository = repository;
    }

    @Transactional
    public ClienteVO atualizarCliente(@NotNull(message = "Cliente não pode ser null") ClienteVO clienteVO) {

        Set<ConstraintViolation<ClienteVO>>
                validateMessage = this.validator.validate(clienteVO);

        if (!validateMessage.isEmpty()) {
            throw new ConstraintViolationException("Cliente inválido", validateMessage);
        }

        if (!clienteVO.getDataCriacao().isEqual(LocalDate.now())) {
            throw new BadRequestException("A data de criação deve ser atual");
        }

        ClienteEntity clienteEntity = new ClienteFactory(clienteVO).toEntity();
        clienteEntity = clienteRepository.save(clienteEntity);
        clienteVO.setId(clienteEntity.getId());

        return clienteVO;
    }

}
