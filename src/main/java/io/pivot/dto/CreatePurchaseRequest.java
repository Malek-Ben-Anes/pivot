package io.pivot.dto;

import io.pivot.enums.PurchaseRequestStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePurchaseRequest {

    private PurchaseRequestStatus status;

    private Long compagnyId;

    private String description;

    private BigDecimal amount;
}
