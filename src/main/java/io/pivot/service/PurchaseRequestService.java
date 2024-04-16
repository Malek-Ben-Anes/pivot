package io.pivot.service;

import io.pivot.dto.CreatePurchaseRequest;
import io.pivot.dto.UpdatePurchaseRequest;
import io.pivot.enums.PurchaseRequestStatus;
import io.pivot.exception.NotFoundException;
import io.pivot.entity.PurchaseRequestEntity;
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
public class PurchaseRequestService {

    private static final BigDecimal BIG_AMOUNT_THRESHOLD = new BigDecimal(50000);

    private final PurchaseRequestRepository purchaseRequestRepository;
    private final AlertService alertService;

    public List<PurchaseRequestEntity> findAll() {
        return purchaseRequestRepository.findAll();
    }

    public PurchaseRequestEntity create(CreatePurchaseRequest request) {
        PurchaseRequestEntity purchaseRequestEntity = new PurchaseRequestEntity();
        purchaseRequestEntity.setStatus(PurchaseRequestStatus.CREATED);
        purchaseRequestEntity.setIssueDate(LocalDateTime.now());
        purchaseRequestEntity.setDescription(request.getDescription());
        sendAlert(request);
        return purchaseRequestRepository.save(purchaseRequestEntity);
    }

    public PurchaseRequestEntity patch(Long purchaseId, UpdatePurchaseRequest request) {
        var purchaseRequestEntity = purchaseRequestRepository.findById(purchaseId)
                .orElseThrow(() -> new NotFoundException("request not found" + purchaseId));

        purchaseRequestEntity.setStatus(request.getStatus());

        if (request.getStatus() == PurchaseRequestStatus.APPROVED) {
            purchaseRequestEntity.setIssueDate(LocalDateTime.now());
        }

        return purchaseRequestRepository.save(purchaseRequestEntity);
    }

    private void sendAlert(CreatePurchaseRequest request) {
        int comparisonResult = request.getAmount().compareTo(BIG_AMOUNT_THRESHOLD);

        if (comparisonResult > 0) {
            alertService.sendAlert("Request with big amount " + request.getAmount());
        }
    }

    public PurchaseRequestEntity findById(Long requestId) {
        return purchaseRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("request not found" + requestId));
    }
}
