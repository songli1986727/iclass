package com.myclass.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myclass.entity.Course;
import com.myclass.entity.CramClass;
import com.myclass.entity.User;
import com.myclass.service.CourseService;
import com.myclass.service.CramClassService;
import com.myclass.util.DateUtil;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private CramClassService cramClassService;
	
	@RequestMapping("/teacherCourses")
	public @ResponseBody Map<Integer, List<Course>> teacherCourses(Integer year,Integer month,HttpSession session){
		Map<Integer, List<Course>> courseMap = new HashMap<Integer,List<Course>>();
		User user = (User)session.getAttribute("user");
		courseMap = courseService.queryTeacherCourse(user.getId(),year,month);
		return courseMap;
	}
	
	@RequestMapping("/studentCourses")
	public @ResponseBody Map<Integer, List<Course>> studentCourses(Integer year,Integer month,HttpSession session){
		Map<Integer, List<Course>> courseMap = new HashMap<Integer,List<Course>>();
		User user = (User)session.getAttribute("user");
		courseMap = courseService.queryStudentCourse(user.getClasses(),year,month);
		return courseMap;
	}
	
	@RequestMapping("/calendar")
	public String getCalendar(HttpSession session,Integer year,Integer month,ModelMap modelMap){
		if(year==null||month==null){
			year = DateUtil.getCurrentDate("year");
			month = DateUtil.getCurrentDate("month");
		}
		Integer monthDays = DateUtil.getDayOfMonth(year,month);
        String date = String.valueOf(year)+"-"+String.valueOf(month<10?"0"+String.valueOf(month):month)+"-01";
		Integer weekDay = DateUtil.getWeekOfDate(date);
		
		List<CramClass> classList = new ArrayList<CramClass>();
		User user = (User)session.getAttribute("user");
		classList  = cramClassService.queryByTeacherId(user.getId());
		
		Integer day = DateUtil.getCurrentDate("day");
		
		modelMap.addAttribute("year", year);
		modelMap.addAttribute("month", month);
		modelMap.addAttribute("day", day);
		modelMap.addAttribute("weekDay", weekDay);
		modelMap.addAttribute("monthDays", monthDays);
		modelMap.addAttribute("classList", classList);
		if(user.getUserType()==0) return "/student_course";
		return "/test";
	}
	
	@RequestMapping("/saveCourse")
	public @ResponseBody Map<String,String> saveCourse(HttpSession session,Integer year,Integer month,Integer day,String startTime,String endTime,Integer cramClassId,String cramClassName){
		Map<String,String> map = new HashMap<String,String>();
		User user = (User)session.getAttribute("user");
		Course course = new Course();
		course.setYear(year);
		course.setMonth(month);
		course.setDay(day);
		course.setStartTime(startTime);
		course.setEndTime(endTime);
		course.setClassId(cramClassId);
		try {
			course.setClassName(URLDecoder.decode(cramClassName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		course.setTeacherId(user.getId());
		course.setTeacherName(user.getUserName());
		courseService.saveCourse(course);		
		return map;
	}
	

	@RequestMapping("/delCourse")
	public @ResponseBody Map<String,String> delCourses(Integer id){
		Map<String,String> map = new HashMap<String,String>();
		courseService.delCourse(id);
		return map;
	}
}
