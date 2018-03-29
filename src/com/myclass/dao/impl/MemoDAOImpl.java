package com.myclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.myclass.dao.MemoDAO;
import com.myclass.entity.Memo;
@Repository("memoDAO")
public class MemoDAOImpl extends BaseDAO<Memo, Integer> implements MemoDAO<Memo, Integer> {

}
