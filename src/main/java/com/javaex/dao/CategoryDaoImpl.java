package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public int createCategory(CategoryVo vo) {
		System.out.println("----> sqlSession.createCategory");
		System.out.println(sqlSession);
		
		int result = sqlSession.insert("CategoryXML.createCategory", vo);
		System.out.println(result+"건 등록");
		return result;
	}

}
