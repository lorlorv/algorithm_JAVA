package leetcode;

import java.util.HashMap;
import java.util.Map;

public class _383_RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        //  magazine hashmap 에 넣고 ransomnote에서 하나씩 빼기

        char[] arr = new char[magazine.length()];
        arr = magazine.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            if (map.containsKey(c)) {
                int count = map.get(c);
                map.put(c, count + 1);
            } else {
                map.put(c, 1);
            }

        }

        char[] ransomNoteArr = ransomNote.toCharArray();
        int result = 0;
        for (char target : ransomNoteArr) {
            if (map.containsKey(target)) {
                // map에 있으면, count - 1, result + 1
                int count = map.get(target);

                if (count == 1) {
                    map.remove(target);
                } else {
                    map.replace(target, count - 1);
                }

                result++;
            }
        }

        return ransomNote.length() == result;

    }

}
