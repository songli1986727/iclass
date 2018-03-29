package com.myclass.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.myclass.entity.Course;
import com.myclass.entity.CramClass;

public interface CourseService {

	public Map<Integer,List<Course>> queryTeacherCourse(Integer teacherId,Integer year,Integer month);
	
	public Map<Integer,List<Course>> queryStudentCourse(Set<CramClass> classes,Integer year,Integer month);
	
	public void saveCourse(Course course);
	
	public void delCourse(Integer id);

}
