package io.pivot.repository;

import io.pivot.entity.Budget;
import io.pivot.entity.Compagny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagnyRepository extends JpaRepository<Compagny, Long> {

}
