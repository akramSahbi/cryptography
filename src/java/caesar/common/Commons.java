package caesar.common;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Commons {
    public static final int ASCII_LENGTH = 256;
    public static final String DELIMITER = "####################################################################################";
    public static final String DICTIONARY_FILE_PATH = "/words_en.txt";

    //calculating the index of the most frequent character
    public static int maxFrequencyKey(int[] frequencyArray) {
        int max = -1;
        int index = -1;

        for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] > max) {
                max = frequencyArray[i];
                index = i;
            }
        }
        return index;
    }

    public static List<String> fillDictionary(String filename) {
        List<String> lines = null;
        try {
            Path textFile = Paths.get(Commons.class.getResource(filename).toURI());
            lines = Files.readAllLines(textFile, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
