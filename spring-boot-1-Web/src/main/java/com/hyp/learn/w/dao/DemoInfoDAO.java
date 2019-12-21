package com.hyp.learn.w.dao;

import com.hyp.learn.w.model.DemoInfo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DemoInfoDAO {

	// public static List<DemoInfo> list = new ArrayList<DemoInfo>();

	// 创建线程安全的Map
	public static Map<String, DemoInfo> map = Collections.synchronizedMap(new HashMap<String, DemoInfo>());

	static {
		for (int i = 0; i < 10; i++) {
			DemoInfo demoInfo = new DemoInfo();
			demoInfo.setId(++i + "");
			demoInfo.setName("mine_" + i);
			demoInfo.setDescription("我是第" + i + "个同学！");
			map.put(i + "", demoInfo);
		}
	}

	public List<DemoInfo> insertDemoInfo(DemoInfo demo) {
		map.put(demo.getId(), demo);
		List<DemoInfo> list = getListDemoInfo(map);
		return list;
	}

	public DemoInfo findDemoInfo(String id) {
		DemoInfo demo = map.get(id);
		return demo;
	}

	public List<DemoInfo> updateDemoInfo(DemoInfo demo) {
		map.replace(demo.getId(), demo);
		List<DemoInfo> list = getListDemoInfo(map);
		return list;
	}

	public List<DemoInfo> deleteDemoInfo(DemoInfo demo) {
		map.remove(demo.getId());
		List<DemoInfo> list = getListDemoInfo(map);
		return list;
	}
	
	public List<DemoInfo> findAll() {
		List<DemoInfo> list = getListDemoInfo(map);
		return list;
	}
	
	private List<DemoInfo> getListDemoInfo(Map<String, DemoInfo> map) {
		Set<String> set = map.keySet();
		List<DemoInfo> list = new ArrayList<DemoInfo>();
		for (String string : set) {
			DemoInfo demo = map.get(string);
			list.add(demo);
		}
		return list;
	}
}
