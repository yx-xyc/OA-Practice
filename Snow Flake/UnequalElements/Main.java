import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Arrays;

public class Main {
    int MAX_VOWELS;
    int res;
    private static final int C = 21;
    private static final int V = 5;
    public static void main(String[] args) {
        Main program = new Main();
        // Test Case 1
        int wordLen = 1;
        int maxVowels = 1;
        System.out.println("Test Case 1: wordLen = " + wordLen + ", maxVowels = " + maxVowels +
                           " => Expected: 26, Got: " + program.getNumOfUniqueWords(wordLen, maxVowels));

        // Test Case 2
        wordLen = 4;
        maxVowels = 1;
        System.out.println("Test Case 2: wordLen = " + wordLen + ", maxVowels = " + maxVowels +
                           " => Expected: 730, Got: " + program.getNumOfUniqueWords(wordLen, maxVowels));

        // Test Case 3
        wordLen = 4;
        maxVowels = 2;
        System.out.println("Test Case 3: wordLen = " + wordLen + ", maxVowels = " + maxVowels +
                           " => Expected: Value, Got: " + program.getNumOfUniqueWords(wordLen, maxVowels));

    }

    public int getNumOfUniqueWords(int wordLen, int maxVowels) {
        MAX_VOWELS = maxVowels;
        res = 0;
        int[] word = new int[wordLen];
        Arrays.fill(word, C);
        backtrack(word, 0, maxVowels);
        return res;
    }
    private void backtrack(int[] word, int index, int maxVowels) {
        // base case
        if (index == word.length) {
            int count = 1;
            for(int num : word) {
                count *= num;
            }
            res += count;
            return;
        }
        if (maxVowels == 0) {
            backtrack(word, index + 1, MAX_VOWELS);
            return;
        }
        word[index] = V;
        backtrack(word, index + 1, maxVowels - 1);
        word[index] = C;
        backtrack(word, index + 1, maxVowels);
    }
}
