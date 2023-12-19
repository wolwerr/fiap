package com.fiap.tech.domain.useCase.produto;


import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.output.produto.DeletaProdutoOutput;
import com.fiap.tech.domain.port.produto.BuscaProdutoInterface;
import com.fiap.tech.domain.port.produto.DeletarProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class DeletaProdutoUseCase {

    private final DeletarProdutoInterface deletaProduto;
    private final BuscaProdutoInterface produtoInterface;
    private OutputInterface deletaProdutoOutput;

    public void execute(UUID uuid) {
        try {
            Produto produtoEntity = this.produtoInterface.encontraProdutoPorUuid(uuid);

            this.deletaProduto.deletaProduto(produtoEntity.getUuid());
            this.deletaProdutoOutput = new DeletaProdutoOutput(
                    produtoEntity,
                    new OutputStatus(204, "No content", "Produto deletado com sucesso")
            );
            System.out.println("teste");
        } catch (ProdutoNaoEncontradoException e) {
            this.deletaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(404, "Not Found", "Produto não encontrado")
            );
        } catch (Exception e) {
            this.deletaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}


