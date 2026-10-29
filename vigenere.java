class Main {
    public static void main(String[] args) {
        String s = "HELLO", key = "KEY";
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String enc = "";

        for (int i = 0; i < s.length(); i++) {
            int a = abc.indexOf(s.charAt(i));
            int b = abc.indexOf(key.charAt(i % key.length()));
            enc += abc.charAt((a + b) % 26);
        }

        System.out.println("Encrypted: " + enc);

        String dec = "";
        for (int i = 0; i < enc.length(); i++) {
            int a = abc.indexOf(enc.charAt(i));
            int b = abc.indexOf(key.charAt(i % key.length()));
            dec += abc.charAt((a - b + 26) % 26);
        }

        System.out.println("Decrypted: " + dec);
    }
}
