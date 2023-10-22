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
	
	/**
	 * a002画面を表示
	 * 
	 * @param a001Form
	 * @param model
	 * @return
	 */
	@PostMapping("forwardA002")
	public String index001(@ModelAttribute A001Form a001Form, Model model) {
		// 値の詰め替え
		A001Form changed001Form = createNew001Form(a001Form);
		A002Form a002Form = new A002Form();
		// コピー元, コピー先
		BeanUtils.copyProperties(changed001Form, a002Form);
		model.addAttribute("a002Form", a002Form);
		
		System.out.println("A001Form : " + a001Form);
		System.out.println("A002Form : " + a002Form);
		
		return "a002";
	}
	
	/**
	 * a003画面を表示
	 * 
	 * @param a002Form
	 * @param model
	 * @return
	 */
	@PostMapping("/a003")
	public String index002(@ModelAttribute A002Form a002Form, Model model) {
		A001Form a001Form = new A001Form();
		// コピー元, コピー先
		BeanUtils.copyProperties(a002Form, a001Form);
		/* 0:新規、1:修正:、2:ブラウザバッグ */
		String screenTransitionFlg = "1";
		model.addAttribute("screenTransitionFlg", screenTransitionFlg);
		model.addAttribute("a001Form", a001Form);
		System.out.println("A001Form : " + a001Form);
		System.out.println("A002Form : " + a002Form);
		return "a001";
	}
	
	/**
	 * A001Formの項目の中で、詰め替えが必要な項目の値を変更
	 * 
	 * @param a001Form 画面から受け取ったForm
	 * @return a001Form 詰め替え後のForm
	 */
	private A001Form createNew001Form(A001Form a001Form) {
		// 実務経験の有無
		switch (a001Form.getWorkExperienceFlg()) {
		case "1":
			a001Form.setWorkExperienceFlg("あり");
			break;
		case "0":
			a001Form.setWorkExperienceFlg("なし");
			break;
		}
		// 現在の職種
		switch (a001Form.getTypeOfOccupationKbn()) {
		case "1":
			a001Form.setTypeOfOccupationKbn("PM / PL");
			break;
		case "2":
			a001Form.setTypeOfOccupationKbn("SE");
			break;
		case "3":
			a001Form.setTypeOfOccupationKbn("PG");
			break;
		case "4":
			a001Form.setTypeOfOccupationKbn("その他");
			break;
		}
		// 使用した事のあるプログラミング言語
//		switch (a001Form.getTypeOfOccupationKbn()) {
//		case "1":
//			a001Form.setTypeOfOccupationKbn("PM / PL");
//			break;
//		case "2":
//			a001Form.setTypeOfOccupationKbn("SE");
//			break;
//		case "3":
//			a001Form.setTypeOfOccupationKbn("PG");
//			break;
//		case "4":
//			a001Form.setTypeOfOccupationKbn("その他");
//			break;
//		}
		
		return a001Form;
	}
}
