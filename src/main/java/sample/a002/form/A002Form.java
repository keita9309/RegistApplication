package sample.a002.form;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class A002Form {
	/* 姓 */
	private String lastName;
	
	/* 名 */
	private String firstName;
	
	/* 生年月日 */
	private String birthDate;
	
	/* 年齢 */
	private String age;
	
	/* 実務経験の有無 */
	private String workExperienceFlg;
	
	/* 現在の職種(業務内容) */
	private String typeOfOccupationKbn;
	
	/* 経験した事のあるプログラミング言語 */
	private List<String> experiencedProgrammingLungage;
	
	/* 電話番号 */
	private String telNo;
	
	/* 郵便番号 */
	private String zipCode;
	
	/* 都道府県 */
	private Map<String, String> prefectures;
	//private String kenCode;
	
	/* 選択された都道府県コード */
	private String selectedKenCd;
	
	/* 都道府県 */
	//private String kenCode;
	
	/* 住所1 */
	private String address1;
	
	/* 住所2 */
	private String address2;
	
	/* 住所3 */
	private String address3;
	
	/* 画面遷移フラグ */
	//private String screenTransitionFlg;
}
