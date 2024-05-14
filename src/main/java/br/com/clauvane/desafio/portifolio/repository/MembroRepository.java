package br.com.clauvane.desafio.portifolio.repository;

import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.MembroId;
import br.com.clauvane.desafio.portifolio.model.entity.Pessoa;
import br.com.clauvane.desafio.portifolio.model.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroRepository extends JpaRepository<Membro, MembroId> {

    @Query("SELECT m FROM Membro m WHERE m.membroId.projeto = :projeto")
    List<Membro> findAllByProjeto(Projeto projeto);

    @Query("SELECT m FROM Membro m WHERE m.membroId.pessoa = :pessoa")
    List<Membro> findAllByPessoa(Pessoa pessoa);

}
