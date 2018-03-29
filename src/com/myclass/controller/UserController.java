package com.myclass.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myclass.entity.CramClass;
import com.myclass.entity.Memo;
import com.myclass.entity.Notice;
import com.myclass.entity.User;
import com.myclass.service.CramClassService;
import com.myclass.service.MemoService;
import com.myclass.service.NoticeService;
import com.myclass.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private CramClassService cramClassService;
	@Autowired
	private MemoService memoService;
	
	@RequestMapping("/login")
	public @ResponseBody Map<String, String> login(String account,String password,HttpSession session){
		LOGGER.info("查询用户：" + account+","+password); 
		Map<String,String> map= new HashMap<String,String>();
		if(account==null||password==null){
			map.put("msg", "error");
			return map;
		} 
	    User userInfo = userService.checkUserLogin(account, password);	        
	    if(userInfo==null){
	    	map.put("msg", "error");
			return map;
	    }	       
	    session.setAttribute("user", userInfo);
	    map.put("msg", "success");
	    if(userInfo.getUserType()==1){    	
	    	map.put("url", "user/teacher.htmls");
	    }else{
	    	map.put("url", "user/student.htmls");
	    }
	    return map;	    
	}
		
	@RequestMapping("/student")
	public String index(ModelMap modelMap,HttpSession session){
		 User user = (User)session.getAttribute("user");
		 List<Notice> noticeList = noticeService.queryByUserNotices(user.getClasses(),1,3);
		 modelMap.addAttribute("noticeList", noticeList);
		 return "/student_index";  
	}
	
	@RequestMapping("/toEdit")
	public String toEditStudent(ModelMap modelMap,HttpSession session){
		User user = (User)session.getAttribute("user");
		List<CramClass> cramClassList = cramClassService.queryByTeacherId(user.getId());
		modelMap.addAttribute("classList", cramClassList);
		return "/user_edit";
	}
	
	@RequestMapping("/teacher")
	public String toTeacherIndex(ModelMap modelMap,HttpSession session){
		User user = (User)session.getAttribute("user");
		List<Memo> memoList = memoService.queryByUserId(user.getId(),1);
		modelMap.addAttribute("memoList",memoList);
		return "/teacher_index";
	}
	
	@RequestMapping("/saveStudent")
	public @ResponseBody Map<String, Integer> saveStudent(String userName,String phone){
		Map<String,Integer> map= new HashMap<String,Integer>();
		try{
			User user = new User();
			user.setUserName(userName);
			user.setUserType(0);
			user.setPhone(phone);
	        Integer result = userService.saveUser(user);
	        map.put("result", result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/studentList")
	public String studentList(ModelMap modelMap){
		List<User> userList = userService.queryAllStudents();
		List<CramClass> classList = cramClassService.queryByAll();
		modelMap.addAttribute("userList", userList);
		modelMap.addAttribute("classList", classList);
		modelMap.addAttribute("userType", 0);
		return "/user_list";
	}

	@RequestMapping("/teacherList")
	public String teacherList(ModelMap modelMap){
		List<User> userList = userService.queryAllTeacher();
		List<CramClass> classList = cramClassService.queryByAll();
		modelMap.addAttribute("userList", userList);
		modelMap.addAttribute("classList", classList);
		modelMap.addAttribute("userType", 1);
		return "/user_list";
	}
	
	@RequestMapping("/classStudent")
	public @ResponseBody Set<User> classStudent(Integer classId){
		Set<User> students= new HashSet<User>();
		try{
			CramClass clas = cramClassService.queryById(classId);
			for(User user:clas.getUsers()){
				User student = new User();
				student.setId(user.getId());
				student.setUserName(user.getUserName());
				students.add(student);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return students;
	}
}