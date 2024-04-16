package io.pivot.service;

import io.pivot.entity.CompagnyEntity;
import io.pivot.repository.CompagnyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompagnyService {

    private static final BigDecimal INITIAL_BUDGET = new BigDecimal(500000);

    private final CompagnyRepository compagnyRepository;


    public CompagnyEntity create(CompagnyEntity compagnyEntity) {
        compagnyEntity.setBudget(INITIAL_BUDGET);
        return compagnyRepository.save(compagnyEntity);
    }

}
