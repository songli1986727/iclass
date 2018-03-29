package com.myclass.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dao.AttendanceDAO;
import com.myclass.entity.Attendance;
import com.myclass.service.AttendanceService;
@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceDAO<Attendance,Integer> attendanceDAO;


	public void addAttendance(List<Map<String, Object>> attendances,Integer classId,String date,Integer courseId) {
		try{
			if(attendances!=null&&attendances.size()>0){
				for(Map<String,Object> att:attendances){
					Attendance attendance = new Attendance();
					attendance.setAttendanceStatus(Integer.valueOf(String.valueOf(att.get("status"))));
					attendance.setUserId(Integer.valueOf(String.valueOf(att.get("userId"))));
					attendance.setClassId(classId);
					attendance.setDate(date);
					attendance.setCourseId(courseId);
					attendanceDAO.save(attendance);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
