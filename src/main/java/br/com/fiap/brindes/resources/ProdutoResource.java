package br.com.fiap.brindes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.brindes.dto.request.ProdutoRequest;
import br.com.fiap.brindes.dto.response.ProdutoResponse;
import br.com.fiap.brindes.entity.Produto;
import br.com.fiap.brindes.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource implements ResourceDTO<ProdutoRequest, ProdutoResponse> {

    @Autowired
    private ProdutoService produtoService;

    @Override
    @PostMapping
    public ResponseEntity<ProdutoResponse> save(@RequestBody ProdutoRequest request) {
        Produto produto = produtoService.toEntity(request);
        produto = produtoService.save(produto);
        ProdutoResponse response = produtoService.toResponse(produto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        ProdutoResponse response = produtoService.toResponse(produto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        List<ProdutoResponse> responses = produtoService.findAll().stream()
                .map(produtoService::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
