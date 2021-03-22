import java.io.*;

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
    public static String Base64Encode(byte[] bt) {
        String version = System.getProperty("java.version");
        try {
            String ret = "";
            if (version.compareTo("1.9") >= 0) {
                Class Base64 = Class.forName("java.util.Base64");
                Object Encoder = Base64.getMethod("getEncoder", new Class[0]).invoke(Base64, new Object[]{});
                ret = (String) Encoder.getClass().getMethod("encodeToString", byte[].class).invoke(Encoder, bt);
            } else {
                Class Base64 = Class.forName("sun.misc.BASE64Encoder");
                Object Encoder = Base64.getDeclaredConstructor().newInstance();
                ret = (String) Encoder.getClass().getMethod("encode", byte[].class).invoke(Encoder, bt);
            }
            return ret;
        } catch (Exception e) {
          return new String(bt);
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
        astest.decoderClassdata = "";
        if(args.length != 1) {
            System.out.printf("Usage: java AsTest [filename].class\neg: java AsTest src/AsoutputRot13.class");
            System.exit(1);
        }
        File file = new File(args[0]);
        FileInputStream fis = new FileInputStream(file);
        byte fileData[] = new byte[(int) file.length()];
        fis.read(fileData);
        astest.decoderClassdata = Base64Encode(fileData).replaceAll("\n", "").replaceAll("\r", "");

        System.out.println(astest.decoderClassdata);

        String Msg = "This is AntSword JSP Decoder Test Message.";
        System.out.printf("\n[+] Plain Text:\n%s\n", Msg);
        System.out.printf("\n[+] Asoutput:\n");
        System.out.println(astest.asoutput(Msg));
    }
}
