package br.com.fiap.brindes.dto.response;

import lombok.Builder;

@Builder
public record CategoriaResponse(
        Long id,
        String nome
) {

}
