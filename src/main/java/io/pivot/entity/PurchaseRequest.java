package io.pivot.entity;

import io.pivot.enums.PurchaseRequestStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
@Entity
public class PurchaseRequest {
    @Id
    @GeneratedValue
    private Long id;

    private PurchaseRequestStatus status;

    private Long companyId;

    private String description;

    private BigDecimal amount;

    private LocalDateTime issueDate;
}
