class Solution {
    public int mctFromLeafValues(int[] arr) {
        int[] stack = new int[arr.length + 1];
        int top = 0;
        stack[top] = Integer.MAX_VALUE; // Sentinel

        int cost = 0;

        for (int value : arr) {
            while (stack[top] <= value) {
                int middle = stack[top--];
                cost += middle * Math.min(stack[top], value);
            }
            stack[++top] = value;
        }

        while (top > 1) {
            int middle = stack[top--];
            cost += middle * stack[top];
        }

        return cost;
    }
}