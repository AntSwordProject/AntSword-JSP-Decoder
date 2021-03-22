
public class AsTest {
    public String cs = "UTF-8";
    public String decoderClassdata;
    public String asoutput(String str) {
        try {
            byte[] classBytes = Base64DecodeToByte(this.decoderClassdata);
            java.lang.reflect.Method defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass",new Class[]{byte[].class, int.class, int.class});
            defineClassMethod.setAccessible(true);
            Class cc = (Class) defineClassMethod.invoke(this.getClass().getClassLoader(), classBytes, 0,classBytes.length);
            return cc.getConstructor(String.class).newInstance(str).toString();
        } catch (Exception e) {
            return e.toString() + str;
        }
    }
    public byte[] Base64DecodeToByte(String str) {
        byte[] bt = null;
        String version = System.getProperty("java.version");
        try {
            if (version.compareTo("1.9") >= 0) {
                Class clazz = Class.forName("sun.misc.BASE64Decoder");
                bt = (byte[]) clazz.getMethod("decodeBuffer", String.class).invoke(clazz.newInstance(), str);
            } else {
                Class clazz = Class.forName("java.util.Base64");
                Object decoder = clazz.getMethod("getDecoder").invoke(null);
                bt = (byte[]) decoder.getClass().getMethod("decode", String.class).invoke(decoder, str);
            }
            return bt;
        }catch (Exception e) {
            return new byte[]{};
        }
    }
    public static void main(String[] args) throws Exception{
        AsTest astest = new AsTest();
        astest.decoderClassdata = "yv66vgAAADQAXQoADgArCgAaACwJABoALQgALgoALwAwCAAxCAAyCgAUADMIADQKAAwANQgANgcANwoADAA4BwA5CgA6ADsKAA4APAgAPQcAPgoAFAA/BwBACABBCgAMAEIKAEMARAgARQcARgcARwEAA3JlcwEAEkxqYXZhL2xhbmcvU3RyaW5nOwEABjxpbml0PgEAFShMamF2YS9sYW5nL1N0cmluZzspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAxCYXNlNjRFbmNvZGUBACYoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nOwEADVN0YWNrTWFwVGFibGUHAEAHAEcHAEYBAAh0b1N0cmluZwEAFCgpTGphdmEvbGFuZy9TdHJpbmc7AQAKU291cmNlRmlsZQEADUFzb3V0cHV0LmphdmEMAB0ASAwAIQAiDAAbABwBAAxqYXZhLnZlcnNpb24HAEkMAEoAIgEAAAEAAzEuOQwASwBMAQAQamF2YS51dGlsLkJhc2U2NAwATQBOAQAKZ2V0RW5jb2RlcgEAD2phdmEvbGFuZy9DbGFzcwwATwBQAQAQamF2YS9sYW5nL09iamVjdAcAUQwAUgBTDABUAFUBAA5lbmNvZGVUb1N0cmluZwEAAltCDABWAFcBABBqYXZhL2xhbmcvU3RyaW5nAQAWc3VuLm1pc2MuQkFTRTY0RW5jb2RlcgwAWABZBwBaDABbAFwBAAZlbmNvZGUBABNqYXZhL2xhbmcvRXhjZXB0aW9uAQAIQXNvdXRwdXQBAAMoKVYBABBqYXZhL2xhbmcvU3lzdGVtAQALZ2V0UHJvcGVydHkBAAljb21wYXJlVG8BABUoTGphdmEvbGFuZy9TdHJpbmc7KUkBAAdmb3JOYW1lAQAlKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL0NsYXNzOwEACWdldE1ldGhvZAEAQChMamF2YS9sYW5nL1N0cmluZztbTGphdmEvbGFuZy9DbGFzczspTGphdmEvbGFuZy9yZWZsZWN0L01ldGhvZDsBABhqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2QBAAZpbnZva2UBADkoTGphdmEvbGFuZy9PYmplY3Q7W0xqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsBAAhnZXRDbGFzcwEAEygpTGphdmEvbGFuZy9DbGFzczsBAAhnZXRCeXRlcwEABCgpW0IBABZnZXREZWNsYXJlZENvbnN0cnVjdG9yAQAzKFtMamF2YS9sYW5nL0NsYXNzOylMamF2YS9sYW5nL3JlZmxlY3QvQ29uc3RydWN0b3I7AQAdamF2YS9sYW5nL3JlZmxlY3QvQ29uc3RydWN0b3IBAAtuZXdJbnN0YW5jZQEAJyhbTGphdmEvbGFuZy9PYmplY3Q7KUxqYXZhL2xhbmcvT2JqZWN0OwAhABoADgAAAAEAAAAbABwAAAADAAEAHQAeAAEAHwAAAC4AAwACAAAADiq3AAEqKiu2AAK1AAOxAAAAAQAgAAAADgADAAAACAAEAAkADQAKAAEAIQAiAAEAHwAAARQABgAGAAAAnxIEuAAFTRIGTiwSB7YACJsAShIJuAAKOgQZBBILA70ADLYADRkEA70ADrYADzoFGQW2ABASEQS9AAxZAxISU7YADRkFBL0ADlkDK7YAE1O2AA/AABROpwBDEhW4AAo6BBkEA70ADLYAFgO9AA62ABc6BRkFtgAQEhgEvQAMWQMSElO2AA0ZBQS9AA5ZAyu2ABNTtgAPwAAUTi2wThIGsAABAAYAmgCbABkAAgAgAAAANgANAAAADAAGAA4ACQAPABIAEAAZABEALwASAFYAEwBZABQAYAAVAHIAFgCZABgAmwAZAJwAGgAjAAAAHwAD/QBZBwAkBwAkP/8AAQADBwAlBwAkBwAkAAEHACYAAQAnACgAAQAfAAAAHQABAAEAAAAFKrQAA7AAAAABACAAAAAGAAEAAAAfAAEAKQAAAAIAKg==";
        
        System.out.println(astest.asoutput("123123"));
    }
}