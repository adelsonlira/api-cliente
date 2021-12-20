package com.apicliente.apicliente.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteVO {

    private long id;

    @NotNull(message = "Campo nome não pode ser nulo e nem vazio")
    @Size(min=3, max=100, message = "Campo nome deve ter "
            + "no mínimo 3 carecteres, máximo 100 carecteres")
    private String nome;

    @NotNull(message = "Campo docPrincipal não pode ser nulo e nem vazio")
    @Pattern(regexp = "[0-9]+", message = "Apenas numeros")
    private String docPrincipal;

    @NotNull(message = "Campo telefone não pode ser nulo e nem vazio")
    @Size(min = 10, max = 12)
    @Pattern(regexp = "[0-9]+", message = "Apenas numeros")
    private String telefone;

    @Size(min = 10, max = 12)
    @Pattern(regexp = "[0-9]+", message = "Apenas numeros")
    private String celular;

    @NotNull(message = "Campo e-mail não pode ser nulo e nem vazio")
    @Email(message = "Campo e-mail inválido")
    private String email;

    // Cliente ter mais de 18 anos
    @NotNull(message = "Campo DataNascimento não pode ser nulo")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Campo DataCriacao não pode ser nulo")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataCriacao;

    @Valid
    private List<EnderecoVO> enderecos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocPrincipal() {
        return docPrincipal;
    }

    public void setDocPrincipal(String docPrincipal) {
        this.docPrincipal = docPrincipal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<EnderecoVO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoVO> enderecos) {
        this.enderecos = enderecos;
    }

    public void add(EnderecoVO endereco) {
        List<EnderecoVO> enderecoLocal =
                Optional.ofNullable(this.getEnderecos()).orElseGet(()->new ArrayList());
        enderecoLocal.add(endereco);

        this.enderecos = enderecoLocal;
    }
}
