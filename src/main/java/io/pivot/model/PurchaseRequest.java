package io.pivot.model;

import io.pivot.enums.PurchaseRequestStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class PurchaseRequest {
    private Long id;
    private PurchaseRequestStatus status;
    private Long companyId;
    private String description;
    private BigDecimal amount;
    private LocalDateTime issueDate;
}
