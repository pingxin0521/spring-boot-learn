package com.hyp.learn.w.service;

import com.hyp.learn.w.model.DemoInfo;

import java.util.List;

/**
 * 
 * @author timebusker
 * 
 * 2017年4月7日
 * 
 * DemoInfoService 接口类
 */
public interface DemoInfoService {

	List<DemoInfo> insertDemoInfo(DemoInfo demo);
	
	DemoInfo findDemoInfo(String id);
	
	List<DemoInfo> updateDemoInfo(DemoInfo demo);
	
	List<DemoInfo> deleteDemoInfo(DemoInfo demo);
	
	List<DemoInfo> findAll();
}
