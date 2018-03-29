package com.myclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.myclass.dao.AttendanceDAO;
import com.myclass.entity.Attendance;
@Repository("attendanceDAO")
public class AttendanceDAOImpl extends BaseDAO<Attendance, Integer> implements
		AttendanceDAO<Attendance, Integer> {

}
