package sample.a001.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.a001.constants.A001Constants;
import sample.a001.form.A001Form;

@Controller
public class A001Controller {
	
	@Autowired
	HttpSession session;
	
	/**
	 * 初期表示用controller
	 * 
	 * @param form
	 * @param model
	 * @return a001
	 */
	@GetMapping(A001Constants.INIT_URL_A001)
	public String index001(@ModelAttribute A001Form form, Model model) {
		/* 0:新規、1:修正:、2:ブラウザバッグ */
		String screenTransitionFlg = A001Constants.ZERO;
		model.addAttribute("screenTransitionFlg", screenTransitionFlg);
		return A001Constants.SCREEN_URL_A001;
	}
	
	/**
	 * a002への画面遷移用controller
	 * 
	 * @param form
	 * @return forwardA002
	 */
	@PostMapping(A001Constants.GET_URL_A002)
	public String nextA001(@ModelAttribute A001Form form) {
		// セッションに登録
		session.setAttribute("data", form);
		// String check = "test";
		return "forward:/" + A001Constants.SCREEN_URL_A002;
//		return "forward:/forwardA002";
	}
	
	/**
	 * a001へのブラウザバック用controller
	 * 
	 * @param form
	 * @param model
	 * @return a001
	 */
	@PostMapping(A001Constants.SCREEN_URL_BACK_A001)
	public String backA001(@ModelAttribute A001Form form, Model model) {
		// セッションから値を取得
		A001Form data = (A001Form)session.getAttribute("data");
		System.out.println("data : " + data);
		// セッション情報クリア
		session.invalidate();
		/* 0:新規、1:修正:、2:ブラウザバッグ */
		String screenTransitionFlg = A001Constants.TWO;		
		model.addAttribute("screenTransitionFlg", screenTransitionFlg);
		model.addAttribute("a001Form", data);
		return A001Constants.SCREEN_URL_A001;
	}
	
}
