package com.babi.cabin.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.babi.domain.weight.Weight;
@Repository
@Transactional
public interface WeightRepository extends JpaRepository<Weight, Integer> {
	
	@Query(nativeQuery=true,value="select * from weights where cabin_id=?1 order by taken DESC")
	java.util.List<Weight> findWeightsByCabinId(String CabinId);	
	
	@Modifying
	@Query(nativeQuery=true,value="delete from weights where cabin_id=?1")
	int deleteWeight(String CabinId);
}
