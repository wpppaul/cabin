package com.babi.cabin.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.babi.domain.exercise.Exercise;
@Repository
@Transactional
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
	
	@Query(nativeQuery=true,value="select * from exercises where cabin_id=?1")
	Exercise findCabinById(String CabinId);	
	
	@Modifying
	@Query(nativeQuery=true,value="delete from exercises where cabin_id=?1")
	int deleteExercise(String CabinId);
}
