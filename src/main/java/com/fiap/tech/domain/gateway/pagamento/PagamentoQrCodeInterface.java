package com.fiap.tech.domain.gateway.pagamento;

import com.fiap.tech.domain.entity.pagamento.GatewayQrCode;

import java.util.UUID;

public interface PagamentoQrCodeInterface {
    public GatewayQrCode geraQrCodePagamento(UUID uuid, Float valorTotal);
}
