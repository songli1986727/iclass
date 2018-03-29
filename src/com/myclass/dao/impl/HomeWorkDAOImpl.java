package com.myclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.myclass.dao.HomeWorkDAO;
import com.myclass.entity.HomeWork;
@Repository("homeWorkDAO")
public class HomeWorkDAOImpl extends BaseDAO<HomeWork, Integer> implements
		HomeWorkDAO<HomeWork, Integer> {

}
