package br.com.clauvane.desafio.portifolio.controller;

import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.MembroId;
import br.com.clauvane.desafio.portifolio.model.entity.Pessoa;
import br.com.clauvane.desafio.portifolio.model.entity.Projeto;
import br.com.clauvane.desafio.portifolio.service.MembroService;
import br.com.clauvane.desafio.portifolio.service.PessoaService;
import br.com.clauvane.desafio.portifolio.service.ProjetoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MembroController.class)
public class MembroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MembroService membroService;

    @MockBean
    private PessoaService pessoaService;

    @MockBean
    private ProjetoService projetoService;

    @Test
    public void testGetAllMembros() throws Exception {
        mockMvc.perform(get("/api/membros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMembroById() throws Exception {
        MembroId membroId = new MembroId(1L, 1L);
        Membro membro = new Membro(membroId);
        when(membroService.findById(membroId)).thenReturn(Optional.of(membro));

        mockMvc.perform(get("/api/membros/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateMembro() throws Exception {
        when(
                pessoaService.findById(1L)
        ).thenReturn(
                Optional.of(new Pessoa(1L))
        );

        when(
                projetoService.findById(1L)
        ).thenReturn(
                Optional.of(new Projeto(1L))
        );

        mockMvc.perform(post("/api/membros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idPessoa\": 1, \"idProjeto\": 1}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateMembro() throws Exception {
        when(
                pessoaService.findById(1L)
        ).thenReturn(
                Optional.of(new Pessoa(1L))
        );

        when(
                projetoService.findById(1L)
        ).thenReturn(
                Optional.of(new Projeto(1L))
        );

        mockMvc.perform(put("/api/membros/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idPessoa\": 1, \"idProjeto\": 1}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteMembro() throws Exception {
        when(
                pessoaService.findById(1L)
        ).thenReturn(
                Optional.of(new Pessoa(1L))
        );

        when(
                projetoService.findById(1L)
        ).thenReturn(
                Optional.of(new Projeto(1L))
        );

        MembroId membroId = new MembroId(1L, 1L);
        Membro membro = new Membro(membroId);
        when(membroService.findById(membroId)).thenReturn(Optional.of(membro));

        mockMvc.perform(delete("/api/membros/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}