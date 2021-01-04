package com.algorithmsImpl.sumSubarray;

public class SumSubarray {

    //蛮力法1 O3
    public static String sumSubarray1(int[] arr){
        int sum=0,left_i=0,right_i=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr.length;j++){
                int tempSum=0;
                for(int k=i;k<=j;k++){
                    tempSum=tempSum+arr[k];
                    System.out.println(arr[k]+" ");
                    if(tempSum>sum){
                        sum=tempSum;
                        left_i=i;
                        right_i=k;
                    }
                }
            }
        }
        return ("sum --> "+ sum+ " left_i --> "+ left_i+" right_i --> "+ right_i);
    }

    //蛮力法2 O2
    public static String sumSubarray2(int[] arr){
        int sum=0,left_i=0,right_i=0;
        for(int i=0;i<arr.length;i++){
            int temp=0;
            for(int j=i;j<arr.length;j++){
                temp=temp+arr[j];
                if(temp>sum){
                    sum=temp;
                    left_i=i;
                    right_i=j;
                }
            }
        }
        return ("sum --> "+ sum+ " left_i --> "+ left_i+" right_i --> "+ right_i);
    }

    //分治法 nlogn
    public static SubArray sumSubarray3(int[] arr,int lo,int hi){
        if(lo==hi) return new SubArray(lo,hi,arr[lo]);
        else{
            int mid =(lo+hi)/2;
            //devide into smaller array until only one element in array
            SubArray subLeftSum=sumSubarray3(arr,lo,mid);
            SubArray subRightSum=sumSubarray3(arr,mid+1,hi);

            //get sub max array
            SubArray subCrossSum=getMaxSubArray(arr,lo,mid,hi);

            if(subLeftSum.subSum>=subCrossSum.subSum && subLeftSum.subSum>=subRightSum.subSum){
                return subLeftSum;
            }else if(subRightSum.subSum>=subCrossSum.subSum && subRightSum.subSum>=subLeftSum.subSum){
                return subRightSum;
            }else{
                return subCrossSum;
            }
        }

    }

    public static SubArray getMaxSubArray(int[] arr, int lo,int mid,int hi){
        int leftsum = Integer.MIN_VALUE;
        int left_i=0;
        int sum=0;
        for(int i=mid;i>=lo;i--){
            sum=sum+arr[i];
            if(sum>leftsum){
                leftsum=sum;
                left_i=i;
            }
        }

        int rightsum = Integer.MIN_VALUE;
        int right_i=0;
        sum=0;
        for(int i=mid+1;i<=hi;i++){
            sum=sum+arr[i];
            if(sum>rightsum){
                rightsum=sum;
                right_i=i;
            }
        }
        return new SubArray(left_i,right_i,(leftsum+rightsum));
    }

    public static class SubArray{
        private int lo;
        private int hi;
        private int subSum;
        public SubArray(int lo,int hi,int subSum){
            this.lo=lo;
            this.hi=hi;
            this.subSum=subSum;
        }

        public int getLo() {
            return lo;
        }

        public void setLo(int lo) {
            this.lo = lo;
        }

        public int getHi() {
            return hi;
        }

        public void setHi(int hi) {
            this.hi = hi;
        }

        public int getSubSum() {
            return subSum;
        }

        public void setSubSum(int subSum) {
            this.subSum = subSum;
        }
    }
}
