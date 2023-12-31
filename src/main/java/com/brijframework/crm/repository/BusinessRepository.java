package com.brijframework.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brijframework.crm.entities.EOBusiness;

@Repository
@Transactional
public interface BusinessRepository extends JpaRepository<EOBusiness, Long>{

	@Query(nativeQuery = true , value = " SELECT * FROM EOBUSINESS WHERE VENDOR_ID=?1 and RECORD_STATUS=?2")
	Optional<List<EOBusiness>> findByVendorId(Long vendorId, String recStat);

}
