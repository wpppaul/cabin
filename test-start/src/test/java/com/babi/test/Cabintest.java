package com.babi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.babi.cabin.serviceImpl.CabinServiceImpl;
import com.babi.start.Start;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Start.class)
public class Cabintest {
	
	@Autowired
	@Qualifier("cabinServiceImpl")
	private CabinServiceImpl cabinService;
	@Test
	public void test(){
		String string = cabinService.findbycabinId("123456");
		System.out.println(string);
	}

}
