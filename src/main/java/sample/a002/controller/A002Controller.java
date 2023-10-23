package sample.a002.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.a001.form.A001Form;
import sample.a002.constants.A002Constants;
import sample.a002.form.A002Form;
import sample.common.cd.CD001;

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
		final CD001 workExFlg_1 = CD001.WorkExFlg_1;
		final CD001 workExFlg_0 = CD001.WorkExFlg_0;
		// 実務経験の有無
		switch (a001Form.getWorkExperienceFlg()) {
		case A002Constants.ONE:
			a001Form.setWorkExperienceFlg(workExFlg_1.getWorkExperienceFlg());
			break;
		case A002Constants.ZERO:
			a001Form.setWorkExperienceFlg(workExFlg_0.getWorkExperienceFlg());
			break;
		}
		
		// 現在の職種
		switch (a001Form.getTypeOfOccupationKbn()) {
		case A002Constants.ONE:
			a001Form.setTypeOfOccupationKbn("PM / PL");
			break;
		case A002Constants.TWO:
			a001Form.setTypeOfOccupationKbn("SE");
			break;
		case A002Constants.THREE:
			a001Form.setTypeOfOccupationKbn("PG");
			break;
		case A002Constants.FOUR:
			a001Form.setTypeOfOccupationKbn("その他");
			break;
		}
		
		// 使用した事のあるプログラミング言語
		int count = 0;
		for(String num : a001Form.getExperiencedProgrammingLungage()) {
			
			switch (num) {
			case A002Constants.ONE:
				a001Form.getExperiencedProgrammingLungage().set(count, A002Constants.PRO_LUNG_JAVA);
				break;
			case A002Constants.TWO:
				a001Form.getExperiencedProgrammingLungage().set(count, A002Constants.PRO_LUNG_PHP);
				break;
			case A002Constants.THREE:
				a001Form.getExperiencedProgrammingLungage().set(count, A002Constants.PRO_LUNG_RUBY);
				break;
			case A002Constants.FOUR:
				a001Form.getExperiencedProgrammingLungage().set(count, A002Constants.PRO_LUNG_ELSE);
				break;
			}
			count++;
		}

		return a001Form;
	}
}
