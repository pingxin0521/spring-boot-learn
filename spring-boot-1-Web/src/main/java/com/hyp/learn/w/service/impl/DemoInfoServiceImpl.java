package com.hyp.learn.w.service.impl;

import com.hyp.learn.w.dao.DemoInfoDAO;
import com.hyp.learn.w.model.DemoInfo;
import com.hyp.learn.w.service.DemoInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoInfoServiceImpl implements DemoInfoService {

	@Resource
    DemoInfoDAO dao;

	@Override
	public List<DemoInfo> insertDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.insertDemoInfo(demo);
	}

	@Override
	public DemoInfo findDemoInfo(String id) {
		// TODO Auto-generated method stub
		return dao.findDemoInfo(id);
	}

	@Override
	public List<DemoInfo> updateDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.updateDemoInfo(demo);
	}

	@Override
	public List<DemoInfo> deleteDemoInfo(DemoInfo demo) {
		// TODO Auto-generated method stub
		return dao.deleteDemoInfo(demo);
	}

	@Override
	public List<DemoInfo> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
