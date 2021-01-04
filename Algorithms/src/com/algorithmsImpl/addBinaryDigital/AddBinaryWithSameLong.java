package com.algorithmsImpl.addBinaryDigital;

public class AddBinaryWithSameLong {

    public static int[] addBinary(int[] m, int[] n){
        int[]a=m;
        int[]b=n;
        int al=m.length;
        int bl=n.length;
        int[] c=null;
        int addLong=0;
        //decide output array long
        if(al>bl){
            c=new int[al+1];
            b=complementByZero(b,al);
            addLong=al;
        }else{
            c=new int[bl+1];
            a=complementByZero(a,bl);
            addLong=bl;
        }

//        method 1:
//        //add array a and b from right to left
//        int carry=0;
//        int i;
//        for(i=addLong-1; i>=0;i--){
//            if(a[i]+b[i]+carry==0){
//                c[i+1]=0;
//                carry=0;
//            }else if(a[i]+b[i]+carry==1){
//                c[i+1]=1;
//                carry=0;
//            }else if(a[i]+b[i]+carry==2){
//                c[i+1]=0;
//                carry=1;
//            }else if(a[i]+b[i]+carry==3){
//                c[i+1]=1;
//                carry=1;
//            }
//        }
//        c[i+1]=carry;



//        method 2:
        int carry=0;
        int i;
        for(i=addLong-1;i>=0;i--){
            c[i]=(a[i]+b[i]+carry)%2;
            carry=(a[i]+b[i]+carry)/2;
        }
        c[i+1]=carry;
        return c;
    }

    //complement by zero to ensure that two array length is same
    public static int[] complementByZero(int[] arr,int para){
        int[] newArrays=new int[para];
        int zeroTimes=para-arr.length;
        for(int i=0;i<zeroTimes;i++){
            newArrays[i]=0;
        }
        for(int i=0;i<arr.length;i++){
            newArrays[i+zeroTimes]=arr[i];
        }
        return newArrays;
    }
}
