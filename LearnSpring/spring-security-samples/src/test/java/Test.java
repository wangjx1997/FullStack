import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author WangJX
 * @Date 2021/8/31 10:22
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(MD5("1"));
    }
    private static String MD5(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            byte[] var5 = result;
            int var6 = result.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                byte b = var5[var7];
                int number = b & 255;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }

                buffer.append(str);
            }

            return buffer.toString();
        } catch (NoSuchAlgorithmException var11) {
            var11.printStackTrace();
            return "";
        }
    }
}
