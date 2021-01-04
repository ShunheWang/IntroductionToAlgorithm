package com.algorithmsImpl.greedy.practice;

import java.util.ArrayList;

public class Practice160104 {

    //1. sort by increasing finish time --> quick sort (O(nlogn))
    private static void quickSort(int[] s,int[]f, int i, int j){
        if (i<j){
            int q=partition(s,f,i,j);
            quickSort(s,f,i,q-1);
            quickSort(s,f,q+1,j);
        }
    }


    //2.create inner obj
    private static class Activity{
        private int sTime;
        private int fTime;
        private int roomNum;
        private boolean isArranged;
        public Activity(int sTime,int fTime){
            this.sTime=sTime;
            this.fTime=fTime;
            this.roomNum=-1;
            this.isArranged=false;
        }

        @Override
        public String toString() {
            return "Activity{" +
                    "sTime=" + sTime +
                    ", fTime=" + fTime +
                    ", roomNum=" + roomNum +
                    ", isArranged=" + isArranged +
                    '}';
        }
    }

    //3.interval graph color problem
    private static void activity_arrangement_problem_by_greedy(int[] s,int[] f){
        quickSort(s,f,0,s.length-1);    //order by increasing finish time

        //3.1 init activity obj
        Activity[] activities=new Activity[s.length];
        ArrayList<Integer> roomTimes=new ArrayList<Integer>();  //record every classroom's last activity finish time
        for (int i = 0; i < s.length; i++) {
            Activity activity=new Activity(s[i],f[i]);
            activities[i]=activity;
        }
        //3.2 arrange first activity
        roomTimes.add(activities[0].fTime);    //init --> first activity arranged into room 0
        //update activity 0 status
        activities[0].roomNum=0;
        activities[0].isArranged=true;
        //3.3 start computing min room
        for (int i = 1; i < activities.length; i++) {   //check every activity
            for (int j = 0;j < roomTimes.size(); j++){
                if (activities[i].sTime >= roomTimes.get(j)){
                    roomTimes.set(j,f[i]);  //update room time
                    //update activity status
                    activities[i].roomNum=j;
                    activities[i].isArranged=true;
                }
            }
            //if current activity cannot be arranged into worked room --> it should allocate new free room for it.
            if (activities[i].isArranged==false){
                roomTimes.add(f[i]);    //new free room
                //update activity status
                activities[i].roomNum=roomTimes.size()-1;
                activities[i].isArranged=true;
            }
        }
        int roomCounts=roomTimes.size();   //room 0 has been initialed

        //print result
        System.out.println("room counts --> "+ roomCounts);
        System.out.println("activity allocation: ");
        for (Activity activity:
             activities) {
            System.out.println("["+activity.toString()+"]");
        }
    }


    /**
     * based on finish time
     * @param s
     * @param f
     * @param i
     * @param j
     * @return
     */
    private static int partition(int[] s, int[] f, int i, int j){
        int pivotElement=f[j];
        int first=i-1;
        for (int k = i; k < j ; k++) {
            if (f[k]<=pivotElement){
                first=first+1;
                swap(f,first,k);
                swap(s,first,k);
            }
        }
        swap(f,first+1,j);
        swap(s,first+1,j);
        return (first+1);
    }

    private static void swap(int[]arr, int x, int y){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }

    public static void main(String[] args) {
        int[] s = {1,3,0,5,3,5,6,8,8,2,12};//start time
        int[] f = {4,5,6,7,9,9,10,11,12,14,16};//finish time
        //quickSort(s,f,0,s.length-1);
        activity_arrangement_problem_by_greedy(s,f);
    }
}
