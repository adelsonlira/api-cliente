package com.apicliente.apicliente.service;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.factory.ClienteFactory;
import com.apicliente.apicliente.repository.ClienteRepository;
import com.apicliente.apicliente.repository.entity.ClienteEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import java.time.LocalDate;
import java.util.Set;

@Service
public class CadastroClienteService {

    private Validator validator;

    private RabbitTemplate rabbitTemplate;

    private ClienteRepository clienteRepository;


    public CadastroClienteService(Validator v, ClienteRepository repository, RabbitTemplate pRabbitTemplate) {
        this.validator = v; this.clienteRepository = repository; this.rabbitTemplate = pRabbitTemplate;}

    @Transactional
    public ClienteVO cadastrarCliente(@NotNull(message = "Cliente não pode ser null") ClienteVO clienteVO) {
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

        this.rabbitTemplate.convertAndSend("cria-cupom-novo-cliente","cupom.criado", clienteVO);

        return clienteVO;
    }
}
