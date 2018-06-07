package com.reporting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.reporting.model.Customer;
import com.reporting.services.CustomerService;

@Controller
@RequestMapping(value="customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String showLogin(ModelMap model,HttpSession session){
		if(session.getAttribute("customer")==null){
			model.put("customerData", new Customer());
			return "login/login";
		}else{
			return "redirect:success";
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doLogin(ModelMap model, @ModelAttribute("customerData") Customer customer,HttpSession session){
		
		System.out.println("..............login called..............");
		
		if(customer.getc_name()!=null && customer.getc_password()!=null && session.getAttribute("customer")==null){
			customer = customerService.loginCustomer(customer);
			if(customer!=null){
				session.setAttribute("customer",customer);
				return "redirect:success";
			}else{
				model.put("failed", "LoginFailed");
				return "login/login";
			}
		}else{
			return "redirect:success";
		}
		
	}
	
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String showSuccess(ModelMap model,HttpSession session){
			model.put("success", new Customer());
			return "success";
	}
	
	
}
