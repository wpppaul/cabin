package com.babi.cabin.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.babi.domain.blood.Blood;
@Repository
@Transactional
public interface BloodRepository extends JpaRepository<Blood, Integer> {
	
	@Query(nativeQuery=true,value="select * from bloods where cabin_id=?1 order by taken DESC")
	java.util.List<Blood> findBloodsByCabinId(String CabinId);	
	
	@Modifying
	@Query(nativeQuery=true,value="delete from bloods where cabin_id=?1")
	int deleteBlood(String CabinId);
}
