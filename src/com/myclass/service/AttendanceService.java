package com.myclass.service;

import java.util.List;
import java.util.Map;

public interface AttendanceService {

	public void addAttendance(List<Map<String,Object>> attendances,Integer classId,String date,Integer courseId);
}
