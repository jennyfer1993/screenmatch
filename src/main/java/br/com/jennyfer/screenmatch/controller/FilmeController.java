package br.com.jennyfer.screenmatch.controller;

import br.com.jennyfer.screenmatch.domain.filme.DadosAlteracaoFilmes;
import br.com.jennyfer.screenmatch.domain.filme.DadosCadastroFilmes;
import br.com.jennyfer.screenmatch.domain.filme.Filme;
import br.com.jennyfer.screenmatch.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller //adicionar anotação para o Spring entender que essa classe é controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        if (id != null) {
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll()); // acesso ao banco de dados encapsulado
        return "filmes/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastraFilme(DadosCadastroFilmes dados) {
        //System.out.println(dados);
        var filme = new Filme(dados);
        repository.save(filme);

        return "redirect:/filmes"; // fazer o redirect para utilizar o método de carregaPaginaListagem e redirect é via método GET
    }

    @PutMapping
    @Transactional
    public String alteraFilme(DadosAlteracaoFilmes dados) {
        //System.out.println(dados);
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes"; // fazer o redirect para utilizar o método de carregaPaginaListagem e redirect é via método GET
    }

    @DeleteMapping
    @Transactional
    public String removeFilme(Long id) {
        repository.deleteById(id);

        return "redirect:/filmes";
    }
}
