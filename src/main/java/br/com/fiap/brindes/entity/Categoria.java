package br.com.fiap.brindes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "tb_categoria", uniqueConstraints = {@UniqueConstraint(name="uk_nome_categoria", columnNames = "nm_categoria")})
public class Categoria {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_categoria")
    @SequenceGenerator(name="sq_categoria", sequenceName = "sq_categoria", allocationSize = 1)
    @Column(name="id_categoria", nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(name="nm_categoria",  nullable = false, unique = true)
    private String nome;


    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Produto> produtos;

}
