package com.myclass.dao;

import java.io.Serializable;

@SuppressWarnings("hiding")
public interface UserDAO<User,Integer extends Serializable> extends GenericDao<User,Integer> {

}
