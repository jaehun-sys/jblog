package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaex.dao.BlogDao;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	private BlogDao blogDao;
	
	@Autowired
	public BlogController(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	@GetMapping("/{id}")
	public String myBlog(@PathVariable String id, HttpSession session) {
		System.out.println(">>> "+this.getClass()+" 호출됨! myBlog");
		System.out.println();
		System.out.println(((UserVo)session.getAttribute("authUser")).toString());
		
		if(id.equals(((UserVo) session.getAttribute("authUser")).getId())){
			
		}else {
			
		}
		return "blog/blog-main";
	}
	
	
}
