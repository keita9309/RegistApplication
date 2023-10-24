package sample.a001.controller;

import java.util.LinkedHashMap;
import java.util.Map;

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
		// 都道府県情報格納用のMap
		Map<String, String> kenMap = new LinkedHashMap<>();
		kenMap = createKenMap(kenMap);
		form.setPrefectures(kenMap);
		model.addAttribute("a001Form", form);
		model.addAttribute("screenTransitionFlg", screenTransitionFlg);
		return A001Constants.SCREEN_URL_A001;
	}
	
	private Map<String, String> createKenMap(Map<String, String> kenMap) {
		kenMap.put("01", "北海道");
		kenMap.put("02", "青森県");
		kenMap.put("03", "岩手県");
		kenMap.put("04", "宮城県");
		kenMap.put("05", "秋田県");
		kenMap.put("06", "山形県");
		kenMap.put("07", "福島県");
		kenMap.put("08", "茨城県");
		kenMap.put("09", "栃木県");
		kenMap.put("10", "群馬県");
		kenMap.put("11", "埼玉県");
		kenMap.put("12", "千葉県");
		kenMap.put("13", "東京都");
		kenMap.put("14", "神奈川県");
		kenMap.put("15", "新潟県");
		kenMap.put("16", "富山県");
		kenMap.put("17", "石川県");
		kenMap.put("18", "福井県");
		kenMap.put("19", "山梨県");
		kenMap.put("20", "長野県");
		kenMap.put("21", "岐阜県");
		kenMap.put("22", "静岡県");
		kenMap.put("23", "愛知県");
		kenMap.put("24", "三重県");
		kenMap.put("25", "滋賀県");
		kenMap.put("26", "京都府");
		kenMap.put("27", "大阪府");
		kenMap.put("28", "兵庫県");
		kenMap.put("29", "奈良県");
		kenMap.put("30", "和歌山県");
		kenMap.put("31", "鳥取県");
		kenMap.put("32", "島根県");
		kenMap.put("33", "岡山県");
		kenMap.put("34", "広島県");
		kenMap.put("35", "山口県");
		kenMap.put("36", "徳島県");
		kenMap.put("37", "香川県");
		kenMap.put("38", "愛媛県");
		kenMap.put("39", "高知県");
		kenMap.put("40", "福岡県");
		kenMap.put("41", "佐賀県");
		kenMap.put("42", "長崎県");
		kenMap.put("43", "熊本県");
		kenMap.put("44", "大分県");
		kenMap.put("45", "宮崎県");
		kenMap.put("46", "鹿児島県");
		kenMap.put("47", "沖縄県");
		return kenMap;
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
		// 都道府県情報格納用のMap
		Map<String, String> kenMap = new LinkedHashMap<>();
		kenMap = createKenMap(kenMap);
		data.setPrefectures(kenMap);
		model.addAttribute("a001Form", data);	
		model.addAttribute("screenTransitionFlg", screenTransitionFlg);
		model.addAttribute("a001Form", data);
		return A001Constants.SCREEN_URL_A001;
	}
	
}
