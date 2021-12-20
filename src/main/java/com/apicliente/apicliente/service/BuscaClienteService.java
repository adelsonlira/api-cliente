package com.apicliente.apicliente.service;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.factory.ClienteFactory;
import com.apicliente.apicliente.repository.ClienteRepository;
import com.apicliente.apicliente.repository.entity.ClienteEntity;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

@Service
public class BuscaClienteService {

    private Validator validator;

    private ClienteRepository clienteRepository;

    public BuscaClienteService(Validator v, ClienteRepository repository) {
        this.validator = v;
        this.clienteRepository = repository;
    }

    public ClienteVO porId(long id) {
        ClienteEntity clienteEntity = this.clienteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Não encontrado cliente com id = "+ id));

        return new ClienteFactory(clienteEntity).toVO();
    }
}
