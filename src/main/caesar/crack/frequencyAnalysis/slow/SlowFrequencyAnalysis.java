package main.caesar.crack.frequencyAnalysis.slow;

import java.util.Scanner;

import static main.caesar.algorithm.CaesarAlgorithm.decrypt;
import static main.caesar.algorithm.CaesarAlgorithm.encrypt;
import static main.caesar.common.Commons.*;

public class SlowFrequencyAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DELIMITER);
        System.out.println("Enter text to encrypt using ceasar cipher:");
        String plaintext = scanner.nextLine();
        System.out.println("Enter key numeric value:");
        String keytext = scanner.nextLine();
        int key = Integer.parseInt(keytext);
        String ciphertext = encrypt(plaintext, key);
        System.out.println(DELIMITER);
        crackFrequencyAnalysis(ciphertext);
        System.out.println(DELIMITER);
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
    public static int calculateFrequencyAnalisisScore(String text) {
        String mostFrequentCharactersInEnglishLanguage = "aAeEiIoOnNtT";
        int frequencyScore = 0;
        for (int i = 0; i < text.length(); i++) {
            if (mostFrequentCharactersInEnglishLanguage.contains(text.charAt(i)+"")) {
                frequencyScore++;
            }
        }
        return frequencyScore;
    }
}
