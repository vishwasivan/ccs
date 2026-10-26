import javax.swing.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

class DES {
    byte[] skey;
    static byte[] raw;

    public DES() {
        try {
            generateSymmetricKey();

            String inputMessage = JOptionPane.showInputDialog(
                null, "Enter message to encrypt");

            if (inputMessage == null)
                return;

            byte[] ibyte = inputMessage.getBytes("UTF-8");
            byte[] ebyte = encrypt(raw, ibyte);

            String encryptedData = Base64.getEncoder().encodeToString(ebyte);
            System.out.println("Encrypted message: " + encryptedData);
            JOptionPane.showMessageDialog(null, "Encrypted Data:\n" + encryptedData);

            byte[] dbyte = decrypt(raw, ebyte);
            String decryptedMessage = new String(dbyte, "UTF-8");

            System.out.println("Decrypted message: " + decryptedMessage);
            JOptionPane.showMessageDialog(null, "Decrypted Data:\n" + decryptedMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void generateSymmetricKey() throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        kgen.init(56);

        SecretKey skey = kgen.generateKey();
        raw = skey.getEncoded();

        System.out.println("DES symmetric key: " +
            Base64.getEncoder().encodeToString(raw));
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(clear);
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(encrypted);
    }

    public static void main(String[] args) {
        new DES();
    }
}
