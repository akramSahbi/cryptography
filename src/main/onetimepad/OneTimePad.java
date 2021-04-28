package main.onetimepad;

import main.caesar.algorithm.CaesarAlgorithm;

import java.util.Random;
import java.util.Scanner;

import static main.caesar.common.Commons.ASCII_LENGTH;
import static main.caesar.common.Commons.DELIMITER;

public class OneTimePad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DELIMITER);
        System.out.println("Enter text to encrypt using one time pad cipher:");
        String plaintext = scanner.nextLine();
        System.out.println("Enter key numeric value:");
        String key = generaterandomKey(plaintext.length());
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted using one time pad algorithm: " + ciphertext);
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("decrypted back using one time pad cipher: " + decryptedText);
    }

    public static String encrypt(String plaintext, String key) {
        String ciphertext = "";
        for (int i = 0; i < plaintext.length(); i++) {
            String toEncrypt = plaintext.charAt(i) + "";
            int keyValue = (int) key.charAt(i);
            ciphertext += CaesarAlgorithm.encrypt(toEncrypt, keyValue);
        }
        return ciphertext;
    }

    public static String decrypt(String ciphertext, String key) {
        String plaintext = "";
        for (int i = 0; i < ciphertext.length(); i++) {
            String toEncrypt = ciphertext.charAt(i) + "";
            int keyValue = (int) key.charAt(i);
            plaintext += CaesarAlgorithm.decrypt(toEncrypt, keyValue);
        }
        return plaintext;
    }

    public static String generaterandomKey(int length) {
        String key = "";
        Random seed = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            int randomCharNumberValue = Math.floorMod(seed.nextInt(), ASCII_LENGTH);
            char randomChar = (char) randomCharNumberValue;
            key += randomChar;
        }
        return key;
    }
}
