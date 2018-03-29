package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dao.UserDAO;
import com.myclass.entity.User;
import com.myclass.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO<User,Integer> userDAO;

	public User checkUserLogin(String account, String password) {
		try{
			List<User> userList = userDAO.findByProperty(User.class, "account", account, null, null);
			if(userList!=null&&userList.size()==1){
				User user = userList.get(0);
				if(user.getPassword().equals(password)){
					return user;
				}
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> queryByClassId(Integer classId) {
		// TODO Auto-generated method stub
		return null;
	}

	public User queryById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User queryByName(String userName) {
		try{
			List<User> userList = userDAO.findByProperty(User.class, "userName", userName, null, null);
			if(userList!=null&&userList.size()==1){
				return userList.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer saveUser(User user){
		try{
			User userTemp = new User();
			//userTemp.setAccount(user.getAccount());
			userTemp.setUserName(user.getUserName());
			//userTemp.setUserType(user.getUserType());
			List<User> userList = userDAO.findByExample(User.class, userTemp, null, null);
			if(userList!=null&&userList.size()>0) return 0;
			userDAO.save(user);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}	

	public List<User> queryAllStudents(){
		List<User> userList = new ArrayList<User>();
		try{
			userList = userDAO.findByProperty(User.class, "userType", 0, "id", "desc");
		}catch(Exception e){
			e.printStackTrace();
		}
		return userList;
	}

	public List<User> queryAllTeacher(){
		List<User> userList = new ArrayList<User>();
		try{
			userList = userDAO.findByProperty(User.class, "userType", 1, "id", "desc");
		}catch(Exception e){
			e.printStackTrace();
		}
		return userList;
	}
}
