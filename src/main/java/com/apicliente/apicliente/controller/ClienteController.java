package com.apicliente.apicliente.controller;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.service.AtualizaClienteService;
import com.apicliente.apicliente.service.BuscaClienteService;
import com.apicliente.apicliente.service.CadastroClienteService;
import com.apicliente.apicliente.service.DeletaClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.Optional;

;


@RestController
@RequestMapping("/v1/clientes")
//@Tag(name = "Cliente")
public class ClienteController {

    private BuscaClienteService buscaClienteService;

    private DeletaClienteService deletaClienteService;

    private CadastroClienteService cadastroClienteService;

    private AtualizaClienteService atualizaClienteService;

    public ClienteController(BuscaClienteService buscaClienteService, CadastroClienteService cadastroClienteService,
                             AtualizaClienteService atualizaClienteService, DeletaClienteService deletaClienteService) {
        super();
        this.buscaClienteService = buscaClienteService;
        this.cadastroClienteService = cadastroClienteService;
        this.atualizaClienteService = atualizaClienteService;
        this.deletaClienteService = deletaClienteService;
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON }, value = "/cadastrocliente")
    //@Operation(description = "Cadastra Cliente")
    public ResponseEntity<ClienteVO> cadastroCliente(@Valid @RequestBody ClienteVO clienteVO) {

        ClienteVO clienteCreated = cadastroClienteService.cadastrarCliente(clienteVO);

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
                .buildAndExpand(clienteCreated.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteCreated);
    }

    @GetMapping(value = "/buscacliente/{id}", produces = { MediaType.APPLICATION_JSON })
    //@Operation(description = "Consulta cliente por id")
    public ResponseEntity<ClienteVO> buscaCliente(@PathVariable("id") Long id) {

        ClienteVO clienteEncontrado = buscaClienteService.porId(id);

        return ResponseEntity.ok(clienteEncontrado);
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON }, value = "/atualizacliente/{id}")
    //@Operation(description = "Atualiza Cliente")
    public ResponseEntity<?> atualizaCliente(@Valid @PathVariable Long id, @RequestBody ClienteVO clienteVO) {

        Optional<ClienteVO> clienteAtual = Optional.ofNullable(buscaClienteService.porId(id));

        if(clienteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            BeanUtils.copyProperties(clienteVO, clienteAtual.get(), "id", "dataCriacao");

            ClienteVO clienteSalvo = atualizaClienteService.atualizarCliente(clienteAtual.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (BadRequestException e ) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @DeleteMapping("/deletacliente/{id}")
    //@Operation(description = "Deleta Cliente")
    public ResponseEntity<?> deletaCliente(@Valid @PathVariable Long id) {

        Optional<ClienteVO> cliente = Optional.ofNullable(buscaClienteService.porId(id));

        if(cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            this.deletaClienteService.deletar(cliente.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (BadRequestException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
