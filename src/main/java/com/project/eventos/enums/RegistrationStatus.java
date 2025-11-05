package com.project.eventos.enums;

public enum RegistrationStatus {
    PENDING,           // aguardando pagamento/confirmação
    RESERVED,          // vaga reservada sem pagamento
    CONFIRMED,         // confirmada
    PAID,              // paga
    PAYMENT_FAILED,    // falha no pagamento
    WAITLISTED,        // lista de espera
    EXPIRED,           // expirada
    CANCELLED,         // cancelada
    REFUND_REQUESTED,  // solicitação de reembolso
    REFUNDED,          // reembolsada
    CHECKED_IN,        // presente/registrado no evento
    NO_SHOW;
    
    
    //TO-DO
    
}
