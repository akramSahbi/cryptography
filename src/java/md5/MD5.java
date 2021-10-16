package md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashMd5 = md.digest(line.getBytes());
        String md5HashedLine = bytesToHexString(hashMd5);
        System.out.println(md5HashedLine);
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();

        for (byte b : bytes) {
            int x = b & 0xff;
            String hex = Integer.toHexString(x);
            if ( hex.length() % 2 == 1) {
                hex = "0" + hex;
            }
            result.append(hex);
        }
        return result.toString();
    }
}
