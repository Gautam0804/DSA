class Solution {

    public int[] findEvenNumbers(int[] digits) {

        int[] count = new int[10];

        for (int digit : digits) {
            count[digit]++;
        }

        boolean[] used = new boolean[1000];

        List<Integer> list = new ArrayList<>();

   
        for (int i = 1; i <= 9; i++) {

            if (count[i] == 0) {
                continue;
            }

            count[i]--;

            for (int j = 0; j <= 9; j++) {

                if (count[j] == 0) {
                    continue;
                }

                count[j]--;

                for (int k = 0; k <= 8; k += 2) {

                    if (count[k] == 0) {
                        continue;
                    }

                    int num = i * 100 + j * 10 + k;

                    if (!used[num]) {
                        used[num] = true;
                        list.add(num);
                    }
                }

                count[j]++;
            }

            count[i]++;
        }

    
        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}