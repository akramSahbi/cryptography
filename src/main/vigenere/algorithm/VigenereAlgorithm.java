package main.vigenere.algorithm;

import main.caesar.algorithm.CaesarAlgorithm;

import java.util.Scanner;

import static main.caesar.common.Commons.ASCII_LENGTH;
import static main.caesar.common.Commons.DELIMITER;

public class VigenereAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DELIMITER);
        System.out.println("Enter text to encrypt using vigenere cipher:");
        String plaintext = scanner.nextLine();
        System.out.println("Enter key string value:");
        String key = scanner.nextLine();
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted using vigenere cipher: " + ciphertext);
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("decrypted back using vigenere cipher: " + decryptedText);
    }

    //encrypt plain text using caesar cipher
    public static String encrypt(String plaintext, String key) {
        String ciphertext = "";
        for (int i = 0; i < plaintext.length(); i++) {
            char character = plaintext.charAt(i);
            char characterKey = key.charAt(Math.floorMod(i, key.length()));
            int characterKeyInteger = (int) characterKey;

            ciphertext += CaesarAlgorithm.encrypt(character+"", characterKeyInteger);
        }

        return ciphertext;
    }

    //decrypt plain text using caesar cipher
    public static String decrypt(String ciphertext, String key) {
        String plaintext = "";
        for (int i = 0; i < ciphertext.length(); i++) {
            char character = ciphertext.charAt(i);
            char characterKey = key.charAt(Math.floorMod(i, key.length()));
            int characterKeyInteger = (int) characterKey;

            plaintext += CaesarAlgorithm.decrypt(character+"", characterKeyInteger);
        }

        return plaintext;
    }
}
