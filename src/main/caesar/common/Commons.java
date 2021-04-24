package src.main.caesar.common;

public class Commons {
    public static final int ASCII_LENGTH = 256;
    public static final String DELIMITER = "####################################################################################";

    //calculating the index of the most frequent character
    public static int maxFrequencyKey(int[] frequencyArray) {
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
}
