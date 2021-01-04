package com.algorithmsImpl.greedy;

public class Knapsack01 {

    private static class Item{
        private int weight;
        private int value;
        public Item(int weight,int value){
            this.weight=weight;
            this.value=value;
        }
    }

    private static Item[] initItems(int[] itemWeights,int[] itemValues){
        Item[] items=new Item[itemWeights.length];
        for (int i = 0; i < itemWeights.length; i++) {
            items[i]= new Item(itemWeights[i],itemValues[i]);
        }
        return items;
    }

    private static void solution_by_dp(int[] itemWeights,int[] itemValues,int knapsackMaxWeight){
        Item[] items=initItems(itemWeights,itemValues);
        int[][] stolenValues=new int[items.length][knapsackMaxWeight+1];

        //1. init
        for (int j = 0; j < knapsackMaxWeight; j++) {
            stolenValues[0][j]=0;
        }
        for (int i = 0; i < items.length; i++) {
            stolenValues[i][0]=0;
        }

        for (int i = 1; i < items.length; i++) {    // every item
            for (int j = 1; j <= knapsackMaxWeight; j++) {  //knapsack weight from 0 to max
                //two options: 1. choose current item, 2. dont choose
                //1. choose
                int choiceValue=0;
                if (items[i].weight<=j){
                    //compute rest weight of knapsack
                    int restKnapsackWeight=j-items[i].weight;
                    int restKnapsackValue=stolenValues[i-1][restKnapsackWeight];
                    choiceValue=items[i].value+restKnapsackValue;
                }
                //2. dont choose
                //compare
                if (choiceValue>=stolenValues[i-1][j]){
                    stolenValues[i][j]=choiceValue;
                }else{
                    stolenValues[i][j]=stolenValues[i-1][j];
                }
            }
        }

        //print max_value
        System.out.println("Knapsack max value --> "+stolenValues[stolenValues.length-1][stolenValues[0].length-1]);
        //print item result
        print_items(stolenValues,items,stolenValues.length-1,stolenValues[0].length-1);
//        for (int i = 0; i < stolenValues.length; i++) {
//            for (int j = 0; j < stolenValues[i].length; j++) {
//                System.out.print(stolenValues[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    private static void print_items(int[][] stolenValue, Item[] items, int i, int j){
        if (i==0||j==0){
            return;
        }
        int restKnapsackWeight=j-items[i].weight;
        if (stolenValue[i][j]==items[i].value+stolenValue[i-1][restKnapsackWeight]){
            print_items(stolenValue,items,i-1,restKnapsackWeight);
            System.out.print("[item "+i+"] ");
        }else{
            print_items(stolenValue,items,i-1,j);
        }
    }

    public static void main(String[] args) {
        //default add item[0] --> weight=0,value=0
        int[] itemWeights=new int[]{0,10,20,30};
        int[] itemValues=new int[]{0,60,100,120};
        int knapsackWeight=50;
        solution_by_dp(itemWeights,itemValues,knapsackWeight);
    }
}
