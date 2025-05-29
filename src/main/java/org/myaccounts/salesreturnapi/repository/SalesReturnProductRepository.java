package org.myaccounts.salesreturnapi.repository;


import org.myaccounts.salesreturnapi.entity.SalesReturnProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReturnProductRepository extends JpaRepository<SalesReturnProductEntity, Long> {

}
