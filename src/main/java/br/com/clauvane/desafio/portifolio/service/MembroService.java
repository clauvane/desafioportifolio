package br.com.clauvane.desafio.portifolio.service;

import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.MembroId;
import br.com.clauvane.desafio.portifolio.model.entity.Pessoa;
import br.com.clauvane.desafio.portifolio.model.entity.Projeto;
import br.com.clauvane.desafio.portifolio.repository.MembroRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    private MembroRepository membroRepository;

    public MembroService(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @Transactional
    public Membro saveOrUpdate(Membro membro) {
        return membroRepository.saveAndFlush(membro);
    }

    @Transactional
    public void delete(Membro membro) {
        membroRepository.delete(membro);
    }

    public List<Membro> findAll() {
        return membroRepository.findAll();
    }

    public List<Membro> findAllByProjeto(Long idProjeto) {
        return membroRepository.findAllByProjeto(new Projeto(idProjeto));
    }

    public List<Membro> findAllByPessoa(Long idPessoa) {
        return membroRepository.findAllByPessoa(new Pessoa(idPessoa));
    }

    public Optional<Membro> findById(MembroId id) {
        return membroRepository.findById(id);
    }

}
