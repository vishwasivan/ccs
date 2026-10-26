class HillCipher {
    public static int[][] keymat = {
        {1, 2, 1},
        {2, 3, 2},
        {2, 2, 1}
    };

    public static int[][] invkeymat = {
        {-1, 0, 1},
        {2, -1, 0},
        {-2, 2, -1}
    };

    public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String encode(char a, char b, char c) {
        int posa = a - 'A', posb = b - 'A', posc = c - 'A';

        int x = posa * keymat[0][0] + posb * keymat[1][0] + posc * keymat[2][0];
        int y = posa * keymat[0][1] + posb * keymat[1][1] + posc * keymat[2][1];
        int z = posa * keymat[0][2] + posb * keymat[1][2] + posc * keymat[2][2];

        return "" + key.charAt(x % 26) + key.charAt(y % 26) + key.charAt(z % 26);
    }

    private static String decode(char a, char b, char c) {
        int posa = a - 'A', posb = b - 'A', posc = c - 'A';

        int x = posa * invkeymat[0][0] + posb * invkeymat[1][0] + posc * invkeymat[2][0];
        int y = posa * invkeymat[0][1] + posb * invkeymat[1][1] + posc * invkeymat[2][1];
        int z = posa * invkeymat[0][2] + posb * invkeymat[1][2] + posc * invkeymat[2][2];

        return "" + key.charAt((x % 26 + 26) % 26)
                   + key.charAt((y % 26 + 26) % 26)
                   + key.charAt((z % 26 + 26) % 26);
    }

    public static void main(String[] args) {
        String msg = "SecurityLaboratory";
        String enc = "", dec = "";

        System.out.println("Simulation of Hill Cipher");
        System.out.println("Input message : " + msg);

        msg = msg.toUpperCase().replaceAll("\\s", "");

        int n = msg.length() % 3;
        if (n != 0) {
            for (int i = 0; i < 3 - n; i++) {
                msg += 'X';
            }
        }

        System.out.println("Padded message : " + msg);

        for (int i = 0; i < msg.length(); i += 3) {
            enc += encode(msg.charAt(i), msg.charAt(i + 1), msg.charAt(i + 2));
        }

        System.out.println("Encoded message : " + enc);

        for (int i = 0; i < enc.length(); i += 3) {
            dec += decode(enc.charAt(i), enc.charAt(i + 1), enc.charAt(i + 2));
        }

        System.out.println("Decoded message : " + dec);
    }
}
