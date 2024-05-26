package br.com.clauvane.desafio.portifolio.service;

import br.com.clauvane.desafio.portifolio.model.entity.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjetoServiceTest {

    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoService mockProjetoService;

    @Test
    void testGetAllProjetos() {
        projetoService.findAll();
        verify(mockProjetoService, times(1)).findAll();
    }

    @Test
    void testGetProjetoById() {
        Projeto projeto = new Projeto(1L);
        when(mockProjetoService.findById(1L)).thenReturn(Optional.of(projeto));

        Optional<Projeto> projetoBd = projetoService.findById(1L);

        assertAll("Deveria retornar o projeto",
            () -> assertThat(projetoBd).isNotNull(),
            () -> assertThat(projeto).isEqualTo(projetoBd.get())
        );
    }

    @Test
    void testCreateProjeto() {
        Projeto projeto = new Projeto(1L);
        when(mockProjetoService.saveOrUpdate(projeto)).thenReturn(projeto);

        Projeto returnedProjeto = projetoService.saveOrUpdate(projeto);

        assertAll("Deveria retornar o projeto salvo",
            () -> assertThat(returnedProjeto).isNotNull(),
            () -> assertThat(returnedProjeto).isEqualTo(projeto)
        );
    }

    @Test
    void testUpdateProjeto() {
        Projeto projeto = new Projeto(1L);
        when(mockProjetoService.saveOrUpdate(projeto)).thenReturn(projeto);

        Projeto returnedProjeto = projetoService.saveOrUpdate(projeto);

        assertAll("Deveria retornar o projeto salvo",
            () -> assertThat(returnedProjeto).isNotNull(),
            () -> assertThat(returnedProjeto).isEqualTo(projeto)
        );
    }

    @Test
    void testDeleteProjeto() {
        Projeto projeto = new Projeto(1L);
        doNothing().when(mockProjetoService).delete(projeto);

        projetoService.delete(projeto);

        verify(mockProjetoService, times(1)).delete(projeto);
    }
}