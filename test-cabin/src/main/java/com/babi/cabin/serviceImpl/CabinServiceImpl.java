package com.babi.cabin.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.babi.cabin.repository.BloodRepository;
import com.babi.cabin.repository.CabinRepository;
import com.babi.cabin.repository.ExerciseRepository;
import com.babi.cabin.repository.WeightRepository;
import com.babi.cabin.service.CabinService;
import com.babi.commom.dto.StatusDto;
import com.babi.domain.blood.Blood;
import com.babi.domain.cabin.Cabin;
import com.babi.domain.exercise.Exercise;
import com.babi.domain.weight.Weight;

@Service
public class CabinServiceImpl implements CabinService {
	
	@Autowired
	private CabinRepository cabinRepository;
		
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private BloodRepository bloodRepository;
	
	@Autowired
	private WeightRepository weightRepository;
	
	@Override
	public String findbycabinId(String cabinid) {
		HashMap<String,Object> map =new HashMap<>();
		//查询仓数据返回
		Cabin cabin = cabinRepository.findCabinById(cabinid);
		map.put("CabinId", cabin.getCabinId());
		map.put("Door", cabin.getDoor().toString());
		map.put("Temp", cabin.getTemperature().toString());
		map.put("PM2.5", cabin.getAqi().toString());
		map.put("Light", cabin.getLight().toString());
		map.put("Color", cabin.getColor().toString());
		map.put("EMGStop", cabin.getEmergencyStop().toString());
		map.put("EMGCall", cabin.getEmergencyCall().toString());
		map.put("Location", cabin.getLocation());
		map.put("Online", cabin.getOnline());
		Exercise exercise = exerciseRepository.findCabinById(cabinid);
		if (exercise!=null) {
			map.put("Dur", exercise.getDuration().toString());
			map.put("Dis", exercise.getDistance().toString());
			map.put("Cal", exercise.getCalorie());
			map.put("Maxspd", exercise.getMaxspd().toString());
		} else {
			map.put("Dur", "");
			map.put("Dis", "");
			map.put("Cal", "");
			map.put("Maxspd", "");
		}
		//查询血压数据并返回
		List<Blood> blood = bloodRepository.findBloodsByCabinId(cabinid);
		if (blood.size()>0) {
			List<String> bloods = new ArrayList<>();
			blood.forEach( b -> {
				HashMap<String,String> bloodmap = new HashMap<String,String>();
				bloodmap.put("sys", b.getSystolic().toString());
				bloodmap.put("dia", b.getDiastolic().toString());
				bloodmap.put("pul", b.getPulse().toString());
				bloodmap.put("ts", b.getTaken());
				bloods.add(JSON.toJSONString(bloodmap));
			});
			map.put("Blood", bloods);			
		}else{
			map.put("Blood", new ArrayList<String>());
		}
		//查询体重，电阻数据返回
		List<Weight> weight = weightRepository.findWeightsByCabinId(cabinid);
		if (weight.size()>0) {
			List<String> weights = new ArrayList<>();
			weight.forEach( w -> {
				HashMap<String,String> weightmap = new HashMap<String,String>();
				weightmap.put("wei",String.valueOf(w.getWeight()));
				weightmap.put("res",String.valueOf(w.getRes()));
				weightmap.put("ts",w.getTaken());
				weights.add(JSON.toJSONString(weightmap));
			});
			map.put("Weight", weights);			
		} else {
			map.put("Weight", new ArrayList<String>());
		}
		return JSON.toJSONString(map);
	}

	@Override
	public void dealwithExercise(StatusDto statusdto){
		String Time =  statusdto.getTime();
		System.out.println(Time);
		if(Time!=null&&Time.length()>0&&Integer.parseInt(Time)==0){
			
			String CabinId = statusdto.getCabinid();
			
			exerciseRepository.deleteExercise(statusdto.getCabinid());
			exerciseRepository.flush();
			
			weightRepository.deleteWeight(CabinId);
			weightRepository.flush();
			
			bloodRepository.deleteBlood(CabinId);
			bloodRepository.flush();
		}else if(Time!=null&&Time.length()>0&&Integer.parseInt(Time)!=0){
			
			String CabinId = statusdto.getCabinid();
			
			weightRepository.deleteWeight(CabinId);
			weightRepository.flush();
			
			bloodRepository.deleteBlood(CabinId);
			bloodRepository.flush();
		}
	}

}
