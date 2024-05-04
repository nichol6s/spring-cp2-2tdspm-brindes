package br.com.fiap.brindes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Atenção aluno, para o CP 2 - Não precisa alterar nada neste arquivo
 */
@RestController
@RequestMapping(value = "/")
public class Index {

    /**
     * Exibe a página inicial da loja.
     * <p>
     * https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html
     *
     * @param p
     * @return
     */

    @GetMapping
    public ModelAndView index(Principal p) {
        var mv = new ModelAndView( "index" );
        mv.addObject( "usuario", p );
        return mv;
    }

    /**
     * Acessa detalhes do Usuário Autenticado JSON
     *
     * @param p
     * @return
     */

    @GetMapping(value = "/user")
    public Principal user(Principal p) {
        return p;
    }

}
