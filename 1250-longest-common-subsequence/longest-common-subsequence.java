class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int dp[][]=new int[m+1][n+1];
        for(int []baba:dp)
        {
            Arrays.fill(baba,-1);
        }
        return solver(text1,text2,m-1,n-1,dp);
    }

    public int solver(String text1, String text2, int i, int j,int [][] dp){

        if(i<0 || j < 0){
            return 0;
        }

        if(text1.charAt(i) == text2.charAt(j)){
            return 1+solver(text1,text2, i-1, j-1,dp);
        }

        if(dp[i][j]!=-1)
        {
            return dp[i][j];
        }

        int skip=solver(text1,text2,i-1,j,dp);
        int take=solver(text1,text2,i,j-1,dp);
        
        return dp[i][j]=Math.max(skip,take);
    }
}