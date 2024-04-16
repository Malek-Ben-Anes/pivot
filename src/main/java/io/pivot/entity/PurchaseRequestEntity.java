package io.pivot.entity;

import io.pivot.enums.PurchaseRequestStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class PurchaseRequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    private PurchaseRequestStatus status;

    @ManyToOne
    private CompagnyEntity compagnyEntity;

    private String description;

    private BigDecimal amount;

    private LocalDateTime issueDate;
}
