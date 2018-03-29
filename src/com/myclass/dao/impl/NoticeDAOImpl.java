package com.myclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.myclass.dao.NoticeDAO;
import com.myclass.entity.Notice;
@Repository("noticeDAO")
public class NoticeDAOImpl extends BaseDAO<Notice, Integer> implements
		NoticeDAO<Notice, Integer> {

}
