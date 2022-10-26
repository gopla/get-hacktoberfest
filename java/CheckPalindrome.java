class CheckPalindrome {
  public static void main(String[] args) {

    String str = "Apple", reverseStr = "";
    
    int strLength = str.length();

    for (int i = (strLength - 1); i >=0; --i) {
      reverseStr = reverseStr + str.charAt(i);
    }

    if (str.toLowerCase().equals(reverseStr.toLowerCase())) {
      System.out.println(str + " PALINDROME");
    }
    else {
      System.out.println(str + " NOT PALINDROME");
    }
  }
