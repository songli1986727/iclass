package com.myclass.service;

import java.util.List;
import com.myclass.entity.User;

public interface UserService {

    public User checkUserLogin(String account,String password);
	
	public User registerUser(User user);
	
	public List<User> queryByClassId(Integer classId);	
	
	public List<User> queryAllStudents();

	public List<User> queryAllTeacher();
	
	public User queryById(String id);
	
	public User queryByName(String userName);
	
	public Integer saveUser(User user);
}
