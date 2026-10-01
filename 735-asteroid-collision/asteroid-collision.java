class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int[] stack = new int[asteroids.length];
        int top = -1;

        for (int asteroid : asteroids) {
            boolean alive = true;

            while (alive && asteroid < 0 && top >= 0 && stack[top] > 0) {
                if (stack[top] < -asteroid) {
                    // The incoming asteroid destroys the stack's top asteroid.
                    top--;
                } else if (stack[top] == -asteroid) {
                    // Both asteroids are destroyed.
                    top--;
                    alive = false;
                } else {
                    // The incoming asteroid is destroyed.
                    alive = false;
                }
            }

            if (alive) {
                stack[++top] = asteroid;
            }
        }

        return java.util.Arrays.copyOf(stack, top + 1);
    }
}