package br.com.clauvane.desafio.portifolio.service;

import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.MembroId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MembroServiceTest {

    @Autowired
    private MembroService membroService;

    @MockBean
    private MembroService mockMembroService;

    @Test
    public void testGetAllMembros() {
        membroService.findAll();
        verify(mockMembroService, times(1)).findAll();
    }

    @Test
    public void testGetAllMembrosByProjeto() {
        membroService.findAllByProjeto(1L);
        verify(mockMembroService, times(1)).findAllByProjeto(any());
    }

    @Test
    public void testGetAllMembrosByPessoa() {
        membroService.findAllByPessoa(1L);
        verify(mockMembroService, times(1)).findAllByPessoa(any());
    }

    @Test
    public void testGetMembroById() {
        MembroId membroId = new MembroId(1L, 1L);
        Membro membro = new Membro(membroId);
        when(mockMembroService.findById(membroId)).thenReturn(Optional.of(membro));

        Optional<Membro> returnedMembro = membroService.findById(membroId);

        assertThat(returnedMembro).isNotNull();
        assertThat(returnedMembro.get()).isEqualTo(membro);
    }

    @Test
    public void testCreateMembro() {
        Membro membro = new Membro(new MembroId(1L, 1L));
        when(mockMembroService.saveOrUpdate(membro)).thenReturn(membro);

        Membro returnedMembro = membroService.saveOrUpdate(membro);

        assertThat(returnedMembro).isNotNull();
        assertThat(returnedMembro).isEqualTo(membro);
    }

    @Test
    public void testUpdateMembro() {
        Membro membro = new Membro(new MembroId(1L, 1L));
        when(mockMembroService.saveOrUpdate(membro)).thenReturn(membro);

        Membro returnedMembro = membroService.saveOrUpdate(membro);

        assertThat(returnedMembro).isNotNull();
        assertThat(returnedMembro).isEqualTo(membro);
    }

    @Test
    public void testDeleteMembro() {
        Membro membro = new Membro(new MembroId(1L, 1L));
        doNothing().when(mockMembroService).delete(membro);

        membroService.delete(membro);

        verify(mockMembroService, times(1)).delete(membro);
    }
}