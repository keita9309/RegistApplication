package sample.common.cd;

public enum CD001 {
	WorkExFlg_1("1", "あり"),
	WorkExFlg_0("0", "なし"),
	;
	
	private String code; // コード
    private String workExperienceFlg; // 経験の有無
    
    /**
     * コンストラクタ
     * かならずprivate指定でないといけない
     */
     private CD001 (String code, String workExperienceFlg) {
         this.code = code;
         this.workExperienceFlg = workExperienceFlg;
     }
     public String getCode(){
         return code;
     }
     public String getWorkExperienceFlg(){
         return workExperienceFlg;
     }

}
