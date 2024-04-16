package io.pivot.service;

import io.pivot.enums.PurchaseRequestStatus;
import io.pivot.exception.NotFoundException;
import io.pivot.model.PurchaseRequest;
import io.pivot.repository.PurchaseRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PurchaseRequestService {
    private final PurchaseRequestRepository purchaseRequestRepository;

    public List<PurchaseRequest> findAll() {
        return purchaseRequestRepository.findAll();
    }

    public PurchaseRequest update(Long requestId, PurchaseRequest request) {
        var existingRequest = purchaseRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("request not found" + requestId));

        existingRequest.setStatus(request.getStatus());
        existingRequest.setIssueDate(LocalDateTime.now());

        if (request.getStatus() == PurchaseRequestStatus.DECLINED) {
            log.warn("Request has been declined ", requestId);
        }

        return purchaseRequestRepository.save(existingRequest);
    }

    public PurchaseRequest create(PurchaseRequest request) {
        request.setId(null);
        return purchaseRequestRepository.save(request);
    }

    public PurchaseRequest findById(Long requestId) {
        return purchaseRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("request not found" + requestId));
    }
}
