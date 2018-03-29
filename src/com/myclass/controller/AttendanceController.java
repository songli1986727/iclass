package com.myclass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myclass.entity.HomeWork;
import com.myclass.entity.User;
import com.myclass.service.AttendanceService;
import com.myclass.service.HomeWorkService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private HomeWorkService homeWorkService;
	
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String,String> saveAttendances(HttpSession session,@RequestBody List<Map<String,Object>> attendances,Integer classId,String date,Integer courseId){
		Map<String,String> mes = new HashMap<String,String>();
		try{
			Map<String,Object> content = attendances.get(attendances.size()-1);
			attendances.remove(attendances.size()-1);
			attendanceService.addAttendance(attendances,classId,date,courseId);
			User user =(User) session.getAttribute("user");
			HomeWork homework = new HomeWork();
			homework.setClassId(classId);
			homework.setCourseId(courseId);
			homework.setDate(date);
			homework.setUserId(user.getId());
			homework.setContent(String.valueOf(content.get("homework")));
			homeWorkService.addHomeWork(homework);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mes;
	}

}
