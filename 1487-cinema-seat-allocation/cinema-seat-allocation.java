import java.util.*;
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reversed seat row-wise
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        // Put every reversed seat into the corresponding row
        for(int[] seat:reservedSeats){
            int row = seat[0];
            int col = seat[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(col);
        }

        // Intially assume every row can fit 2 families

        int ans = (n-map.size())*2;

        // process only rows tha have reversed seats

        for(int row:map.keySet()){
            HashSet<Integer> seats = map.get(row);

            // check seats 2,3,4,5

            boolean left = true;

            for(int i=2;i<=5;i++){
                if(seats.contains(i)){
                    left = false;
                    break;
                }
            }
            // check seats 4,5,6,7

            boolean middle = true;
            for(int i=4;i<=7;i++){
                if(seats.contains(i)){
                    middle = false;
                    break;
                }
            }
            // check seats 6,7,8,9

            boolean right = true;
            for(int i=6;i<=9;i++){
                if(seats.contains(i)){
                    right = false;
                    break;
                }
            }

            // if both left and right are avilable,
            // we can put 2 families
            if(left && right){
                ans += 2;
            } 
            // otherwise, if any one group is avilable,
            // we can put 2 families

            else if(left || middle || right){
                ans += 1;
            }
        }
return ans;
        
    }
}