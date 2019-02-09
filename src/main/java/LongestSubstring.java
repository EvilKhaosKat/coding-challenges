import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * 3. Longest Substring Without Repeating Characters
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length of 3. Example 2:
 * <p>
 * Input: "bbbbb" Output: 1 Explanation: The answer is "b", with the length of 1. Example 3:
 * <p>
 * Input: "pwwkew" Output: 3 Explanation: The answer is "wke", with the length of 3. Note that the answer must be a
 * substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstring extends Solution {
    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();

        validateEqual(longestSubstring.lengthOfLongestSubstring("abcabcbb"), 3);
        validateEqual(longestSubstring.lengthOfLongestSubstring("bbbbb"), 1);
        validateEqual(longestSubstring.lengthOfLongestSubstring("pwwkew"), 3);
    }

    public int lengthOfLongestSubstring(String string) {
        int longestSubstringSize = 0;

        int length = string.length();

        for (int startPos = 0; startPos < length - 1; startPos++) {
            Set<Character> characters = new HashSet<>(); //TODO optimize size

            int currentSubstringLen = 0;
            for (int checkCharPos = startPos; checkCharPos < length; checkCharPos++) {
                char checkChar = string.charAt(checkCharPos);

                if (!characters.contains(checkChar)) {
                    characters.add(checkChar);
                } else {
                    currentSubstringLen = checkCharPos - startPos;

                    if (currentSubstringLen > longestSubstringSize) {
                        longestSubstringSize = currentSubstringLen;
                    }
                    break;
                }
            }
        }

        return longestSubstringSize;
    }
}