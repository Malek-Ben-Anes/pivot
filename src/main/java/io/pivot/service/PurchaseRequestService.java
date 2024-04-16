package io.pivot.service;

import io.pivot.enums.PurchaseRequestStatus;
import io.pivot.exception.NotFoundException;
import io.pivot.model.PurchaseRequest;
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

    private static final BigDecimal BIG_AMOUNT_THRESHOLD = new BigDecimal("50000");
    private final PurchaseRequestRepository purchaseRequestRepository;
    private final AlertService alertService;

    public List<PurchaseRequest> findAll() {
        return purchaseRequestRepository.findAll();
    }

    public PurchaseRequest update(Long purchaseId, PurchaseRequest request) {
        var existingRequest = purchaseRequestRepository.findById(purchaseId)
                .orElseThrow(() -> new NotFoundException("request not found" + purchaseId));

        checkRequest(purchaseId, request);

        existingRequest.setStatus(request.getStatus());

        if (request.getStatus() == PurchaseRequestStatus.APPROVED) {
            existingRequest.setIssueDate(LocalDateTime.now());
        }

        return purchaseRequestRepository.save(existingRequest);
    }

    public String checkRequest(Long requestId, PurchaseRequest request) {
        int comparisonResult = request.getAmount().compareTo(BIG_AMOUNT_THRESHOLD);

        if (comparisonResult > 0) {
            alertService.sendAlert("Request with big amount " + requestId);
            return "Alert message: ";
        }
        return "standard request";
    }

    public PurchaseRequest create(PurchaseRequest request) {
        request.setId(null);
        request.setStatus(PurchaseRequestStatus.CREATED);
        request.setIssueDate(LocalDateTime.now());
        return purchaseRequestRepository.save(request);
    }

    public PurchaseRequest findById(Long requestId) {
        return purchaseRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("request not found" + requestId));
    }
}
