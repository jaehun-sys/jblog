package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDaoImpl implements BlogDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int createBlog(BlogVo vo) {
		System.out.println("----> sqlSession.createBlog");
		System.out.println(sqlSession);
		
		int result = sqlSession.insert("BlogXML.createBlog", vo);
		System.out.println(result+"건 등록");
		return result;
	}
	
}
