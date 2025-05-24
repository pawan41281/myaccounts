package org.myaccounts.salesapi.repository;

import org.myaccounts.salesapi.entity.SalesProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalesProductRepository extends JpaRepository<SalesProductEntity, Long> {

}
