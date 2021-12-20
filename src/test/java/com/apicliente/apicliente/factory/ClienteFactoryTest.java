package com.apicliente.apicliente.factory;

import com.apicliente.apicliente.domain.ClienteVO;
import com.apicliente.apicliente.domain.EnderecoVO;
import com.apicliente.apicliente.repository.entity.ClienteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClienteFactoryTest {

    @Test
    @DisplayName("Testa o factory Cliente de VO para entity")
    public void test_quandoCriarEntityPeloVO_transformaParaEntity() {

        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setNome("Projeto");
        clienteVO.setDocPrincipal("09602400012");
        clienteVO.setTelefone("8199669966");
        clienteVO.setCelular("8199669966");
        clienteVO.setEmail("projeto@teste.com");
        clienteVO.setDataNascimento(LocalDate.now());
        clienteVO.setDataCriacao(LocalDate.now());
        clienteVO.setId(2l);

        EnderecoVO enderecoVO = new EnderecoVO();
        enderecoVO.setRua("Rua A");
        enderecoVO.setNumero("2");
        enderecoVO.setComplemento("Casa");
        enderecoVO.setBairro("Boa Viagem");
        enderecoVO.setCidade("Recife");
        enderecoVO.setEstado("PE");

        clienteVO.add(enderecoVO);

        ClienteEntity clienteEntitycriada = new ClienteFactory(clienteVO).toEntity();

        assertNotNull(clienteEntitycriada);

        assertNotNull(clienteEntitycriada.getNome());
        assertEquals(clienteVO.getNome(), clienteEntitycriada.getNome());

        assertNotNull(clienteEntitycriada.getTelefone());
        assertEquals(clienteVO.getTelefone(), clienteEntitycriada.getTelefone());

        assertNotNull(clienteEntitycriada.getCelular());
        assertEquals(clienteVO.getCelular(), clienteEntitycriada.getCelular());

        assertNotNull(clienteEntitycriada.getEmail());
        assertEquals(clienteVO.getEmail(), clienteEntitycriada.getEmail());

        assertNotNull(clienteEntitycriada.getDataNascimento());
        assertEquals(clienteVO.getDataNascimento(), clienteEntitycriada.getDataNascimento());

        assertNotNull(clienteEntitycriada.getDataCriacao());
        assertEquals(clienteVO.getDataCriacao(), clienteEntitycriada.getDataCriacao());


        assertNotNull(clienteEntitycriada.getEnderecos());
        assertEquals(clienteVO.getEnderecos().size(), clienteEntitycriada.getEnderecos().size());

        assertEquals(clienteVO.getEnderecos().size(), clienteEntitycriada.getEnderecos().size());
        assertEquals(clienteVO.getEnderecos().get(0).getRua(),
                clienteEntitycriada.getEnderecos().get(0).getRua());

        assertEquals(clienteVO.getEnderecos().get(0).getNumero(),
                clienteEntitycriada.getEnderecos().get(0).getNumero());

        assertEquals(clienteVO.getEnderecos().get(0).getComplemento(),
                clienteEntitycriada.getEnderecos().get(0).getComplemento());

        assertEquals(clienteVO.getEnderecos().get(0).getCidade(),
                clienteEntitycriada.getEnderecos().get(0).getCidade());

        assertEquals(clienteVO.getEnderecos().get(0).getEstado(),
                clienteEntitycriada.getEnderecos().get(0).getEstado());

        assertEquals(clienteVO.getEnderecos().get(0).getCep(),
                clienteEntitycriada.getEnderecos().get(0).getCep());


        ClienteVO voClienteCriado = new ClienteFactory(clienteEntitycriada).toVO();

        assertNotNull(voClienteCriado);
        assertNotNull(voClienteCriado.getNome());
        assertEquals(clienteVO.getNome(), voClienteCriado.getNome());

        assertNotNull(voClienteCriado.getDocPrincipal());
        assertEquals(clienteVO.getDocPrincipal(), voClienteCriado.getDocPrincipal());

        assertNotNull(voClienteCriado.getTelefone());
        assertEquals(clienteVO.getTelefone(), voClienteCriado.getTelefone());

        assertNotNull(voClienteCriado.getCelular());
        assertEquals(clienteVO.getCelular(), voClienteCriado.getCelular());

        assertNotNull(voClienteCriado.getEmail());
        assertEquals(clienteVO.getEmail(), voClienteCriado.getEmail());

        assertNotNull(voClienteCriado.getDataNascimento());
        assertEquals(clienteVO.getDataNascimento(), voClienteCriado.getDataNascimento());

        assertNotNull(voClienteCriado.getEnderecos());
        assertEquals(clienteVO.getEnderecos().size(), voClienteCriado.getEnderecos().size());

        assertEquals(clienteVO.getEnderecos().get(0).getRua(),
                voClienteCriado.getEnderecos().get(0).getRua());

        assertEquals(clienteVO.getEnderecos().get(0).getNumero(),
                voClienteCriado.getEnderecos().get(0).getNumero());

        assertEquals(clienteVO.getEnderecos().get(0).getComplemento(),
                voClienteCriado.getEnderecos().get(0).getComplemento());

        assertEquals(clienteVO.getEnderecos().get(0).getCidade(),
                voClienteCriado.getEnderecos().get(0).getCidade());

        assertEquals(clienteVO.getEnderecos().get(0).getBairro(),
                voClienteCriado.getEnderecos().get(0).getBairro());

        assertEquals(clienteVO.getEnderecos().get(0).getEstado(),
                voClienteCriado.getEnderecos().get(0).getEstado());

        assertEquals(clienteVO.getEnderecos().get(0).getCep(),
                voClienteCriado.getEnderecos().get(0).getCep());

    }
}