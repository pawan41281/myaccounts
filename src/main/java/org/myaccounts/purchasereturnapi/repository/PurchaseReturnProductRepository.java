package org.myaccounts.purchasereturnapi.repository;

import org.myaccounts.purchasereturnapi.entity.PurchaseReturnProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseReturnProductRepository extends JpaRepository<PurchaseReturnProductEntity, Long> {

}
