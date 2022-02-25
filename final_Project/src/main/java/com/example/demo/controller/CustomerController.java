package com.example.demo.controller;

import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

import lombok.Setter;

@Controller
@Setter
public class CustomerController {

	@Autowired
	private CustomerDAO dao;
	
	@RequestMapping(value = "/insertCustomer", method = RequestMethod.GET)
	public void join_form(){
		
	}
	
	@RequestMapping(value = "/insertCustomer", method = RequestMethod.POST)
	public ModelAndView join_submit(CustomerVO c){
		ModelAndView mav = new ModelAndView();
		int re = dao.insertCustomer(c);
		if(re == 1) {
			mav.setViewName("insertCustomerOK");
			mav.addObject("msg",c.getCust_id()+"보글님 회원가입이 완료되었습니다.");
		}else {
			mav.setViewName("/common/error");
		}
		return mav;
	}
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	@ResponseBody
	public int idCheck_submit(HttpServletRequest request, HttpSession session ,String cust_id)  {
		int re = dao.doubleCheck_id(cust_id);
		System.out.println("아이디 중복확인 결과:"+re);
		return re;
		
	}
	
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	@ResponseBody
	public int emailCheck_submit(HttpServletRequest request, HttpSession session ,String cust_email)  {
		int re = dao.doubleCheck_email(cust_email);
		System.out.println("이메일 중복확인 결과:"+re);
		return re;
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login_form() {
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login_submit(HttpSession session, String cust_id, String cust_pwd) {
		
		ModelAndView mav = new ModelAndView("loginOK");
		int re = dao.login(cust_id, cust_pwd);
		String msg = "";
		if(re == 1) {
			msg = cust_id + "님, 로그인 하였습니다.";
			session.setAttribute("member",dao.detailCustomer(cust_id));
			session.setAttribute("cust_id", cust_id);
		}else { //re가 -1
			msg = "아이디가 존재하지 않습니다.";
		}
		mav.addObject("msg",msg);
		
		return mav;
	}
	
	@RequestMapping(value = "/find_id", method = RequestMethod.GET)
	public void find_id_form() {
		
	}
	
	@RequestMapping(value = "/find_id", method = RequestMethod.POST)
	public void find_id_submit() {
		
	}
	
	@RequestMapping(value = "/find_pwd", method = RequestMethod.GET)
	public void find_pwd_form() {
		
	}
	
	@RequestMapping(value = "/find_pwd", method = RequestMethod.POST)
	public void find_pwd_submit() {
		
	}
	
	@RequestMapping(value = "/mypage/updateCustomer", method = RequestMethod.GET)
	public void update_form(HttpSession session, Model model, String cust_id) {
		CustomerVO member = (CustomerVO)session.getAttribute("member");
		model.addAttribute("c",dao.detailCustomer(member.getCust_id()));
	}
	
	@RequestMapping(value = "/mypage/updateCustomer", method = RequestMethod.POST)
	public ModelAndView update_submit(CustomerVO c) {
		ModelAndView mav = new ModelAndView();
		int re = dao.updateCustomer(c);
		if(re == 1) {
			mav.setViewName("redirect:/main");
		}else {
			mav.addObject("msg","정보수정에 실패했습니다.");
			mav.setViewName("/common/error");
		}
		return mav;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String getLogout(HttpSession session) throws Exception{
		
		session.invalidate();
		return "redirect:/main";
	}
}









