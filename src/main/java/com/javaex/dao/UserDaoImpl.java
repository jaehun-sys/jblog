package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int join(UserVo vo) {
		System.out.println("----> sqlSession.join");
		System.out.println(sqlSession);
		
		int result = sqlSession.insert("UserXML.join", vo);
		System.out.println(result+"건 등록");
		return result;
	}

	@Override
	public List<UserVo> loginSelect(UserVo vo) {
		System.out.println("----> sqlSession.loginSelect");
		System.out.println(sqlSession);
		
		List<UserVo> resultList = sqlSession.selectList("UserXML.loginSelect", vo);
		return resultList;
	}

	@Override
	public int updateUser(UserVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int idCheckCnt(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
