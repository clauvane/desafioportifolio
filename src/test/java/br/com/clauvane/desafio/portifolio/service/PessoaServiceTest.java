package br.com.clauvane.desafio.portifolio.service;

import br.com.clauvane.desafio.portifolio.model.entity.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @MockBean
    private PessoaService mockPessoaService;

    @Test
    public void testGetAllPessoas() {
        pessoaService.findAll();
        verify(mockPessoaService, times(1)).findAll();
    }

    @Test
    public void testGetPessoaById() {
        Pessoa pessoa = new Pessoa(1L);
        when(mockPessoaService.findById(1L)).thenReturn(Optional.of(pessoa));

        Optional<Pessoa> returnedPessoa = pessoaService.findById(1L);

        assertThat(returnedPessoa).isNotNull();
        assertThat(returnedPessoa.get()).isEqualTo(pessoa);
    }

    @Test
    public void testCreatePessoa() {
        Pessoa pessoa = new Pessoa(1L);
        when(mockPessoaService.saveOrUpdate(pessoa)).thenReturn(pessoa);

        Pessoa returnedPessoa = pessoaService.saveOrUpdate(pessoa);

        assertThat(returnedPessoa).isNotNull();
        assertThat(returnedPessoa).isEqualTo(pessoa);
    }

    @Test
    public void testUpdatePessoa() {
        Pessoa pessoa = new Pessoa(1L);
        when(mockPessoaService.saveOrUpdate(pessoa)).thenReturn(pessoa);

        Pessoa returnedPessoa = pessoaService.saveOrUpdate(pessoa);

        assertThat(returnedPessoa).isNotNull();
        assertThat(returnedPessoa).isEqualTo(pessoa);
    }

    @Test
    public void testDeletePessoa() {
        Pessoa pessoa = new Pessoa(1L);
        doNothing().when(mockPessoaService).delete(pessoa);

        pessoaService.delete(pessoa);

        verify(mockPessoaService, times(1)).delete(pessoa);
    }
}