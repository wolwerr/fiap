package com.fiap.tech.domain.useCase;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.exception.cliente.EmailNaoPodeSerNuloException;
import com.fiap.tech.domain.exception.cliente.NomeNaoPodeSerNuloException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.input.IdentificaClienteInput;
import com.fiap.tech.domain.output.IdentificaClienteOutput;
import com.fiap.tech.domain.port.IdentificarClienteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IdentificarClienteUseCase {

    private final IdentificarClienteInterface identificaCliente;
    private OutputInterface identificaClienteOutput;

    public void execute(IdentificaClienteInput identificaClienteInput) throws Exception {
        Cliente cliente = new Cliente(
                identificaClienteInput.getNome(),
                identificaClienteInput.getCpf(),
                identificaClienteInput.getEmail(),
                null
        );
        try {
            cliente = cliente.identificarCliente();
            Cliente clienteEntity = this.identificaCliente.identificarCliente(cliente);
            this.identificaClienteOutput = new IdentificaClienteOutput(
                clienteEntity,
                new OutputStatus(201, "Created", "Cliente criado")
            );
        } catch (NomeNaoPodeSerNuloException | EmailNaoPodeSerNuloException validationException) {
            this.identificaClienteOutput = new OutputError(
                validationException.getMessage(),
                new OutputStatus(422, "Unprocessable Entity", validationException.getMessage())
            );
        }
    }
}
