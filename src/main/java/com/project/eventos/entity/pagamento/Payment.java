package com.project.eventos.entity.pagamento;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.project.eventos.enums.pagamento.Method;
import com.project.eventos.enums.pagamento.PaymentStatus;

import jakarta.persistence.Id;
import jakarta.persistence.*;





@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_id", nullable = false)
    private Long registrationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Method method;

    @Column(nullable = false)
    private String gateway;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaymentStatus status;

    @Column(name = "provider_ref")
    private String providerRef;

    @Column(name = "qr_code", columnDefinition = "TEXT")
    private String qrCode;

    @Column(name = "paid_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime paidAt;

    public Payment() {
    }
    
    public Payment(Long registrationId, Method method, String gateway, BigDecimal amount, PaymentStatus status) {
        this.registrationId = registrationId;
        this.method = method;
        this.gateway = gateway;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public Method getMethod() {
        return method;
    }

    public String getGateway() {
        return gateway;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getProviderRef() {
        return providerRef;
    }

    public String getQrCode() {
        return qrCode;
    }

    public OffsetDateTime getPaidAt() {
        return paidAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}