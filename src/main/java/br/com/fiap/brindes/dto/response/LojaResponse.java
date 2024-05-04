package br.com.fiap.brindes.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record LojaResponse(
        Long id,
        String nome,
        Collection<ProdutoResponse> produtosComercializados
) {

}
