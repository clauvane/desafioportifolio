package br.com.clauvane.desafio.portifolio.controller;

import br.com.clauvane.desafio.portifolio.model.dto.PessoaDto;
import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.Pessoa;
import br.com.clauvane.desafio.portifolio.service.MembroService;
import br.com.clauvane.desafio.portifolio.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/portifolio/pessoa")
public class PessoaController {

    private static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada.";
    private static final String PESSOAS = "pessoas";
    private static final String PESSOA_FORMULARIO = "pessoa/formulario";
    private static final String PESSOA_LISTAGEM = "pessoa/listagem";
    private static final String FORM_ACTION = "formAction";
    private static final String PESSOA = "pessoa";
    private static final String PESSOA_POSSUI_MEMBROS_CADASTRADOS = "Pessoa possui membros cadastrados.";
    private static final String MSG = "msg";
    private static final String SUCESSO = "SUCESSO";
    private static final String ERRO = "ERRO";
    private static final String TIPO_MSG = "tipoMsg";
    private static final String PESSOA_SALVA_COM_SUCESSO = "Pessoa salva com sucesso";
    private static final String PESSOA_ATUALIZADA_COM_SUCESSO = "Pessoa atualizada com sucesso";
    private static final String PESSOA_REMOVIDA_COM_SUCESSO = "Pessoa removida com sucesso";

    private PessoaService pessoaService;
    private MembroService membroService;

    public PessoaController(PessoaService pessoaService, MembroService membroService) {
        this.pessoaService = pessoaService;
        this.membroService = membroService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String listagem(Model model){
        List<Pessoa> pessoas = pessoaService.findAll();
        model.addAttribute(PESSOAS, pessoas);
        return PESSOA_LISTAGEM;
    }

    @GetMapping("/salvar")
    public String prepararSalvar(ModelMap model){
      model.addAttribute(PESSOA, new Pessoa());
      model.addAttribute(FORM_ACTION, "salvar");

      return PESSOA_FORMULARIO;
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute(PESSOA) PessoaDto pessoaDto, Model model){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        pessoaService.saveOrUpdate(pessoa);
        model.addAttribute(PESSOA, new Pessoa());
        model.addAttribute(FORM_ACTION, "salvar");
        model.addAttribute(MSG, PESSOA_SALVA_COM_SUCESSO);
        model.addAttribute(TIPO_MSG, SUCESSO);
        return PESSOA_FORMULARIO;
    }

    @GetMapping("/editar")
    public String prepararEditar(@RequestParam String id, Model model){
        Optional<Pessoa> pessoa = pessoaService.findById(Long.parseLong(id));
        if (!pessoa.isPresent()) {
            throw new IllegalArgumentException(PESSOA_NAO_ENCONTRADA);
        }
        model.addAttribute(PESSOA, pessoa.get());
        model.addAttribute(FORM_ACTION, "editar");

        return PESSOA_FORMULARIO;
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute(PESSOA) PessoaDto pessoaDto, Model model){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        pessoaService.saveOrUpdate(pessoa);
        List<Pessoa> pessoas = pessoaService.findAll();
        model.addAttribute(PESSOAS, pessoas);
        model.addAttribute(MSG, PESSOA_ATUALIZADA_COM_SUCESSO);
        model.addAttribute(TIPO_MSG, SUCESSO);
        return PESSOA_LISTAGEM;
    }

    @GetMapping("/remover")
    public String remover(@RequestParam String id, Model model){
        Optional<Pessoa> pessoa = pessoaService.findById(Long.parseLong(id));
        if (!pessoa.isPresent()) {
            throw new IllegalArgumentException(PESSOA_NAO_ENCONTRADA);
        }

        List<Pessoa> pessoas = pessoaService.findAll();
        model.addAttribute(PESSOAS, pessoas);

        if (hasErrorForDelete(pessoa.get(), model)) {
            model.addAttribute(TIPO_MSG, ERRO);
        } else {
            pessoaService.delete(pessoa.get());
            model.addAttribute(MSG, PESSOA_REMOVIDA_COM_SUCESSO);
            model.addAttribute(TIPO_MSG, SUCESSO);
        }

        return PESSOA_LISTAGEM;
    }

    private boolean hasErrorForDelete(Pessoa pessoa, Model model) {
        List<Membro> membros = membroService.findAllByPessoa(pessoa.getId());
        if(!membros.isEmpty()) {
            model.addAttribute(MSG, PESSOA_POSSUI_MEMBROS_CADASTRADOS);
            return true;
        }

        return false;
    }

}
