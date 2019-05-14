package com.babi.cabin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.babi.domain.cabin.Cabin;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, String> {
	
	@Query(nativeQuery=true,value="select * from cabins where id=?1")
	Cabin findCabinById(String cabinId);
}
