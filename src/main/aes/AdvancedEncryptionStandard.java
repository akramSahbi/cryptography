package main.aes;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import static main.caesar.common.Commons.DELIMITER;

public class AdvancedEncryptionStandard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DELIMITER);
        System.out.println("Enter text to encrypt using advanced encryption standard cipher:");
        String plaintext = scanner.nextLine();
        System.out.print("Random Key generated value: ");
        SecretKey key = generateRandomKey();
        System.out.println(key.toString());
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted using advanced encryption standard algorithm: " + ciphertext);
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("decrypted back using advanced encryption standard cipher: " + decryptedText);
    }

    public static SecretKey generateRandomKey() {
        SecretKey key = null;
        try {
            key = KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String encrypt(String plaintext, SecretKey key) {
        byte[] cipherBytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plaintextBytes = plaintext.getBytes();
            cipherBytes = cipher.doFinal(plaintextBytes);
            // to represent cipher text as string
            cipherBytes = Base64.getEncoder().encode(cipherBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(cipherBytes);
    }

    public static String decrypt(String ciphertext, SecretKey key) {
        byte[] plaintextBytes = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] ciphertextBytes = ciphertext.getBytes();
            // to represent cipher text as string
            ciphertextBytes = Base64.getDecoder().decode(ciphertextBytes);
            plaintextBytes = cipher.doFinal(ciphertextBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        try {
            return new String(plaintextBytes, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
