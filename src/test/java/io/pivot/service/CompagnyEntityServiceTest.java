package io.pivot.service;

import io.pivot.entity.CompagnyEntity;
import io.pivot.repository.CompagnyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CompagnyEntityServiceTest {

    @Mock
    private CompagnyRepository compagnyRepository;

    @InjectMocks
    private CompagnyService compagnyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_CompagnyWithInitialBudget_ReturnsCompagnyWithInitialBudget() {
        // Arrange
        CompagnyEntity compagnyEntity = new CompagnyEntity();
        compagnyEntity.setName("Test Company");

        // Mock repository behavior
        when(compagnyRepository.save(compagnyEntity)).thenReturn(compagnyEntity);

        // Act
        CompagnyEntity result = compagnyService.create(compagnyEntity);

        // Assert
        assertEquals(compagnyEntity, result);
        assertEquals(new BigDecimal("500000"), result.getBudget());
        verify(compagnyRepository, times(1)).save(compagnyEntity);
    }
}
