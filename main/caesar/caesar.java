package main.caesar;

import java.util.Scanner;

public class caesar {
    private static final int ASCII_LENGTH = 256;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to encrypt using ceasar cipher:");
        String plaintext = scanner.nextLine();
        System.out.println("Enter key numeric value:");
        String keytext = scanner.nextLine();
        int key = Integer.parseInt(keytext);
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted using ceasar cipher: " + ciphertext);
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("decrypted back using ceasar cipher: " + decryptedText);
    }

    private static String encrypt(String plaintext, int key) {
        String ciphertext = "";
        for (int i = 0; i < plaintext.length(); i++) {
            char character = plaintext.charAt(i);
            int plainTextCharacterIndex = (int) character;
            int cipherTextCharacterIndex = Math.floorMod(plainTextCharacterIndex + key, ASCII_LENGTH);
            char cipherTextCharacter = (char) cipherTextCharacterIndex;
            ciphertext += cipherTextCharacter;
        }

        return ciphertext;
    }

    private static String decrypt(String ciphertext, int key) {
        String plaintext = "";
        for (int i = 0; i < ciphertext.length(); i++) {
            char character = ciphertext.charAt(i);
            int plainTextCharacterIndex = (int) character;
            int cipherTextCharacterIndex = Math.floorMod(plainTextCharacterIndex - key, ASCII_LENGTH);
            char cipherTextCharacter = (char) cipherTextCharacterIndex;
            plaintext += cipherTextCharacter;
        }

        return plaintext;
    }
}
