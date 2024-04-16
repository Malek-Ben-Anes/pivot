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
public class Compagny {

    @Id
    @GeneratedValue
    private Long id;

    private Long companyId;

    /**
     * the amount that has already been spent from the budget
     */
    private String description;

    private BigDecimal budget;
}
