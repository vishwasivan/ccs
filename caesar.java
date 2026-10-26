class CaesarCipher {
public static String encode(String enc, int offset) {
offset = (offset % 26 + 26) % 26;
StringBuilder encoded = new StringBuilder();

for (char i : enc.toCharArray()) {
if (Character.isLetter(i)) {
if (Character.isUpperCase(i)) {
encoded.append((char) ('A' + (i - 'A' + offset) % 26));
} else {
encoded.append((char) ('a' + (i - 'a' + offset) % 26));
}
} else {
encoded.append(i);
}
}
return encoded.toString();
}

public static String decode(String enc, int offset) {
return encode(enc, -offset);
}

public static void main(String[] args) {
String msg = "Hello welcome to Security Laboratory";
int key = 12;

System.out.println("Simulation of Caesar Cipher");
System.out.println("Input message   : " + msg);
System.out.println("Encoded message : " + encode(msg, key));
System.out.println("Decoded message : " + decode(encode(msg, key), key));
}
}
