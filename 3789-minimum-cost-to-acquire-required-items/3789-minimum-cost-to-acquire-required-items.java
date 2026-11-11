class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Find the common requirements that need both type 1 and type 2
        long common = Math.min(need1, need2);
        
        // Cost to satisfy one unit of both requirements
        long costCommonUnit = Math.min((long) costBoth, (long) cost1 + cost2);
        
        // Remaining requirements after fulfilling the common part
        long rem1 = need1 - common;
        long rem2 = need2 - common;
        
        // Cost to satisfy remaining type 1 and type 2 requirements individually
        long costRem1 = Math.min((long) costBoth, (long) cost1);
        long costRem2 = Math.min((long) costBoth, (long) cost2);
        
        // Total minimum cost
        return (common * costCommonUnit) + (rem1 * costRem1) + (rem2 * costRem2);
    }
}