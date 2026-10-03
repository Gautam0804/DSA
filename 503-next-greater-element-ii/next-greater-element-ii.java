import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {

        Stack<Integer> st = new Stack<>();

        int n = nums.length;

        int[] ans = new int[n];

        for(int i = 2 * n - 1; i >= 0; i--) {

            int index = i % n;

            while(!st.empty() && st.peek() <= nums[index]) {
                st.pop();
            }

            if(st.empty()) {
                ans[index] = -1;
            } else {
                ans[index] = st.peek();
            }

            st.push(nums[index]);
        }

        return ans;
    }
}