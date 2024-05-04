package br.com.fiap.brindes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.brindes.dto.request.CategoriaRequest;
import br.com.fiap.brindes.dto.response.CategoriaResponse;
import br.com.fiap.brindes.entity.Categoria;
import br.com.fiap.brindes.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource implements ResourceDTO<CategoriaRequest, CategoriaResponse> {

    @Autowired
    private CategoriaService categoriaService;

    @Override
    @PostMapping
    public ResponseEntity<CategoriaResponse> save(@RequestBody CategoriaRequest request) {
        Categoria categoria = categoriaService.toEntity(request);
        categoria = categoriaService.save(categoria);
        CategoriaResponse response = categoriaService.toResponse(categoria);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        CategoriaResponse response = categoriaService.toResponse(categoria);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        List<CategoriaResponse> responses = categoriaService.findAll().stream()
                .map(categoriaService::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
