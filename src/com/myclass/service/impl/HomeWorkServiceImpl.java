package com.myclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dao.HomeWorkDAO;
import com.myclass.entity.HomeWork;
import com.myclass.service.HomeWorkService;
@Service("homeWorkService")
public class HomeWorkServiceImpl implements HomeWorkService {
	@Autowired
	private HomeWorkDAO<HomeWork,Integer> homeWorkDAO;

	public void addHomeWork(HomeWork homeWork) {
		try{			
			homeWorkDAO.save(homeWork);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
