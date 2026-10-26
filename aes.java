import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptString {
    private static final String encryptionKey = "ABCDEFGHIJKLMNOP";
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5Padding";

    public static String encrypt(String plainText) {
        try {
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            IvParameterSpec iv = new IvParameterSpec(key);

            Cipher cipher = Cipher.getInstance(cipherTransformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] cipherText = cipher.doFinal(plainText.getBytes(characterEncoding));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            System.err.println("Encryption Exception: " + e.getMessage());
            return "";
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            IvParameterSpec iv = new IvParameterSpec(key);

            Cipher cipher = Cipher.getInstance(cipherTransformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            byte[] cipherText = Base64.getDecoder().decode(encryptedText);
            return new String(cipher.doFinal(cipherText), characterEncoding);
        } catch (Exception e) {
            System.err.println("Decryption Exception: " + e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter String: ");
        String plainString = sc.nextLine();

        String encryptedStr = encrypt(plainString);
        String decryptedStr = decrypt(encryptedStr);

        System.out.println("Plain String   : " + plainString);
        System.out.println("Encrypted String: " + encryptedStr);
        System.out.println("Decrypted String: " + decryptedStr);

        sc.close();
    }
}
