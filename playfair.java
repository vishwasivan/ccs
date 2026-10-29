class Main {
    public static void main(String[] args) {
        char[][] m = {
            {'M','O','N','A','R'}, {'C','H','Y','B','D'},
            {'E','F','G','I','K'}, {'L','P','Q','S','T'},
            {'U','V','W','X','Z'}
        };

        String s = "INSTRUMENTS", p = "", enc = "", dec = "";
        s = s.replace('J','I').toUpperCase();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = i+1 < s.length() ? s.charAt(i+1) : 'X';
            if (a == b) b = 'X';
            else i++;
            p += "" + a + b;
        }

        for (int i = 0; i < p.length(); i += 2) {
            int r1=0,c1=0,r2=0,c2=0;
            for(int r=0;r<5;r++) for(int c=0;c<5;c++) {
                if(m[r][c]==p.charAt(i))   {r1=r;c1=c;}
                if(m[r][c]==p.charAt(i+1)){r2=r;c2=c;}
            }
            if(r1==r2) enc += ""+m[r1][(c1+1)%5]+m[r2][(c2+1)%5];
            else if(c1==c2) enc += ""+m[(r1+1)%5][c1]+m[(r2+1)%5][c2];
            else enc += ""+m[r1][c2]+m[r2][c1];
        }
        System.out.println("Encrypted: " + enc);

        for (int i = 0; i < enc.length(); i += 2) {
            int r1=0,c1=0,r2=0,c2=0;
            for(int r=0;r<5;r++) for(int c=0;c<5;c++) {
                if(m[r][c]==enc.charAt(i))   {r1=r;c1=c;}
                if(m[r][c]==enc.charAt(i+1)){r2=r;c2=c;}
            }
            if(r1==r2) dec += ""+m[r1][(c1+4)%5]+m[r2][(c2+4)%5];
            else if(c1==c2) dec += ""+m[(r1+4)%5][c1]+m[(r2+4)%5][c2];
            else dec += ""+m[r1][c2]+m[r2][c1];
        }
        System.out.println("Decrypted: " + dec);
    }
}
