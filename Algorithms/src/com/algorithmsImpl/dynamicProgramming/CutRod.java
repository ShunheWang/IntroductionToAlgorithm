package com.algorithmsImpl.dynamicProgramming;


/**
 * Dynamic Programming in chapter 15 of introduction to algorithm
 */
public class CutRod {
    private static int countsNormal=0;
    private static int countsDP1=0;
    private static int countsDP2=0;
    //method 1: common recursive
    private static int cutRodByCommonRecursive(int p[],int n){
        if (n==0) {
            return 0;
        }
        int temp=Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            countsNormal++;
            temp=Math.max(temp,p[i]+cutRodByCommonRecursive(p,n-i));
        }
        return temp;
    }

    //method 2: use dp (top-down with memorization)
    private static int cutRodByDP1(int p[],int n){
        int[] temp=new int[n+1];
        for (int i = 0; i < n+1; i++) {
            temp[i]=Integer.MIN_VALUE;
        }
        return memoizedCutRod(p,n,temp);
    }

    private static int memoizedCutRod(int[] p, int n, int[] temp) {
        //sub problem has been computed --> use it
        if (temp[n]>=0) {
            return temp[n];
        }
        int t=Integer.MIN_VALUE;
        if (n==0) {
            t=0;
        }
        for (int i = 1; i <= n; i++) {
             countsDP1++;
            t=Math.max(t,p[i]+memoizedCutRod(p,n-i,temp));
        }

        //put result of sub problem into temp array
        temp[n]=t;
        return t;
    }

    //method 2: use dp (bottom-up with memorization)
    private static int cutRodByDP2(int p[],int n){
        int[] temp=new int[n+1];
        for (int i = 0; i < n+1; i++) {
            temp[i]=Integer.MIN_VALUE;
        }
        temp[0]=0;

        for (int i = 1; i <= n; i++) {
            int t=Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                countsDP2++;
                t=Math.max(t,p[j]+temp[i-j]);
            }
            temp[i]=t;
        }

        return temp[n];
    }

    //modified method 2: use dp and return maximum value and solution (bottom-up with memorization)
    private static void modifiedCutRodByDP2(int[] p,int n){
        int[] maximumValue=new int[n+1];
        int[] solution=new int[n+1];
        int counts=0;
        for (int i = 0; i < n+1; i++) {
            maximumValue[i]=Integer.MIN_VALUE;
            solution[i]=Integer.MIN_VALUE;
        }
        maximumValue[0]=0;
        solution[0]=0;
        for (int i = 1; i <= n; i++) {
            int t=Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                counts++;
                if(t<(p[j]+maximumValue[i-j])){
                    t=p[j]+maximumValue[i-j];
                    //store first part value in every cut
                    solution[i]=j;
                }
            }
            maximumValue[i]=t;
        }

        handleSolution(solution,n);
        System.out.println();
        System.out.println(maximumValue[n]);
        System.out.println("Counts --> "+counts);
    }

    private static void handleSolution(int[] s,int n){
        System.out.print("[");
        while (n > 0) {
            System.out.print(s[n]+" ,");
            n=n-s[n];
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        int[] p=new int[]{0,1,5,8,9,10,17,17,20,24,30};
        System.out.println(cutRodByCommonRecursive(p,4));
        System.out.println("Counts --> "+ countsNormal);
        System.out.println(cutRodByDP1(p,4));
        System.out.println("Counts --> "+ countsDP1);
        System.out.println(cutRodByDP2(p,4));
        System.out.println("Counts --> "+ countsDP2);

        modifiedCutRodByDP2(p,4);
    }
}
