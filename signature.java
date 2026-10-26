import java.io.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class DigitalSignatureHandler {
    static final String DOC = "info.txt";
    static final String SIG = "info.signature";
    static final String PUB = "info.public.key";

    static PrivateKey privateKey;
    static PublicKey publicKey;

    static void generateKeys() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        privateKey = kp.getPrivate();
        publicKey = kp.getPublic();

        try (FileOutputStream f = new FileOutputStream(PUB)) {
            f.write(publicKey.getEncoded());
        }
    }

    static byte[] sign() throws Exception {
        Signature s = Signature.getInstance("SHA256withDSA");
        s.initSign(privateKey);

        try (FileInputStream f = new FileInputStream(DOC)) {
            byte[] b = new byte[1024];
            int n;
            while ((n = f.read(b)) != -1)
                s.update(b, 0, n);
        }

        byte[] signature = s.sign();
        try (FileOutputStream f = new FileOutputStream(SIG)) {
            f.write(signature);
        }
        return signature;
    }

    static boolean verify() throws Exception {
        byte[] key = java.nio.file.Files.readAllBytes(
                java.nio.file.Paths.get(PUB));
        publicKey = KeyFactory.getInstance("DSA")
                .generatePublic(new X509EncodedKeySpec(key));

        byte[] signature = java.nio.file.Files.readAllBytes(
                java.nio.file.Paths.get(SIG));

        Signature s = Signature.getInstance("SHA256withDSA");
        s.initVerify(publicKey);

        try (FileInputStream f = new FileInputStream(DOC)) {
            byte[] b = new byte[1024];
            int n;
            while ((n = f.read(b)) != -1)
                s.update(b, 0, n);
        }

        return s.verify(signature);
    }

    public static void main(String[] args) {
        try {
            generateKeys();
            sign();
            System.out.println(verify()
                    ? "Signature Verification Successful!"
                    : "Signature Verification Failed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
