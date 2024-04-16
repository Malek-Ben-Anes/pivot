package io.pivot.repository;

import io.pivot.entity.CompagnyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagnyRepository extends JpaRepository<CompagnyEntity, Long> {

}
