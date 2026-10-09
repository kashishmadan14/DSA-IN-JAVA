import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        // If the main string is shorter than the required total length, return early
        if (sLen < totalLen) {
            return result;
        }

        // Build the frequency map for the words array
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Run the sliding window with 'wordLen' different starting offsets
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> currentFreq = new HashMap<>();

            // Slide the right boundary by wordLen steps
            while (right + wordLen <= sLen) {
                // Extract the next word candidate
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordFreq.containsKey(word)) {
                    currentFreq.put(word, currentFreq.getOrDefault(word, 0) + 1);
                    count++;

                    // If a word's count exceeds its required frequency, shrink from the left
                    while (currentFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // If all words are matched perfectly, record the left index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Encountered an invalid word: reset the current window entirely
                    currentFreq.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}
