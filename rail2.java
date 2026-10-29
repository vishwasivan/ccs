class Main {
    public static void main(String[] args) {
        String s = "WEAREDISCOVERED", enc = "", dec = "";
        int n = 3, r = 0, d = 1;
        String[] rail = {"", "", ""};

        for (char c : s.toCharArray()) {
            rail[r] += c;
            if (r == 0) d = 1;
            if (r == n - 1) d = -1;
            r += d;
        }

        for (String x : rail) enc += x;
        System.out.println("Encrypted: " + enc);

        int[] pattern = new int[s.length()];
        r = 0; d = 1;
        for (int i = 0; i < s.length(); i++) {
            pattern[i] = r;
            if (r == 0) d = 1;
            if (r == n - 1) d = -1;
            r += d;
        }

        int[] pos = new int[n];
        for (int i = 0; i < s.length(); i++) pos[pattern[i]]++;

        String[] rows = new String[n];
        int start = 0;
        for (int i = 0; i < n; i++) {
            rows[i] = enc.substring(start, start + pos[i]);
            start += pos[i];
        }

        for (int i = 0; i < s.length(); i++)
            dec += rows[pattern[i]].charAt(--pos[pattern[i]] + rows[pattern[i]].length());

        System.out.println("Decrypted: " + dec);
    }
}
