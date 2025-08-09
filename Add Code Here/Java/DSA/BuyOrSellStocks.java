public class BuyOrSellStocks {

    public static int BuyOrSell(int prices[]){
        int buyPrice=Integer.MAX_VALUE;
        int maxprofit=0;
        for(int i=0;i<prices.length;i++){
            if (buyPrice < prices[i]){
                int profit = prices[i] - buyPrice;
                maxprofit=Math.max(maxprofit,profit);
            }
            else {
                buyPrice=prices[i];
            }
        }return maxprofit;



    }
    public static void main(String[] args){
        int prices[]={1500,6500,4000,800,6000,3555,4444};
        System.out.println(BuyOrSell(prices));


    }
}
