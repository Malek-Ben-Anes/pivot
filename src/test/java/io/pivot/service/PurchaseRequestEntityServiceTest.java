package io.pivot.service;

import io.pivot.dto.CreatePurchaseRequest;
import io.pivot.dto.UpdatePurchaseRequest;
import io.pivot.entity.PurchaseRequestEntity;
import io.pivot.enums.PurchaseRequestStatus;
import io.pivot.exception.NotFoundException;
import io.pivot.repository.PurchaseRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PurchaseRequestEntityServiceTest {

    @Mock
    private PurchaseRequestRepository purchaseRequestRepository;

    @Mock
    private AlertService alertService;

    @InjectMocks
    private PurchaseRequestService purchaseRequestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void update_ExistingRequest_SuccessfullyUpdated() {
        // Arrange
        Long purchaseId = 1L;
        PurchaseRequestEntity existingRequest = new PurchaseRequestEntity();
        existingRequest.setId(purchaseId);
        existingRequest.setStatus(PurchaseRequestStatus.CREATED);

        PurchaseRequestEntity updatedRequest = new PurchaseRequestEntity();
        updatedRequest.setStatus(PurchaseRequestStatus.APPROVED);

        when(purchaseRequestRepository.findById(purchaseId)).thenReturn(Optional.of(existingRequest));

        // Act
        when(purchaseRequestRepository.save(any())).thenReturn(updatedRequest);
        PurchaseRequestEntity result = purchaseRequestService.patch(purchaseId, new UpdatePurchaseRequest());

        // Assert
        assertEquals(updatedRequest.getStatus(), result.getStatus());
        assertEquals(PurchaseRequestStatus.APPROVED, result.getStatus());
        verify(purchaseRequestRepository, times(1)).findById(purchaseId);
        verify(purchaseRequestRepository, times(1)).save(existingRequest);
    }

    @Test
    void update_NonExistingRequest_ExceptionThrown() {
        // Arrange
        Long purchaseId = 1L;
        PurchaseRequestEntity updatedRequest = new PurchaseRequestEntity();

        when(purchaseRequestRepository.findById(purchaseId)).thenReturn(Optional.empty());
        var updateRequest = new UpdatePurchaseRequest();

        // Act & Assert
        assertThrows(NotFoundException.class, () -> purchaseRequestService.patch(purchaseId, updateRequest));
        verify(purchaseRequestRepository, times(1)).findById(purchaseId);
        verify(purchaseRequestRepository, never()).save(any());
    }

    @Test
    void create_NewRequest_SuccessfullyCreated() {
        // Arrange
        PurchaseRequestEntity entity = new PurchaseRequestEntity();
        entity.setAmount(BigDecimal.valueOf(60000)); // This will trigger the alert
        entity.setStatus(PurchaseRequestStatus.CREATED); // This will trigger the alert

        var createPurchaseRequest = new CreatePurchaseRequest();
        createPurchaseRequest.setAmount(BigDecimal.valueOf(60000)); // This will trigger the alert

        // Act
        when(purchaseRequestRepository.save(any())).thenReturn(entity);
        PurchaseRequestEntity result = purchaseRequestService.create(createPurchaseRequest);

        // Assert
        assertEquals(PurchaseRequestStatus.CREATED, result.getStatus());
        verify(alertService, times(1)).sendAlert("Request with big amount 60000");
        verify(purchaseRequestRepository, times(1)).save(any());
    }

    @Test
    void findById_ExistingRequest_ReturnsRequest() {
        // Arrange
        Long requestId = 1L;
        PurchaseRequestEntity request = new PurchaseRequestEntity();
        request.setId(requestId);

        when(purchaseRequestRepository.findById(requestId)).thenReturn(Optional.of(request));

        // Act
        PurchaseRequestEntity result = purchaseRequestService.findById(requestId);

        // Assert
        assertEquals(request, result);
    }

    @Test
    void findById_NonExistingRequest_ExceptionThrown() {
        // Arrange
        Long requestId = 1L;

        when(purchaseRequestRepository.findById(requestId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> purchaseRequestService.findById(requestId));
    }
}
