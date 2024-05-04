package br.com.fiap.brindes.service;

import br.com.fiap.brindes.dto.request.ProdutoRequest;
import br.com.fiap.brindes.dto.response.ProdutoResponse;
import br.com.fiap.brindes.dto.response.CategoriaResponse;
import br.com.fiap.brindes.entity.Produto;
import br.com.fiap.brindes.repository.CategoriaRepository;
import br.com.fiap.brindes.repository.Produtorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProdutoService implements ServiceDTO<Produto, ProdutoRequest, ProdutoResponse> {

    @Autowired
    private Produtorepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Collection<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Collection<Produto> findAll(Example<Produto> example) {
        return produtoRepository.findAll(example);
    }

    @Override
    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto toEntity(ProdutoRequest dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setCategoria(categoriaRepository.findById(dto.categoria().id())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
        return produto;
    }

    @Override
    public ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                new CategoriaResponse(produto.getCategoria().getId(), produto.getCategoria().getNome()));
    }
}
