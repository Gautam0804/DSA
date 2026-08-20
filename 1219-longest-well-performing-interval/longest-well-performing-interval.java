class Solution {
    public int longestWPI(int[] hours) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int n = hours.length;
        int sum = 0;
        int longest = 0;

        for(int i=0;i<n;i++){
          

            if(hours[i]>8){
                sum++;
            } else {
                sum--;
            }
            if(sum>0){
                longest = i+1;
            }
            if(map.containsKey(sum-1)){
                longest = Math.max(longest,i-map.get(sum-1));
            }
           if(!map.containsKey(sum)){
            map.put(sum,i);
           }
        }
        
        
        return longest;
    }
}