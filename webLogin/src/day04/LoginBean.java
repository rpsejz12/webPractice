package day04;

public class LoginBean {
   private String userID; // 멤버변수명 -> view(.jsp,.html)의 name과 일치시킴!
   private String userPW;
   public String getUserID() {
      return userID;
   }
   public void setUserID(String userID) {
      this.userID = userID;
   }
   public String getUserPW() {
      return userPW;
   }
   public void setUserPW(String userPW) {
      this.userPW = userPW;
   }
   
   final String dbID="kim"; // DB역할. 값을 상수화
   final String dbPW="1234";
   
   public boolean check() { // 빈즈의 기능
      if(userID.equals(dbID)&&userPW.equals(dbPW)) {
         return true;
      }
      return false;
   }
}