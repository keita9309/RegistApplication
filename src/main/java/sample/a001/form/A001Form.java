package sample.a001.form;

import lombok.Data;

@Data
public class A001Form {
	/* 姓 */
	private String lastName;
	
	/* 名 */
	private String firstName;
	
	/* 生年月日 */
	private String birthDate;
	
	/* 年齢 */
	private int age;
	
	/* ユーザコード */
	private String userCode;
	
	/* 郵便番号 */
	private String zipCode;
	
	/* 都道府県 */
	private String kenCode;
	
	/* 住所1 */
	private String address1;
	
	/* 住所2 */
	private String address2;
	
	/* 住所3 */
	private String address3;

}
