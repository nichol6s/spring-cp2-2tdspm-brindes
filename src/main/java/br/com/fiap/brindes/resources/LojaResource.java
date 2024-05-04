package br.com.fiap.brindes.resources;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.brindes.dto.request.LojaRequest;
import br.com.fiap.brindes.dto.request.ProdutoRequest;
import br.com.fiap.brindes.dto.response.LojaResponse;
import br.com.fiap.brindes.entity.Loja;
import br.com.fiap.brindes.entity.Produto;
import br.com.fiap.brindes.service.LojaService;
import br.com.fiap.brindes.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lojas")
public class LojaResource implements ResourceDTO<LojaRequest, LojaResponse> {
    @Autowired
    private LojaService lojaService;

    @Autowired
    private ProdutoService produtoService;

    @Override
    @PostMapping
    public ResponseEntity<LojaResponse> save(@RequestBody LojaRequest request) {
        Loja loja = lojaService.toEntity(request);
        loja = lojaService.save(loja);
        LojaResponse response = lojaService.toResponse(loja);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<LojaResponse> findById(@PathVariable Long id) {
        Loja loja = lojaService.findById(id);
        LojaResponse response = lojaService.toResponse(loja);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LojaResponse>> listarLojas() {
        List<LojaResponse> responses = lojaService.findAll().stream()
                .map(lojaService::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @Transactional
    @PostMapping("/{id}/produtos-comercializados")
    public ResponseEntity<Void> adicionarProduto(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        Loja loja = lojaService.findById(id);

        Produto produto = produtoService.toEntity(request);
        produto = produtoService.save(produto);

        Set<Produto> produtosComercializados = loja.getProdutosComercializados();
        produtosComercializados.add(produto);
        loja.setProdutosComercializados(produtosComercializados);

        lojaService.save(loja);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
