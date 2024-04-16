package io.pivot.repository;

import io.pivot.entity.PurchaseRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequestEntity, Long> {

}
