package com.simplilearn.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {
	
	// request mapping
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public String indexMapping() {		
		return "Spring Application server is Up & Running !";
	}
	
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	public String helloMapping(@RequestParam("username") String username) {		
		return "Hello, user "+username;
	}
	
	
	@RequestMapping(value="/greet", method=RequestMethod.GET)
	@ResponseBody
	public String helloMapping(@RequestParam("time") int time) {	
		if(time>=5 && time< 12) {
			return "Good Morning!, You start a wonderfull day.";
		} else if(time>=12 & time<18) {
			return "Good Afternoon!, Best of luck for the other of the day";
		}  else if(time>=18 & time<24) {
			return "Good Evining!, You have done a wonderfull job";
		}
		return "Good Night, You have earn a good night sleep.";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String userMapping(@PathVariable("id") int id) {		
		return "Hello user, & collected userId is : "+id;
	}
}
