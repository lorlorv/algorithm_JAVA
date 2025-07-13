package leetcode;

import java.util.HashMap;
import java.util.Map;

public class _383_RansomNote_2 {
    public boolean canConstruct(String ransomNote, String magazine) {
        // alpha 배열로 다시 풀어보기

        int[] alpha = new int[26];

        for (int i = 0; i < magazine.length(); i++){
            int alphaIndex = magazine.charAt(i) - 'a';

            alpha[alphaIndex]++;
        }

        for (int i = 0; i < ransomNote.length(); i++){
            int alphaIndex = ransomNote.charAt(i) - 'a';

            if (alpha[alphaIndex] > 0){
                alpha[alphaIndex]--;
            } else {
                return false;
            }
        }
        return true;
    }
}
