class Main {
    public static void main(String[] args) {
        int[][] k = {{3, 3}, {2, 5}};
        String s = "HELP", enc = "", dec = "";
        s = s.toUpperCase().replaceAll("[^A-Z]", "");

        for (int i = 0; i < s.length(); i += 2) {
            int a = s.charAt(i) - 'A', b = s.charAt(i + 1) - 'A';
            enc += (char)((3*a + 3*b) % 26 + 'A');
            enc += (char)((2*a + 5*b) % 26 + 'A');
        }
        System.out.println("Encrypted: " + enc);

        int[][] d = {{15, 17}, {20, 9}}; // Inverse key matrix mod 26

        for (int i = 0; i < enc.length(); i += 2) {
            int a = enc.charAt(i) - 'A', b = enc.charAt(i + 1) - 'A';
            dec += (char)((d[0][0]*a + d[0][1]*b) % 26 + 'A');
            dec += (char)((d[1][0]*a + d[1][1]*b) % 26 + 'A');
        }
        System.out.println("Decrypted: " + dec);
    }
}
