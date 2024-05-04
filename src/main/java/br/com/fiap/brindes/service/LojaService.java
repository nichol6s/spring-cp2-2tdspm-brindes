package br.com.fiap.brindes.service;

import br.com.fiap.brindes.dto.request.LojaRequest;
import br.com.fiap.brindes.dto.response.LojaResponse;
import br.com.fiap.brindes.dto.response.ProdutoResponse;
import br.com.fiap.brindes.entity.Loja;
import br.com.fiap.brindes.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LojaService implements ServiceDTO<Loja, LojaRequest, LojaResponse> {
    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public Collection<Loja> findAll() {
        return lojaRepository.findAll();
    }

    @Override
    public Collection<Loja> findAll(Example<Loja> example) {
        return lojaRepository.findAll(example);
    }

    @Override
    public Loja findById(Long id) {
        return lojaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));
    }

    @Override
    public Loja save(Loja loja) {
        return lojaRepository.save(loja);
    }

    @Override
    public Loja toEntity(LojaRequest dto) {
        Loja loja = new Loja();
        loja.setNome(dto.nome());
        return loja;
    }

    @Override
    public LojaResponse toResponse(Loja loja) {
        Collection<ProdutoResponse> produtosComercializados = loja.getProdutosComercializados()
                .stream()
                .map(produtoService::toResponse)
                .collect(Collectors.toList());

        return new LojaResponse(
                loja.getId(),
                loja.getNome(),
                produtosComercializados
        );
    }
}
