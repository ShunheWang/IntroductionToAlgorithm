package com.company;
import java.util.HashMap;

public class Solution1 {

    /**
     * 暴力解
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] resArray = new int[0];
        boolean[] records = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++) {
            records[i] = false;
        }

        for(int i = 0; i < nums.length; i++) {
            int firstNum = nums[i];
            for(int j =i+1; j < nums.length; j++) {
                int secondNum = nums[j];
                if ((firstNum + secondNum) == target && !records[i] && !records[j]){
                    int[] temp = new int[resArray.length + 2];
                    System.arraycopy(resArray,0,temp,0,resArray.length);
                    temp[resArray.length] = i;
                    temp[resArray.length + 1] = j;
                    resArray = temp;
                    records[i] = true;
                    records[j] = true;
                }
            }
        }
        return resArray;
    }


    /**
     * hashmap
     * hashmap 在查找时 tc--> O(1) cuz hashmap 是数组+链表结构 .containKey 实际上是去hash(current-key) 然后查找数组中对应的位置 e.x. array[1] or array[hash(current-key)]
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target-nums[i])) {
                return new int[]{i,hashMap.get(target-nums[i])};
            }
            hashMap.put(nums[i],i);
        }
        return null;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        Solution1 solution = new Solution1();
        int[] res1= solution.twoSum1(nums,6);
        int[] res2= solution.twoSum2(nums,6);
        for (int i = 0; i < res1.length; i++) {
            System.out.print(res1[i]);
        }
        for (int i = 0; i < res2.length; i++) {
            System.out.print(res2[i]);
        }
    }
}