package com.apicliente.apicliente.repository.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Entity
@Table(name = "TB_CLIENTE")
public class ClienteEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME_CLIENTE")
    @NotNull(message = "Campo Nome não pode ser nulo")
    private String nome;

    @Column(name = "DOC_PRINCIPAL")
    @NotNull(message = "Campo docPrincipal não pode ser nulo e nem vazio")
    private String docPrincipal;

    @Column(name = "TELEFONE")
    @NotNull(message = "Campo telefone não pode ser nulo e nem vazio")
    private String telefone;

    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "EMAIL")
    @NotNull(message = "Campo e-mail não pode ser nulo e nem vazio")
    private String email;

    @Column(name = "DATA_NASCIMENTO")
    @NotNull(message = "Campo DataNascimento não pode ser nulo")
    private LocalDate dataNascimento;

    @Column(name = "DATA_CRIACAO")
    @NotNull(message = "Campo DataCriacao não pode ser nulo")
    private LocalDate dataCriacao;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<EnderecosEntity> enderecos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<EnderecosEntity> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecosEntity> enderecos) {
        this.enderecos = enderecos;
    }

    public void add(EnderecosEntity endereco) {
        List<EnderecosEntity> enderecosLocal =
                Optional.ofNullable(this.getEnderecos()).orElseGet(() -> new ArrayList());
        enderecosLocal.add(endereco);

        this.enderecos = enderecosLocal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
