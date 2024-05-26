package br.com.clauvane.desafio.portifolio.service;

import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.MembroId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@SpringBootTest
class MembroServiceTest {

    @Autowired
    private MembroService membroService;

    @MockBean
    private MembroService mockMembroService;

    @Test
    void testGetAllMembros() {
        membroService.findAll();
        verify(mockMembroService, times(1)).findAll();
    }

    @Test
    void testGetAllMembrosByProjeto() {
        membroService.findAllByProjeto(1L);
        verify(mockMembroService, times(1)).findAllByProjeto(any());
    }

    @Test
    void testGetAllMembrosByPessoa() {
        membroService.findAllByPessoa(1L);
        verify(mockMembroService, times(1)).findAllByPessoa(any());
    }

    @Test
    void testGetMembroById() {
        MembroId membroId = new MembroId(1L, 1L);
        Membro membro = new Membro(membroId);
        when(mockMembroService.findById(membroId)).thenReturn(Optional.of(membro));

        Optional<Membro> membroBd = membroService.findById(membroId);

        assertAll("Deveria retornar o membro",
            () -> assertThat(membroBd).isNotNull(),
            () -> assertThat(membro).isEqualTo(membroBd.get())
        );
    }

    @Test
    void testCreateMembro() {
        Membro membro = new Membro(new MembroId(1L, 1L));
        when(mockMembroService.saveOrUpdate(membro)).thenReturn(membro);

        Membro membroBd = membroService.saveOrUpdate(membro);

        assertAll("Deveria retornar o membro salvo",
            () -> assertThat(membroBd).isNotNull(),
            () -> assertThat(membroBd).isEqualTo(membro)
        );
    }

    @Test
    void testUpdateMembro() {
        Membro membro = new Membro(new MembroId(1L, 1L));
        when(mockMembroService.saveOrUpdate(membro)).thenReturn(membro);

        Membro membroBd = membroService.saveOrUpdate(membro);

        assertAll("Deveria retornar o membro atualizado",
            () -> assertThat(membroBd).isNotNull(),
            () -> assertThat(membroBd).isEqualTo(membro)
        );
    }

    @Test
    void testDeleteMembro() {
        Membro membro = new Membro(new MembroId(1L, 1L));
        doNothing().when(mockMembroService).delete(membro);

        membroService.delete(membro);

        verify(mockMembroService, times(1)).delete(membro);
    }
}