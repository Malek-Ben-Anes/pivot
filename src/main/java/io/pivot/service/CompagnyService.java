package io.pivot.service;

import io.pivot.entity.Compagny;
import io.pivot.entity.PurchaseRequest;
import io.pivot.enums.PurchaseRequestStatus;
import io.pivot.exception.NotFoundException;
import io.pivot.repository.CompagnyRepository;
import io.pivot.repository.PurchaseRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompagnyService {

    private static final BigDecimal INITIAL_BUDGET = new BigDecimal("500000");

    private final CompagnyRepository compagnyRepository;


    public Compagny create(Compagny Compagny) {
        Compagny.setBudget(INITIAL_BUDGET);
        return compagnyRepository.save(Compagny);
    }

}
