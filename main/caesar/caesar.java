package main.caesar;

import java.util.Scanner;

public class caesar {
    private static final int ASCII_LENGTH = 256;
    private static final String DELIMITER = "####################################################################################";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DELIMITER);
        System.out.println("Enter text to encrypt using ceasar cipher:");
        String plaintext = scanner.nextLine();
        System.out.println("Enter key numeric value:");
        String keytext = scanner.nextLine();
        int key = Integer.parseInt(keytext);
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted using ceasar cipher: " + ciphertext);
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("decrypted back using ceasar cipher: " + decryptedText);
        crackBruteForce(ciphertext);
        System.out.println(DELIMITER);
        crackFrequencyAnalysis(ciphertext);
        System.out.println(DELIMITER);
        fastFrequencyAnalysis(ciphertext);
        System.out.println(DELIMITER);
    }

    //encrypt plain text using caesar cipher
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

    //decrypt plain text using caesar cipher
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

    //cracking caesar cipher using brute force attack
    private static void crackBruteForce(String ciphertext) {
        System.out.println("#######     Cracking Caesar Cipher using brute force attack     #######");
        for (int key = 1; key < ASCII_LENGTH; key++) {
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("using key: " + key + " : " + decryptedText);
        }
    }

    //cracking caesar cipher using frequency analysis attack
    private static void crackFrequencyAnalysis(String ciphertext) {
        int[] keyScores = new int[ASCII_LENGTH];
        System.out.println("#######     Cracking Caesar Cipher using frequency analysis attack      #######");
        for (int key = 1; key < ASCII_LENGTH; key++) {
            String decryptedText = decrypt(ciphertext, key);
            keyScores[key] = calculateFrequencyAnalisisScore(decryptedText);
        }
        int key = maxFrequencyKey(keyScores);
        System.out.println("The key found using frequency analysis attack is: " + key);
        System.out.println("message found: " + decrypt(ciphertext, key));
        System.out.println(DELIMITER);
    }

    //calculating the score of a given plain text using the most common characters frequency score
    // in the english language
    private static int calculateFrequencyAnalisisScore(String text) {
        String mostFrequentCharactersInEnglishLanguage = "aAeEiIoOnNtT";
        int frequencyScore = 0;
        for (int i = 0; i < text.length(); i++) {
            if (mostFrequentCharactersInEnglishLanguage.contains(text.charAt(i)+"")) {
                frequencyScore++;
            }
        }
        return frequencyScore;
    }

    //calculating the index of the most frequent character
    private static int maxFrequencyKey(int[] frequencyArray) {
        int max = -1;
        int index = -1;
        int previousMax = -1;
        int previousMaxIndex = -1;

        for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] > max) {
                previousMax = max;
                previousMaxIndex = index;
                max = frequencyArray[i];
                index = i;
            }
        }
        return index;
    }

    //cracking caesar cipher using fast frequency analysis by analysing the most used character in the cipher text
    //which is mapped to the space character
    private static void fastFrequencyAnalysis(String ciphertext) {
        System.out.println("#######     Cracking Caesar Cipher using fast frequency analysis attack      #######");
        final char SPACE = ' ';
        int spaceIndex = (int) SPACE;
        int[] asciiFrequency = new int[ASCII_LENGTH];
        for (int i = 0; i < ciphertext.length(); i++) {
           char character = ciphertext.charAt(i);
           int charIndex = (int) character;
           asciiFrequency[charIndex]++;
        }
        int mostFrequentCipherCharacterIndex = maxFrequencyKey(asciiFrequency);
        char mostFrequentCipherCharacter = (char) mostFrequentCipherCharacterIndex;
        System.out.println("the most frequent cipher character is: " + mostFrequentCipherCharacter);
        int key = Math.floorMod(mostFrequentCipherCharacter - spaceIndex, ASCII_LENGTH);
        System.out.println("The key found using fast frequency analysis attack is: " + key);
        System.out.println("message found: " + decrypt(ciphertext, key));
        System.out.println(DELIMITER);
    }
}
