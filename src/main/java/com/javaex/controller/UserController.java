package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	
	private UserDao userDao;
	private BlogDao blogDao;
	private CategoryDao categoryDao;
	
	@Autowired
	public UserController(UserDao userDao, BlogDao blogDao, CategoryDao categoryDao) {
		this.userDao = userDao;
		this.blogDao = blogDao;
		this.categoryDao = categoryDao;
	}
	
	@GetMapping("/user/join")
	public String join() {
		System.out.println(">>> "+this.getClass()+" 호출됨! join");
		System.out.println();
		return "user/joinForm";
	}
	
	@PostMapping("/user/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(">>> "+this.getClass()+" 호출됨! joinCheck");
		System.out.println();
		System.out.println("join userVo: "+userVo);
		if(userVo.getUserName().equals("")||userVo.getId().equals("")||userVo.getPassword().equals("")) {
			System.out.println("회원가입 fail!");
			return "redirect:/user/joinForm";
		}else {
			userDao.join(userVo);
			System.out.println("회원가입 success!");
			
			BlogVo blogVo = new BlogVo();
			blogVo.setId(userVo.getId());
			blogVo.setBlogTitle(userVo.getUserName()+"님의 블로그");
			blogVo.setLogoFile(userVo.getUserName()+"의 로고파일");
			blogDao.createBlog(blogVo);
			System.out.println("블로그 생성 success!");
			
			CategoryVo categoryVo = new CategoryVo();
			categoryVo.setId(userVo.getId());
			categoryDao.createCategory(categoryVo);
			System.out.println("카테고리 생성 success!");
			return "user/joinSuccess";
		}		
	}
	
	@GetMapping("/user/login")
	String loginform() {
		System.out.println(">>> "+this.getClass()+" 호출됨! login");
		System.out.println();
		return "user/loginForm";
	}
	
	@PostMapping("/user/logincheck")
	String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println(">>> "+this.getClass()+" 호출됨! logincheck");
		System.out.println();
		List<UserVo> resultList = userDao.loginSelect(userVo);
		if(resultList.isEmpty()) {					//로그인 실패
			System.out.println("로그인 fail!");
			return "redirect:/user/login";
		}else {										//로그인 성공
			System.out.println("로그인 success!");
			//session.setAttribute("sessionEmail", userVo.getEmail() );
			session.setAttribute("authUser", resultList.get(0));
			return "redirect:/";
		}	
	}
	
	@GetMapping("/user/logout")
	String logout(HttpSession session) {
		System.out.println(">>> "+this.getClass()+" 호출됨! logincheck");
		System.out.println();
		session.invalidate();
		return "redirect:/";
	}
}
