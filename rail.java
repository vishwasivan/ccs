class Main {
    public static void main(String[] args) {
        String s = "WEAREDISCOVERED";
        int n = 2;
        String a = "", b = "";

        for (int i = 0; i < s.length(); i++)
            if (i % 2 == 0) a += s.charAt(i);
            else b += s.charAt(i);

        String enc = a + b;
        System.out.println("Encrypted: " + enc);

        int mid = (s.length() + 1) / 2;
        String dec = "";

        for (int i = 0; i < s.length(); i++)
            dec += i % 2 == 0 ? enc.charAt(i / 2) : enc.charAt(mid + i / 2);

        System.out.println("Decrypted: " + dec);
    }
}
