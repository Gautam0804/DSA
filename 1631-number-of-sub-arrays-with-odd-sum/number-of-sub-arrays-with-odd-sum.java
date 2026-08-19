class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1000000007;
        int[] count = new int[2];
        int prefix = 0;
        long answer = 0;
        count[0] = 1;

        for (int num : arr) {
            prefix += num;
            int parity = prefix % 2;
            if (parity == 0) {
                answer += count[1];
            } else {
                answer += count[0];
            }
            count[parity]++;
            answer %= MOD;
        }
        return (int)answer;
    }
}