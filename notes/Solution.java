package notes;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        //[8,9,3,5,1,3],3
        new Solution().maxProfit(new int[]{8, 9, 3, 5, 1, 3}, 3);
    }

    public int maxProfit (int[] prices, int k) {
        // write code here
        int[][][] dp = new int[prices.length][k][2];
        for (int j = 0; j < k; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = 0 - prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = 0 - prices[i];
            for (int j = 1; j < k; j++) {
                int a = dp[i - 1][j][0];
                int b = dp[i - 1][j - 1][1] + prices[i];
                int c = dp[i - 1][j][1];
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                int d = dp[i][j][0] - prices[i];
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i][j][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][k - 1][0];
    }
}   