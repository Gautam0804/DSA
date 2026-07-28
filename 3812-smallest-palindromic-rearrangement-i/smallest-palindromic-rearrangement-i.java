class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for(char c: s.toCharArray()){
            count[c-'a']++;
        }
        StringBuilder left = new StringBuilder();
        char middle = 0;

        for(int i=0;i<26;i++){
            int half = count[i]/2;

            while(half-->0){
                left.append((char)('a'+i));
            }
            if(count[i]%2==1){
                middle = (char)('a'+i);
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if(middle !=0){
            ans.append(middle);
        }
        ans.append(new StringBuilder(left).reverse());
        return ans.toString();
        
    }
}