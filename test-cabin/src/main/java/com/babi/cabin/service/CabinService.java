package com.babi.cabin.service;

import com.babi.commom.dto.StatusDto;
import com.babi.domain.exercise.Exercise;

public interface CabinService {
	
	String findbycabinId(String cabinid);
	
	void dealwithExercise(StatusDto statusdto);
	
}
