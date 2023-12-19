package com.fiap.tech.domain.entity.produto;

import com.fiap.tech.domain.entity.produto.validations.CriaProdutoValidation;
import com.fiap.tech.domain.exception.produto.NomeNaoPodeSerVazioException;
import com.fiap.tech.domain.exception.produto.ValorDoProdutoMenorQueZeroException;
import com.fiap.tech.domain.input.produto.EditaProdutoInput;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Data
@Getter
@Setter
public class Produto {
    private UUID uuid;
    private String nome;
    private Float valor;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Integer quantidade;
    private Date dataCriacao;


    public Produto(String nome, Float valor, String descricao, Categoria categoria, Integer quantidade, Date dataCriacao) {
        this.uuid = uuid;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.dataCriacao = dataCriacao;
    }

    public Produto criaProduto() throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        return new CriaProdutoValidation().validaEntidade(this);
    }

    public void atualizaProduto(EditaProdutoInput editaProdutoInput) throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        this.setNome(editaProdutoInput.nome());
        this.setValor(editaProdutoInput.valor());
        this.setDescricao(editaProdutoInput.descricao());
        this.setCategoria(editaProdutoInput.categoria());
        this.setQuantidade(editaProdutoInput.quantidade());
        this.setDataCriacao(editaProdutoInput.dataCriacao());
        new CriaProdutoValidation().validaEntidade(this);
    }
}
