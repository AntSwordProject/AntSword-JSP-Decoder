public class AsoutputRot13 {
  String res;
  public AsoutputRot13(String str) {
    StringBuffer sb = new StringBuffer(str);
    for(int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      if (c >= 'a' && c <= 'm') c += 13;
      else if  (c >= 'A' && c <= 'M') c += 13;
      else if  (c >= 'n' && c <= 'z') c -= 13;
      else if  (c >= 'N' && c <= 'Z') c -= 13;
      sb.setCharAt(i, c);
    }
    res = sb.toString();
  }
  @Override
  public String toString() {
    return res;
  }
}