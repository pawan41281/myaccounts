package org.myaccounts.purchaseapi.repository;

import org.myaccounts.purchaseapi.entity.PurchaseProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProductEntity, Long> {

}
