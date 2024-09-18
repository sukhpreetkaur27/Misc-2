/**
 * Find all subsets -> product of each subset -> add it to the global sum
 * TC: O(2^n) ~ exponential
 * SC: O(n)
 * <p>
 * Approach 2:
 * Use Maths
 * <p>
 * eg 1:
 * [a,b]
 * = a + b + ab
 * = 1 + a + b + ab - 1
 * extract (1 + a)
 * = 1 + a + b (1 + a) - 1
 * = (1 + a) (1 + b) - 1
 * <p>
 * eg 2:
 * [a,b,c]
 * = a + b + c + ab + ac + bc + abc
 * = 1 + a + b + c + ab + ac + bc + abc - 1
 * extract (1 + a)
 * = 1 + a + b (1 + a) + c (1 + a) + bc (1 + a) - 1
 * = (1 + a) (1 + b + c + bc) - 1
 * = (1 + a) (1 + b + c (1 + b)) - 1
 * = (1 + a) (1 + b) (1 + c) - 1
 */
public class SumOfProductOfSubsets {

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public int sum(int[] nums) {
        int sum = 1;
        for (int i : nums) {
            sum *= (1 + i);
        }
        return sum - 1;
    }

}
