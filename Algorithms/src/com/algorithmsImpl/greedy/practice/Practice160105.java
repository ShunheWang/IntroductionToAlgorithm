package com.algorithmsImpl.greedy.practice;

/**
 * dp to solve problem
 */
public class Practice160105 {

    private static class Activity{
        private int sTime;
        private int fTime;
        private int value;
        public Activity(int sTime,int fTime,int value){
            this.sTime=sTime;
            this.fTime=fTime;
            this.value=value;
        }

        @Override
        public String toString() {
            return "Activity{" +
                    "sTime=" + sTime +
                    ", fTime=" + fTime +
                    ", value=" + value +
                    '}';
        }
    }

    private static void max_value_by_dp(Activity[] activities){
        int n=activities.length;
        int[] maxValues=new int[n]; //record max value
        int[] prevs=new int[n]; //record prev node
        //init first value
        maxValues[0]=0;
        prevs[0]=0;
        for (int i = 1; i < n; i++) {
            //find prev node
            for (int j = i-1; j >= 0; j--) {
                if (activities[j].fTime<=activities[i].sTime){
                    prevs[i]=j;
                    break;
                }
            }
            //compute current max_value
            if (activities[i].value+maxValues[prevs[i]]>=maxValues[i-1]){
                maxValues[i]=activities[i].value+maxValues[prevs[i]];
            }else{
                maxValues[i]=maxValues[i-1];
            }
        }

        //print out max value results
        print_results(maxValues,"value result");
        print_results(prevs,"prev node result");
        //print out choice activities
        print_choice_activities(maxValues,prevs,activities,n-1);
    }

    private static void print_choice_activities(int[] maxValues,int[] prevs,Activity[] activities,int n){
        if (n==0){
            return;
        }
        if (activities[n].value+maxValues[prevs[n]]==maxValues[n]){
            print_choice_activities(maxValues,prevs,activities,prevs[n]);
            System.out.print(" [activity "+n+"]");
        }else{
//            activities[n-1]==maxValues[n];
            print_choice_activities(maxValues,prevs,activities,(n-1));
        }
    }

    private static void print_results(int[] arr,String present){
        System.out.print(present+" --> ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //default activity has been sorted by increasing finish time
        //add first task to be easy computing
        int[] s={0,1,3,0,4,3,5,6,8};
        int[] f={0,4,5,6,7,8,9,10,11};
        int[] v={0,5,1,8,4,6,3,2,4};
        //init activity array
        Activity[] activities=new Activity[s.length];
        for (int i = 0; i < activities.length; i++) {
            activities[i]=new Activity(s[i],f[i],v[i]);
        }
        max_value_by_dp(activities);
    }
}
