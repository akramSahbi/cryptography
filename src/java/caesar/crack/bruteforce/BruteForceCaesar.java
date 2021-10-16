package caesar.crack.bruteforce;

import java.util.Scanner;

import static caesar.common.Commons.ASCII_LENGTH;
import static caesar.common.Commons.DELIMITER;
import static caesar.algorithm.CaesarAlgorithm.decrypt;
import static caesar.algorithm.CaesarAlgorithm.encrypt;

public class BruteForceCaesar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DELIMITER);
        System.out.println("Enter text to encrypt using ceasar cipher:");
        String plaintext = scanner.nextLine();
        System.out.println("Enter key numeric value:");
        String keytext = scanner.nextLine();
        int key = Integer.parseInt(keytext);
        String ciphertext = encrypt(plaintext, key);
        crackBruteForce(ciphertext);
        System.out.println(DELIMITER);
    }

    //cracking caesar cipher using brute force attack
    public static String[] crackBruteForce(String ciphertext) {
        String[] decryptedTextsForEachKey = new String[ASCII_LENGTH];
        System.out.println("#######     Cracking Caesar Cipher using brute force attack     #######");
        for (int key = 1; key < ASCII_LENGTH; key++) {
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("using key: " + key + " : " + decryptedText);
            decryptedTextsForEachKey[key] = decryptedText;
        }
        return decryptedTextsForEachKey;
    }
}
