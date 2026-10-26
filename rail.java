class RailFenceCipherHelper {
    String encode(String msg, int depth) {
        int r = depth, l = msg.length();
        int c = (l + depth - 1) / depth, k = 0;
        char[][] mat = new char[r][c];
        String enc = "";

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k < l)
                    mat[j][i] = msg.charAt(k++);
                else
                    mat[j][i] = 'X';
            }
        }

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                enc += mat[i][j];

        return enc;
    }

    String decode(String encmsg, int depth) {
        int r = depth, l = encmsg.length();
        int c = (l + depth - 1) / depth, k = 0;
        char[][] mat = new char[r][c];
        String dec = "";

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                mat[i][j] = encmsg.charAt(k++);

        for (int i = 0; i < c; i++)
            for (int j = 0; j < r; j++)
                dec += mat[j][i];

        return dec;
    }
}

class RailFenceCipher {
    public static void main(String[] args) {
        RailFenceCipherHelper rf = new RailFenceCipherHelper();
        String msg = "hellorailfencecipher";
        int depth = 2;

        String enc = rf.encode(msg, depth);
        String dec = rf.decode(enc, depth);

        System.out.println("Simulation of Rail Fence Cipher");
        System.out.println("Input message   : " + msg);
        System.out.println("Encoded message: " + enc);
        System.out.println("Decoded message: " + dec);
    }
}
