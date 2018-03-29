package com.myclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.myclass.dao.CramClassDAO;
import com.myclass.entity.CramClass;
@Repository("cramClassDAO")
public class CramClassDAOImpl extends BaseDAO<CramClass, Integer> implements
		CramClassDAO<CramClass, Integer> {

}
