package com.apicliente.apicliente.service;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.factory.ClienteFactory;
import com.apicliente.apicliente.repository.ClienteRepository;
import com.apicliente.apicliente.repository.entity.ClienteEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

@Service
public class DeletaClienteService {

    private Validator validator;

    private ClienteRepository clienteRepository;


    public DeletaClienteService(Validator pValidator,
                                ClienteRepository pclienteRepository) {
        this.validator = pValidator;
        this.clienteRepository = pclienteRepository;
    }

    @Transactional
    public ClienteVO deletar(ClienteVO clienteVO) {
        ClienteEntity clienteEntity = new ClienteFactory(clienteVO).toEntity();
        this.clienteRepository.delete(clienteEntity);

        return clienteVO;
    }
}
