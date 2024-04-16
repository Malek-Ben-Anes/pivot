package io.pivot.dto;

import io.pivot.enums.PurchaseRequestStatus;
import lombok.Data;

@Data
public class UpdatePurchaseRequest {
    private PurchaseRequestStatus status;
}
