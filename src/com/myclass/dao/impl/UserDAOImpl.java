package com.myclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.myclass.dao.UserDAO;
import com.myclass.entity.User;
@Repository("userDAO")
public class UserDAOImpl extends BaseDAO<User, Integer> implements
		UserDAO<User, Integer> {

}
