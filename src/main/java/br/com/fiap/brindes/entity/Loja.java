package br.com.fiap.brindes.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tb_loja", uniqueConstraints = {@UniqueConstraint(name="uk_nome_loja", columnNames = "nm_loja")})
public class Loja {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_loja")
    @SequenceGenerator(name="sq_loja", sequenceName = "sq_loja", allocationSize = 1)
    @Column(name="id_loja", nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(name="nm_loja", nullable = false, unique = true)
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_loja_produto",
            joinColumns = @JoinColumn(name = "id_loja", foreignKey = @ForeignKey(name = "fk_loja_produto_loja")),
            inverseJoinColumns = @JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "fk_loja_produto_produto"))
    )
    private Set<Produto> produtosComercializados = new LinkedHashSet<>();

}
