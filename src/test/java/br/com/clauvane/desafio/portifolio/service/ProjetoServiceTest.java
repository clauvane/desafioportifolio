package br.com.clauvane.desafio.portifolio.service;

import br.com.clauvane.desafio.portifolio.model.entity.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProjetoServiceTest {

    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoService mockProjetoService;

    @Test
    public void testGetAllProjetos() {
        projetoService.findAll();
        verify(mockProjetoService, times(1)).findAll();
    }

    @Test
    public void testGetProjetoById() {
        Projeto projeto = new Projeto(1L);
        when(mockProjetoService.findById(1L)).thenReturn(Optional.of(projeto));

        Optional<Projeto> returnedProjeto = projetoService.findById(1L);

        assertThat(returnedProjeto).isNotNull();
        assertThat(returnedProjeto.get()).isEqualTo(projeto);
    }

    @Test
    public void testCreateProjeto() {
        Projeto projeto = new Projeto(1L);
        when(mockProjetoService.saveOrUpdate(projeto)).thenReturn(projeto);

        Projeto returnedProjeto = projetoService.saveOrUpdate(projeto);

        assertThat(returnedProjeto).isNotNull();
        assertThat(returnedProjeto).isEqualTo(projeto);
    }

    @Test
    public void testUpdateProjeto() {
        Projeto projeto = new Projeto(1L);
        when(mockProjetoService.saveOrUpdate(projeto)).thenReturn(projeto);

        Projeto returnedProjeto = projetoService.saveOrUpdate(projeto);

        assertThat(returnedProjeto).isNotNull();
        assertThat(returnedProjeto).isEqualTo(projeto);
    }

    @Test
    public void testDeleteProjeto() {
        Projeto projeto = new Projeto(1L);
        doNothing().when(mockProjetoService).delete(projeto);

        projetoService.delete(projeto);

        verify(mockProjetoService, times(1)).delete(projeto);
    }
}