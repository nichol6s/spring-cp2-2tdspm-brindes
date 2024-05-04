package br.com.fiap.brindes.dto.response;

public record ProdutoResponse(
        Long id,
        String nome,
        Double preco,
        CategoriaResponse categoria
){

}
