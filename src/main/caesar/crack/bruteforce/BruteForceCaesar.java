package src.main.caesar.crack.bruteforce;

import java.util.Scanner;

import static src.main.caesar.algorithm.CaesarAlgorithm.decrypt;
import static src.main.caesar.algorithm.CaesarAlgorithm.encrypt;
import static src.main.caesar.common.Commons.ASCII_LENGTH;
import static src.main.caesar.common.Commons.DELIMITER;

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
    public static void crackBruteForce(String ciphertext) {
        System.out.println("#######     Cracking Caesar Cipher using brute force attack     #######");
        for (int key = 1; key < ASCII_LENGTH; key++) {
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("using key: " + key + " : " + decryptedText);
        }
    }
}
