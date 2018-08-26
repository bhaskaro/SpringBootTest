package com.oggu.spring.boot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class SampleController {

	@RequestMapping(value = "/greet/{userName}", method = RequestMethod.GET)
	@ResponseBody
	String greet(@PathVariable String userName) {
		return "Hello " + userName + " !";
	}
	
	@RequestMapping(value = "/greet/", method = RequestMethod.POST)
	@ResponseBody
	String greetPost(@RequestBody String input) {
		
		System.out.println("POST Called with : " + input);
		return "Hello POST " + input + " !";
	}
	
	@RequestMapping(value = "/greet/", method = RequestMethod.PUT)
	@ResponseBody
	String greetPut(@RequestBody String input) {
		
		System.out.println("PUT Called with : " + input);
		return "Hello PUT " + input + " !";
	}
	
	@RequestMapping(value = "/greet/", method = RequestMethod.PATCH)
	@ResponseBody
	String greetPatch(@RequestBody String input) {
		return "Hello PATCH " + input + " !";
	}
	
	@RequestMapping(value = "/greet/{userName}", method = RequestMethod.DELETE)
	@ResponseBody
	String greetDelete(@PathVariable String userName) {
		return "Hello DELTE " + userName + " !";
	}

}