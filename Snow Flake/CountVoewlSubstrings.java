import java.util.*;

public class CountVoewlSubstrings {
    public static void main(String[] args) {
        
    } 
    public int countVowelSubstrings(String word) {
        // count all valid strings
        int count = 0;
        // the last seen position of each vowels
        Map<Character, Integer> lastSeen = new HashMap<>(Map.of('a',-1,'e',-1,'i',-1,'o',-1,'u',-1));
        for (int i=0, lastInvalidPos=-1;i<word.length();i++) {
            // if the current is a valid Character
            if (lastSeen.containsKey(word.charAt(i))) {
                // update the last seen position in lastSeen hash set
                lastSeen.put(word.charAt(i), i);
                // compare the most left most left valid voewl with the last Invalid position
                // if the invalid position is on the right on the left most valid voewl 
                // -> not valid
                // -> count += 0
                // else 
                // -> valid
                // -> all the characters between the left most and lastInvalidPos 
                //    can be part of one of the valid string
                // -> count += Collections.min(lastSeen.values())-lastInvalidPos
                // System.out.println(Math.max(Collections.min(lastSeen.values())-lastInvalidPos,0));
                count += Math.max(Collections.min(lastSeen.values())-lastInvalidPos,0);
            } else {
                lastInvalidPos = i;
            }
        }
        return count;
    }
}

