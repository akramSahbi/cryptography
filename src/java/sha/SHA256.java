package sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static md5.MD5.bytesToHexString;

public class SHA256 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashMd5 = md.digest(line.getBytes());
        String md5HashedLine = bytesToHexString(hashMd5);
        System.out.println(md5HashedLine);
    }
}
