package com.myclass.service;

import java.util.List;
import com.myclass.entity.CramClass;

public interface CramClassService {

	public List<CramClass> queryByTeacherId(Integer teacherId);
	
	public List<CramClass> queryByAll();
	
	public CramClass queryById(Integer id);
	
	public void saveCramClass(CramClass cramClass);
	
	public void deleteClass(Integer id);
}
