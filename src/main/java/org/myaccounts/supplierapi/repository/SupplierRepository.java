package org.myaccounts.supplierapi.repository;

import java.util.List;

import org.myaccounts.supplierapi.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

	public List<SupplierEntity> findByNameIgnoreCase(String value);

	public List<SupplierEntity> findByCityIgnoreCase(String value);

	public List<SupplierEntity> findByStateIgnoreCase(String value);

	public List<SupplierEntity> findBymobileIgnoreCase(String value);

	public List<SupplierEntity> findByemailIgnoreCase(String value);

	public List<SupplierEntity> findByGstnoIgnoreCase(String value);

	public List<SupplierEntity> findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
			String name, String city, String state, String mobile, String email, String gstno);

}
