import java.util.*;
import java.math.BigInteger;

public class Main {
    final static BigInteger one = BigInteger.ONE;

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        System.out.println("Enter the approximate value of p:");
        BigInteger p = getNextPrime(stdin.next());

        System.out.println("Your prime is " + p + ".");

        System.out.println("Enter a number between 2 and p-1:");
        BigInteger g = new BigInteger(stdin.next());

        System.out.println("Person A: Enter your secret number:");
        BigInteger a = new BigInteger(stdin.next());

        BigInteger resulta = g.modPow(a, p);
        System.out.println("Person A sends to Person B: " + resulta);

        System.out.println("Person B: Enter your secret number:");
        BigInteger b = new BigInteger(stdin.next());

        BigInteger resultb = g.modPow(b, p);
        System.out.println("Person B sends to Person A: " + resultb);

        BigInteger KeyACalculates = resultb.modPow(a, p);
        BigInteger KeyBCalculates = resulta.modPow(b, p);

        System.out.println("A calculates (" + resultb + "^" + a + ") mod " + p);
        System.out.println("The key calculated by A is: " + KeyACalculates);

        System.out.println("B calculates (" + resulta + "^" + b + ") mod " + p);
        System.out.println("The key calculated by B is: " + KeyBCalculates);

        if (KeyACalculates.equals(KeyBCalculates)) {
            System.out.println("Key exchange successful! Both keys match.");
        }

        stdin.close();
    }

    public static BigInteger getNextPrime(String ans) {
        BigInteger test = new BigInteger(ans);

        while (!test.isProbablePrime(99)) {
            test = test.add(one);
        }

        return test;
    }
}
