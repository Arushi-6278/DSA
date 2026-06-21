class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;
        for (int cost : costs) {
            if (cost > maxCost) {
                maxCost = cost;
            }
        }

        int[] frequency = new int[maxCost + 1];
        for (int cost : costs) {
            frequency[cost]++;
        }

        int count = 0;
        for (int price = 1; price <= maxCost; price++) {
            if (frequency[price] == 0) {
                continue;
            }
            if (coins < price) {
                break;
            }
            
            long totalCostOfCurrentPrice = (long) price * frequency[price];
            if (coins >= totalCostOfCurrentPrice) {
                count += frequency[price];
                coins -= totalCostOfCurrentPrice;
            } else {
                int buyable = coins / price;
                count += buyable;
                break;
            }
        }

        return count;
    }
}