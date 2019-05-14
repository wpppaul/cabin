package com.babi.cabin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babi.cabin.service.CabinService;
import com.babi.commom.dto.StatusDto;
import com.babi.common.utils.CabinUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api
@RestController
@RequestMapping("/Cabin")
public class CabinController {
	 
	@Autowired
	private CabinService cabinService;
	
	/**
	 * @author Tom 2018年1月25日下午7:22:38
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "消息通道")
	@PostMapping("/ctrlCabin")
	@CrossOrigin
	public String openLock(@RequestBody StatusDto statusDto) throws Exception {
		 CabinUtils.sendmessage(statusDto);	
		 String cabinString =cabinService.findbycabinId(statusDto.getCabinid());
		 cabinService.dealwithExercise(statusDto);
		return cabinString;
	}
	
	@ApiOperation(value = "删除测试")
	@PostMapping("/testCabin")
	@CrossOrigin
	public void dealwithExerciseTest(@RequestBody StatusDto statusDto) throws Exception { 
		System.out.println(statusDto);
		cabinService.dealwithExercise(statusDto);
	}

}
