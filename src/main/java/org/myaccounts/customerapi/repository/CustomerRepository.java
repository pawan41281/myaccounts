package org.myaccounts.customerapi.repository;

import java.util.List;

import org.myaccounts.customerapi.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	public List<CustomerEntity> findByNameIgnoreCase(String value);

	public List<CustomerEntity> findByCityIgnoreCase(String value);

	public List<CustomerEntity> findByStateIgnoreCase(String value);

	public List<CustomerEntity> findBymobileIgnoreCase(String value);

	public List<CustomerEntity> findByemailIgnoreCase(String value);

	public List<CustomerEntity> findByGstnoIgnoreCase(String value);

	public List<CustomerEntity> findByNameIgnoreCaseOrCityIgnoreCaseOrStateIgnoreCaseOrMobileOrEmailIgnoreCaseOrGstnoIgnoreCase(
			String name, String city, String state, String mobile, String email, String gstno);

}
