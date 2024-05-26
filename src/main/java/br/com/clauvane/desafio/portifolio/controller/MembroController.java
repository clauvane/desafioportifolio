package br.com.clauvane.desafio.portifolio.controller;

import br.com.clauvane.desafio.portifolio.model.dto.MembroDto;
import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.MembroId;
import br.com.clauvane.desafio.portifolio.model.entity.Pessoa;
import br.com.clauvane.desafio.portifolio.model.entity.Projeto;
import br.com.clauvane.desafio.portifolio.service.MembroService;
import br.com.clauvane.desafio.portifolio.service.PessoaService;
import br.com.clauvane.desafio.portifolio.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membros")
public class MembroController {

    public static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada.";
    public static final String PROJETO_NAO_ENCONTRADO = "Projeto não encontrado.";

    private final MembroService membroService;
    private final PessoaService pessoaService;
    private final ProjetoService projetoService;

    public MembroController(MembroService membroService, PessoaService pessoaService, ProjetoService projetoService) {
        this.membroService = membroService;
        this.pessoaService = pessoaService;
        this.projetoService = projetoService;
    }

    @GetMapping
    public ResponseEntity<List<Membro>> getAllMembros() {
        return new ResponseEntity<>(membroService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idPessoa}/{idProjeto}")
    public ResponseEntity<Membro> getMembroById(@PathVariable Long idPessoa, @PathVariable Long idProjeto) {
        MembroId membroId = new MembroId(idProjeto, idPessoa);
        Optional<Membro> membro = membroService.findById(membroId);
        if (membro.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(membro.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createMembro(@RequestBody @Valid MembroDto membroDto) {
        Optional<Pessoa> pessoa = pessoaService.findById(membroDto.getIdPessoa());
        if(pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PESSOA_NAO_ENCONTRADA);
        }

        if(!Boolean.TRUE.equals(pessoa.get().getFuncionario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa não é funcionário.");
        }

        Optional<Projeto> projeto = projetoService.findById(membroDto.getIdProjeto());
        if(projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PROJETO_NAO_ENCONTRADO);
        }

        MembroId membroId = new MembroId(membroDto.getIdProjeto(), membroDto.getIdPessoa());
        Membro membro = new Membro(membroId);

        return ResponseEntity.status( HttpStatus.CREATED).body(membroService.saveOrUpdate(membro));
    }

    @PutMapping("/{idPessoa}/{idProjeto}")
    public ResponseEntity<Object> updateMembro(@PathVariable Long idPessoa, @PathVariable Long idProjeto, @RequestBody @Valid MembroDto membroDto) {
        Optional<Pessoa> pessoa = pessoaService.findById(idPessoa);
        if(pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PESSOA_NAO_ENCONTRADA);
        }

        Optional<Projeto> projeto = projetoService.findById(idProjeto);
        if(projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PROJETO_NAO_ENCONTRADO);
        }

        Optional<Pessoa> pessoaUpdate = pessoaService.findById(membroDto.getIdPessoa());
        if(pessoaUpdate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PESSOA_NAO_ENCONTRADA);
        }

        Optional<Projeto> projetoUpdate = projetoService.findById(membroDto.getIdProjeto());
        if(projetoUpdate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PROJETO_NAO_ENCONTRADO);
        }

        MembroId membroId = new MembroId(membroDto.getIdProjeto(), membroDto.getIdPessoa());
        Membro membro = new Membro(membroId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(membroService.saveOrUpdate(membro));
    }

    @DeleteMapping("/{idPessoa}/{idProjeto}")
    public ResponseEntity<Object> deleteMembro(@PathVariable Long idPessoa, @PathVariable Long idProjeto) {
        Optional<Pessoa> pessoa = pessoaService.findById(idPessoa);
        if(pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PESSOA_NAO_ENCONTRADA);
        }

        Optional<Projeto> projeto = projetoService.findById(idProjeto);
        if(projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PROJETO_NAO_ENCONTRADO);
        }

        MembroId membroId = new MembroId(idProjeto, idPessoa);

        membroService.delete(membroService.findById(membroId).orElseThrow());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}