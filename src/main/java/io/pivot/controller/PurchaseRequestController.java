package io.pivot.controller;

import io.pivot.dto.CreatePurchaseRequest;
import io.pivot.dto.UpdatePurchaseRequest;
import io.pivot.entity.PurchaseRequestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.pivot.service.PurchaseRequestService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PurchaseRequestController {

    private final PurchaseRequestService purchaseRequestService;

    @GetMapping("/purchaseRequest")
    public ResponseEntity<List<PurchaseRequestEntity>> findAll() {
        return ResponseEntity.ok(purchaseRequestService.findAll());
    }

    @GetMapping("/purchaseRequest/{requestId}")
    public ResponseEntity<PurchaseRequestEntity> findById(@PathVariable("requestId") Long requestId) {
        return ResponseEntity.ok(purchaseRequestService.findById(requestId));
    }

    @PostMapping("/purchaseRequest")
    public ResponseEntity<PurchaseRequestEntity> createPurchaseRequest(
                                            @RequestBody CreatePurchaseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseRequestService.create(request));
    }

    @PatchMapping("/purchaseRequest/{requestId}/status")
    public ResponseEntity<PurchaseRequestEntity> updatePurchaseRequestStatus(@PathVariable("requestId") Long requestId,
                                                                             @RequestBody UpdatePurchaseRequest request) {
        return ResponseEntity.ok(purchaseRequestService.patch(requestId, request));
    }
}
