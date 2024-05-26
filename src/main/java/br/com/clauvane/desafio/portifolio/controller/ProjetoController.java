package br.com.clauvane.desafio.portifolio.controller;

import br.com.clauvane.desafio.portifolio.model.dto.ProjetoDto;
import br.com.clauvane.desafio.portifolio.model.entity.Membro;
import br.com.clauvane.desafio.portifolio.model.entity.Projeto;
import br.com.clauvane.desafio.portifolio.model.enums.StatusProjeto;
import br.com.clauvane.desafio.portifolio.service.MembroService;
import br.com.clauvane.desafio.portifolio.service.PessoaService;
import br.com.clauvane.desafio.portifolio.service.ProjetoService;
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
@RequestMapping("/portifolio/projeto")
public class ProjetoController {

    private static final String PROJETO_NAO_ENCONTRADO = "Projeto não encontrado.";
    private static final String PROJETOS = "projetos";
    private static final String PROJETO_LISTAGEM = "projeto/listagem";
    private static final String FORM_ACTION = "formAction";
    private static final String PROJETO_FORMULARIO = "projeto/formulario";
    private static final String PROJETO_POSSUI_MEMBROS_CADASTRADOS = "Projeto possui membros cadastrados.";
    private static final String PROJETO_SALVO_COM_SUCESSO = "Projeto salvo com sucesso";
    private static final String PROJETO = "projeto";
    private static final String MSG = "msg";
    private static final String SUCESSO = "SUCESSO";
    private static final String ERRO = "ERRO";
    private static final String TIPO_MSG = "tipoMsg";
    private static final String PROJETO_ATUALIZADO_COM_SUCESSO = "PROJETO ATUALIZADA COM SUCESSO";
    private static final String PROJETO_REMOVIDO_COM_SUCESSO = "PROJETO REMOVIDO COM SUCESSO";
    private static final String NAO_POSSIVEL_RETROCEDER_STATUS_PROJETO = "Não é possível retroceder o status do projeto.";
    private static final String NAO_POSSIVEL_AVANCAR_MAIS_DE_UM_STATUS_PROJETO = "Não é possível avançar mais de um status do projeto.";
    private static final String PESSOAS = "pessoas";
    private static final String DATA_PREVISAO_FIM_DEVE_SER_MAIOR_QUE_DATA_INICIO = "Data previsão de fim deve ser maior que a data de início.";
    private static final String DATA_FIM_DEVE_SER_MAIOR_QUE_DATA_INICIO = "Data fim deve ser maior que a data de início.";
    public static final String NAO_POSSIVEL_REMOVER_PROJETO_NESTES_STATUS = "Não é possível remover projeto neste status: INICIADO, EM ANDAMENTO e ENCERRADO.";

