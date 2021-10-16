package caesar.crack.frequencyAnalysis.fast;

import java.util.Scanner;

import static caesar.algorithm.CaesarAlgorithm.decrypt;
import static caesar.algorithm.CaesarAlgorithm.encrypt;
import static caesar.common.Commons.*;

public class FastFrequencyAnalysis {
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
        fastFrequencyAnalysis(ciphertext);
        System.out.println(DELIMITER);
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
