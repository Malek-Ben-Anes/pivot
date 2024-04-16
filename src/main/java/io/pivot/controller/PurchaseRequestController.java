package io.pivot.controller;

import io.pivot.model.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.pivot.service.PurchaseRequestService;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class PurchaseRequestController {

    private final PurchaseRequestService purchaseRequestService;

    @GetMapping("/purchaseRequest")
    public ResponseEntity<List<PurchaseRequest>> findAll() {
        return ResponseEntity.ok(purchaseRequestService.findAll());
    }

    @GetMapping("/purchaseRequest/{requestId}")
    public ResponseEntity<PurchaseRequest> findById(@PathVariable("requestId") Long requestId) {
        return ResponseEntity.ok(purchaseRequestService.findById(requestId));
    }

    @PostMapping("/purchaseRequest")
    public ResponseEntity<PurchaseRequest> createPurchaseRequest(
                                            @RequestBody PurchaseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseRequestService.create(request));
    }

    @PatchMapping("/purchaseRequest/{requestId}")
    public ResponseEntity<PurchaseRequest> updatePurchaseRequestStatus(@PathVariable("requestId") Long requestId,
                                                     @RequestBody PurchaseRequest request) {
        return ResponseEntity.ok(purchaseRequestService.update(requestId, request));
    }
}
