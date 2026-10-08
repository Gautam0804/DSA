import java.util.Stack;
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int maxArea = 0;
        int nsr[] = new int[heights.length];
        int nsl[] = new int[heights.length];

        // Next smaller right
        Stack<Integer> s = new Stack<>();

        for(int i = heights.length-1;i>=0;i--){
            while(!s.isEmpty() && heights[s.peek()]>=heights[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nsr[i] = heights.length;
            }else{
                nsr[i] =  s.peek();
            }
            s.push(i);
        }

       // Next smaller left

        s = new Stack<>();

        for(int i = 0;i<heights.length;i++){
            while(!s.isEmpty() && heights[s.peek()]>=heights[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nsl[i] = -1;
            }else{
                nsl[i] =  s.peek();
            }
            s.push(i);
        }

        // current area : widtth = j-i-1 = nsr[i]-nsl[i]-1

        for(int i = 0;i<heights.length;i++){
            int height = heights[i];
            int width = nsr[i]-nsl[i]-1;
            int currArea = height*width;
            maxArea = Math.max(currArea, maxArea);
        }
      return maxArea;
        
    }
}