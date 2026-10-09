
import java.util.*;

class Solution {
    public int trap(int[] H) {
        int n = H.length;
        int ans = 0;

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && H[i] > H[st.peek()]) {
                int bottom = st.pop();

                if (st.isEmpty()) {
                    break;
                }

                int width = i - st.peek() - 1;

                int height = Math.min(H[i], H[st.peek()])
                           - H[bottom];

                ans += width * height;
            }

            st.push(i);
        }

        return ans;
    }
}
