package main.caesar.crack.dictionary;

import main.caesar.common.Commons;
import main.caesar.crack.bruteforce.BruteForceCaesar;

import java.util.List;
import java.util.Scanner;

import static main.caesar.common.Commons.ASCII_LENGTH;
import static main.caesar.algorithm.CaesarAlgorithm.decrypt;
import static main.caesar.algorithm.CaesarAlgorithm.encrypt;
import static main.caesar.common.Commons.*;

public class DictionaryAttackCaesar {
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
        dictionaryAttack(ciphertext);
    }

    public static void dictionaryAttack(String ciphertext) {
        List<String> dictionary = Commons.fillDictionary(Commons.DICTIONARY_FILE_PATH);
        String[] possibleDecryptedTexts = BruteForceCaesar.crackBruteForce(ciphertext);
        int[] scoreForEachKey = new int[ASCII_LENGTH];
        for (int key = 1; key < possibleDecryptedTexts.length; key++) {
            String[] wordsInDecryptedText = possibleDecryptedTexts[key].split(" ");
            int score = 0;
            for (String wordInDecryptedText : wordsInDecryptedText) {
                if (dictionary.contains(wordInDecryptedText)) {
                    score++;
                }
            }
            scoreForEachKey[key] = score;
        }
        int keyThatContainsMostWords = maxFrequencyKey(scoreForEachKey);
        System.out.println("The key found using frequency analysis attack is: " + keyThatContainsMostWords);
        System.out.println("message found: " + decrypt(ciphertext, keyThatContainsMostWords));
        System.out.println(DELIMITER);
    }
}
