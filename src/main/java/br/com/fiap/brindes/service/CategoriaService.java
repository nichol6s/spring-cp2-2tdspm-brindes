package br.com.fiap.brindes.service;

import br.com.fiap.brindes.dto.request.CategoriaRequest;
import br.com.fiap.brindes.dto.response.CategoriaResponse;
import br.com.fiap.brindes.entity.Categoria;
import br.com.fiap.brindes.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoriaService implements ServiceDTO<Categoria, CategoriaRequest, CategoriaResponse>{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Collection<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Collection<Categoria> findAll(Example<Categoria> example) {
        return categoriaRepository.findAll(example);
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria toEntity(CategoriaRequest dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        return categoria;
    }

    @Override
    public CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }
}
