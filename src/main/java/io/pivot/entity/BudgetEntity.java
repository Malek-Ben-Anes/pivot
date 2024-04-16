package io.pivot.entity;

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
public class BudgetEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long companyId;

    /**
     * the amount that has already been spent from the budget
     */
    private BigDecimal currentExpenditure;

    private LocalDateTime lastUpdatedDate;
}
