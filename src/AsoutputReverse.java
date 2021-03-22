package src;
public class AsoutputReverse {
  String res;
  public AsoutputReverse(String str) {
    res = new StringBuffer(str).reverse().toString();
  }
  @Override
  public String toString() {
    return res;
  }
}

