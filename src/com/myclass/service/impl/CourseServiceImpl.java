package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dao.CourseDAO;
import com.myclass.entity.Course;
import com.myclass.entity.CramClass;
import com.myclass.service.CourseService;
@Service("courseService")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDAO<Course, Integer> courseDAO;

	public Map<Integer, List<Course>> queryTeacherCourse(Integer teacherId,Integer year,
			Integer month) {
		Map<Integer, List<Course>> courseMap = new HashMap<Integer, List<Course>> ();
		try{
			Course course = new Course();
			course.setTeacherId(teacherId);
			course.setMonth(month);
			course.setYear(year);
			List<Course> courseList = courseDAO.findByExample(Course.class, course, null, null);
			if(courseList!=null&&courseList.size()>0){
				for(Course cour:courseList){
					if(courseMap.containsKey(cour.getDay())){
						courseMap.get(cour.getDay()).add(cour);
					}else{
						List<Course> courList = new ArrayList<Course>();
						courList.add(cour);
						courseMap.put(cour.getDay(), courList);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courseMap;
	}
	
	public Map<Integer,List<Course>> queryStudentCourse(Set<CramClass> classes,Integer year,Integer month){
		Map<Integer, List<Course>> courseMap = new HashMap<Integer, List<Course>> ();
		try{
			if(classes!=null&&classes.size()>0){
				for(CramClass clas:classes){
					Course course = new Course();
					course.setClassId(clas.getId());
					course.setMonth(month);
					course.setYear(year);
					List<Course> courseList = courseDAO.findByExample(Course.class, course, null, null);
					if(courseList!=null&&courseList.size()>0){
						for(Course cour:courseList){
							if(courseMap.containsKey(cour.getDay())){
								courseMap.get(cour.getDay()).add(cour);
							}else{
								List<Course> courList = new ArrayList<Course>();
								courList.add(cour);
								courseMap.put(cour.getDay(), courList);
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courseMap;
	}

	@Transactional
	public void saveCourse(Course course){
		try{
			courseDAO.save(course);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void delCourse(Integer id){
		try{
			courseDAO.delete(Course.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
