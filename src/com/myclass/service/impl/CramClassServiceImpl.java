package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dao.CramClassDAO;
import com.myclass.entity.CramClass;
import com.myclass.service.CramClassService;
@Service("cramClassService")
public class CramClassServiceImpl implements CramClassService {
	@Autowired
	private CramClassDAO<CramClass,Integer> cramClassDAO;

	public List<CramClass> queryByTeacherId(Integer teacherId){
		List<CramClass> cramClassList = new ArrayList<CramClass>();
		try{
			cramClassList = cramClassDAO.findByProperty(CramClass.class, "teacherId", teacherId, "id", "desc");
		}catch(Exception e){
			e.printStackTrace();
		}
		return cramClassList;
	}
	
	public List<CramClass> queryByAll(){
		return cramClassDAO.findAll(CramClass.class, "id", "desc");
	}
	
	public CramClass queryById(Integer id){
		try{
			return cramClassDAO.get(CramClass.class, id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public void saveCramClass(CramClass cramClass){
		try{
			cramClassDAO.save(cramClass);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void deleteClass(Integer id){
		cramClassDAO.delete(CramClass.class, id);
	}
}
