package sample.a001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.a001.form.A001Form;

@Controller
public class A001Controller {
	
	@GetMapping("/")
	public String index001(@ModelAttribute A001Form form) {
//		A001Form a001Form = new A001Form();
//		model.addAttribute("a001Form", a001Form);
		return "a001";
	}
	
	@PostMapping("/a002")
	public String nextA001(@ModelAttribute A001Form form) {
		String check = "test";
		return "forward:/forwardA002";
	}
	
}
