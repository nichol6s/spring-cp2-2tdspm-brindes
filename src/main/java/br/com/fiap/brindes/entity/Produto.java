package br.com.fiap.brindes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tb_produto", uniqueConstraints = {@UniqueConstraint(columnNames = {"nm_produto", "id_categoria"})})
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_produto")
    @SequenceGenerator(name="sq_produto", sequenceName = "sq_produto", allocationSize = 1)
    @Column(name="id_produto", nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(name="nm_produto",nullable = false, unique = true)
    private String nome;

    @NotNull
    @Column(name="pr_produto", nullable = false)
    private Double preco;

    @ManyToOne()
    @JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name="fk_produto_categoria"))
    private Categoria categoria;

    @ManyToMany(mappedBy = "produtosComercializados")
    private Set<Loja> lojas;
}
