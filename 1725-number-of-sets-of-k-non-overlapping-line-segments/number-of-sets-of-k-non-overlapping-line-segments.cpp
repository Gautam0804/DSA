class Solution {
public:
    int numberOfSets(int n, int k) {
        const long long MOD = 1e9 + 7;

        long long ans = 1;

        int N = n + k - 1;
        int R = 2 * k;

        for (int i = 1; i <= R; i++) {
            ans = ans * (N - R + i) % MOD;

            // Modular inverse of i using Fermat's Little Theorem
            long long inv = modPow(i, MOD - 2);

            ans = ans * inv % MOD;
        }

        return ans;
    }

private:
    long long modPow(long long a, long long b) {
        long long result = 1;

        while (b > 0) {
            if (b & 1) {
                result = result * a % 1000000007;
            }

            a = a * a % 1000000007;
            b >>= 1;
        }

        return result;
    }
};