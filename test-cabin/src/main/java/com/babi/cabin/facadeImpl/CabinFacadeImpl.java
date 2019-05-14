package com.babi.cabin.facadeImpl;

import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babi.cabin.dto.CtrlCabinDto;
import com.babi.cabin.facade.CabinFacade;
import com.babi.cabin.service.CabinService;
import com.babi.common.beans.Result;
import com.babi.common.constant.CodeEnum;
import com.babi.common.utils.CabinUtils;
import com.babi.domain.status.Status;

@Service
public class CabinFacadeImpl implements CabinFacade {
	@Autowired
	private CabinService cabinService;

	@Override
	public Result getCabinStatus(CtrlCabinDto CtrlCabinDto){
//		Status status=new Status();	
//		BeanUtils.copyProperties(CtrlCabinDto, status);
////		BeanUtils
//		
//		if (!CabinUtils.openLock(CtrlCabinDto.getCabinId())) {
//			return Result.setCodeMsg(CodeEnum.ORDER_OPEN_FAIL);
//		}
//		Status sta=new Status();
//		sta.setDoor(CtrlCabinDto.getDoor());
//		sta.setColor(CtrlCabinDto.getColor());
//		cabinService.save(sta);
//		
//		List<Status> list = cabinService.queryByCabinId(CtrlCabinDto.getCabinId());	
//		System.out.println(CtrlCabinDto.getCabinId());
//		if (list.size() > 0) {
//			List<Map<String, Object>> statu = new ArrayList<>();
//			for (Status stat : list) {
//				Map<String, Object> maps = new HashMap<>();
//				maps.put("CabinID",stat.getCabinid() );
//				maps.put("Door",stat.getDoor());
//				maps.put("Light", stat.getLight());
//				maps.put("CabinPM2.5",stat.getCabinPM());
//				maps.put("CabinTemperature",stat.getTemperatue());
//				maps.put("Color",stat.getColor());
//				maps.put("Energy",stat.getTemperatue());
//				maps.put("EmergencyStop",stat.getEmergencyStop());
//				maps.put("EmergencyCall",stat.getEmergencyCall());
//				maps.put("Dur",stat.getDur());
//				maps.put("Dis",stat.getDis());
//				maps.put("Cal",stat.getCal());
//				statu.add(maps);	
//			}					
//			return Result.setCodeMsgData("SUCCESS", "Description", "status", statu);
//		}
//		return Result.setCodeMsg("SYS_PARAM_ERROR", "Description");
//
//	}
//
//	@Override
//	public Result getCabin(String CabinId, String color, String door) {
//		
		return null;
	}

	@Override
	public Result getCabin(String CabinId, String color, String door) {
		// TODO Auto-generated method stub
		return null;
	}

}
