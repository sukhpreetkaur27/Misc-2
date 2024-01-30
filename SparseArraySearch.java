// GfG: https://www.geeksforgeeks.org/sparse-search/
/**
 * Given a sorted array of strings which is interspersed with empty strings, write a method to find the location of a given string.

Examples: 

Input : arr[] = {"for", "geeks", "", "", "", "", "ide", 
                      "practice", "", "", "", "quiz"}
          x = "geeks"
Output : 1

Input : arr[] = {"for", "geeks", "", "", "", "", "ide", 
                      "practice", "", "", "", "quiz"}, 
          x = "ds"
Output : -1
 */

 /**
  * Brute: Linear Search

  * TC: O(n * s)
  * SC: O(1)
  */
  /**
   * Optimal: Binary Search + Sliding Window
   * If mid == ""; find the first non-empty string on left or right; pick left or right half based on string comparison.
   * 
   * TC: O(s * log n)
   * SC: O(1)
   */

public class SparseArraySearch {
    
    public int search(String[] words, String key) {
        int n = words.length;
        int lo = 0;
        int hi = n - 1;
        int mid;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(key.compareTo(words[mid]) == 0) {
                return mid;
            }
            if(words[mid] == "") {
                // find left or right boundary
                int left = mid - 1;
                int right = mid + 1;
                while(left >= lo && right <= hi) {
                    if(words[left] == "" && words[right] == "") {
                        left--;
                        right++;
                        continue;
                    }
                    if(words[left] != "") {
                        if(key.compareTo(words[left]) == 0) {
                            return left;
                        } else if(key.compareTo(words[left]) < 0) {
                            hi = left - 1;
                        } else {
                            // > 0
                            lo = right;
                        }
                        break;
                    } else {
                        if(key.compareTo(words[right]) == 0) {
                            return right;
                        } else if(key.compareTo(words[right]) > 0) {
                            lo = right + 1;
                        } else {
                            // < 0
                            hi = left;
                        }
                        break;
                    }
                }
                if(left < lo && right > hi) {
                    break;
                }
            } else if(key.compareTo(words[mid]) < 0) {
                hi = mid - 1;
            } else {
                // > 0
                lo = mid + 1;
            }
        }
        return -1;
    }

}