package src;
public class AsoutputBase64 {
  String res;
  public AsoutputBase64(String str) {
    res = Base64Encode(str);
  }
  public String Base64Encode(String str) {
    String version = System.getProperty("java.version");
    try {
        String ret = "";
        if (version.compareTo("1.9") >= 0) {
            Class Base64 = Class.forName("java.util.Base64");
            Object Encoder = Base64.getMethod("getEncoder", new Class[0]).invoke(Base64, new Object[]{});
            ret = (String) Encoder.getClass().getMethod("encodeToString", byte[].class).invoke(Encoder, str.getBytes());
        } else {
            Class Base64 = Class.forName("sun.misc.BASE64Encoder");
            Object Encoder = Base64.getDeclaredConstructor().newInstance();
            ret = (String) Encoder.getClass().getMethod("encode", byte[].class).invoke(Encoder, str.getBytes());
        }
        return ret;
    } catch (Exception e) {
      return str;
    }
  }
  @Override
  public String toString() {
    return res;
  }
}