    private ProjetoService projetoService;
    private PessoaService pessoaService;
    private MembroService membroService;

    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService, MembroService membroService) {
        this.projetoService = projetoService;
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
        List<Projeto> projetos = projetoService.findAll();
        model.addAttribute(PROJETOS, projetos);
        return PROJETO_LISTAGEM;
    }

    @GetMapping("/salvar")
    public String prepararSalvar(ModelMap model){
      model.addAttribute(PROJETO, new Projeto());
      model.addAttribute(PESSOAS, pessoaService.findAllGerentes());
      model.addAttribute(FORM_ACTION, "salvar");

      return PROJETO_FORMULARIO;
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute(PROJETO) ProjetoDto projetoDto, Model model){
        Projeto projeto = new Projeto();
        BeanUtils.copyProperties(projetoDto, projeto);
        projetoService.saveOrUpdate(projeto);
        model.addAttribute(PROJETO, new Projeto());
        model.addAttribute(PESSOAS, pessoaService.findAllGerentes());
        model.addAttribute(MSG, PROJETO_SALVO_COM_SUCESSO);
        model.addAttribute(TIPO_MSG, SUCESSO);
        return PROJETO_FORMULARIO;
    }

    @GetMapping("/editar")
    public String prepararEditar(@RequestParam String id, Model model){
        Optional<Projeto> projeto = projetoService.findById(Long.parseLong(id));
        if (!projeto.isPresent()) {
            throw new IllegalArgumentException(PROJETO_NAO_ENCONTRADO);
        }
        model.addAttribute(PROJETO, projeto.get());
        model.addAttribute(PESSOAS, pessoaService.findAllGerentes());
        model.addAttribute(FORM_ACTION, "editar");

        return PROJETO_FORMULARIO;
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute(PROJETO) ProjetoDto projetoDto, Model model){
        Optional<Projeto> projetoBanco = projetoService.findById(projetoDto.getId());
        if (projetoBanco.isEmpty()) {
            throw new IllegalArgumentException(PROJETO_NAO_ENCONTRADO);
        }

        if(hasErrorForEdit(projetoDto, projetoBanco.get(), model)) {
            model.addAttribute(TIPO_MSG, ERRO);
            model.addAttribute(PROJETO, projetoBanco.get());
            model.addAttribute(PESSOAS, pessoaService.findAllGerentes());
            return PROJETO_FORMULARIO;
        }

        Projeto projeto = new Projeto();
        BeanUtils.copyProperties(projetoDto, projeto);
        projetoService.saveOrUpdate(projeto);
        List<Projeto> projetos = projetoService.findAll();
        model.addAttribute(PROJETOS, projetos);
        model.addAttribute(PESSOAS, pessoaService.findAllGerentes());
        model.addAttribute(MSG, PROJETO_ATUALIZADO_COM_SUCESSO);
        model.addAttribute(TIPO_MSG, SUCESSO);
        return PROJETO_LISTAGEM;
    }

    private boolean hasErrorForEdit(ProjetoDto projetoDto, Projeto projetoBanco, Model model) {
        Integer idStatusDto = projetoDto.getStatus() == null ? 0 : projetoDto.getStatus().getId();
        Integer idStatusBanco = projetoBanco.getStatus() == null ? 0 : projetoBanco.getStatus().getId();
        if(idStatusDto < idStatusBanco) {
            model.addAttribute(MSG, NAO_POSSIVEL_RETROCEDER_STATUS_PROJETO);

            return true;
        }

        if(idStatusDto > idStatusBanco && idStatusDto - idStatusBanco > 1) {
            model.addAttribute(MSG, NAO_POSSIVEL_AVANCAR_MAIS_DE_UM_STATUS_PROJETO);

            return true;
        }

        if (projetoDto.getDataPrevisaoFim() != null
                && projetoDto.getDataInicio() != null
                && !projetoDto.getDataPrevisaoFim().after(projetoDto.getDataInicio())) {
            model.addAttribute(MSG, DATA_PREVISAO_FIM_DEVE_SER_MAIOR_QUE_DATA_INICIO);

            return true;
        }

        if (projetoDto.getDataFim() != null
                && projetoDto.getDataInicio() != null
                && !projetoDto.getDataFim().after(projetoDto.getDataInicio())) {
            model.addAttribute(MSG, DATA_FIM_DEVE_SER_MAIOR_QUE_DATA_INICIO);

            return true;
        }

        return false;
    }

    @GetMapping("/remover")
    public String remover(@RequestParam String id, Model model){
        Optional<Projeto> projeto = projetoService.findById(Long.parseLong(id));
        if (!projeto.isPresent()) {
            throw new IllegalArgumentException(PROJETO_NAO_ENCONTRADO);
        }

        if(hasErrorForDelete(projeto.get(), model)) {
            model.addAttribute(TIPO_MSG, ERRO);
        } else {
            projetoService.delete(projeto.get());

            List<Projeto> projetos = projetoService.findAll();
            model.addAttribute(PROJETOS, projetos);
            model.addAttribute(MSG, PROJETO_REMOVIDO_COM_SUCESSO);
            model.addAttribute(TIPO_MSG, SUCESSO);
        }

        return PROJETO_LISTAGEM;
    }

    private boolean hasErrorForDelete(Projeto projeto, Model model) {
        if(projeto.getStatus() != null) {
            List<StatusProjeto> statusProjetoList = List.of(StatusProjeto.INICIADO, StatusProjeto.EM_ANDAMENTO, StatusProjeto.ENCERRADO);
            if(statusProjetoList.contains(projeto.getStatus())){
                model.addAttribute(MSG, NAO_POSSIVEL_REMOVER_PROJETO_NESTES_STATUS);

                return true;
            }

            List<Membro> membros = membroService.findAllByProjeto(projeto.getId());
            if (!membros.isEmpty()) {
                model.addAttribute(MSG, PROJETO_POSSUI_MEMBROS_CADASTRADOS);

                return true;
            }
        }

        return false;
    }

}
