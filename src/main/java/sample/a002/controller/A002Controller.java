package sample.a002.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.a001.form.A001Form;
import sample.a002.form.A002Form;

@Controller
public class A002Controller {
	
	@PostMapping("/forwardA002")
	public String index001(@ModelAttribute A001Form a001Form, Model model) {
		A002Form a002Form = new A002Form();
		// コピー元, コピー先
		BeanUtils.copyProperties(a001Form, a002Form);
		model.addAttribute("a002Form", a002Form);
		System.out.println("A001Form : " + a001Form);
		System.out.println("A002Form : " + a002Form);
		return "a002";
	}
}
