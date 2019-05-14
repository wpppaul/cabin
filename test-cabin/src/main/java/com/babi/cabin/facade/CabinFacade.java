package com.babi.cabin.facade;

import java.lang.reflect.InvocationTargetException;

import com.babi.cabin.dto.CtrlCabinDto;
import com.babi.common.beans.Result;
import com.babi.domain.status.Status;


public interface CabinFacade {
	
	Result getCabinStatus(CtrlCabinDto CtrlCabinDto) throws IllegalAccessException, InvocationTargetException;
	
	
	Result getCabin(String CabinId,String color,String door);
}
