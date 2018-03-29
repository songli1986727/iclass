package com.myclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.myclass.dao.CourseDAO;
import com.myclass.entity.Course;
@Repository("courseDAO")
public class CourseDAOImpl extends BaseDAO<Course, Integer> implements
		CourseDAO<Course, Integer> {

}
