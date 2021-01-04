package com.company;


import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1438 {

    /**
     * 超时
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray1(int[] nums, int limit) {
        int longest = 1;
        for (int l = 1; l < nums.length; l++) {
            int left = 0;
            int right = left + l;
            while (right < nums.length) {
                PriorityQueue<Integer> minQueue = new PriorityQueue(Comparator.naturalOrder());
                PriorityQueue<Integer> maxQueue = new PriorityQueue(Comparator.reverseOrder());
                for (int i = left; i <= right; i++) {
                    minQueue.add(nums[i]);
                    maxQueue.add(nums[i]);
                }
                // find max value and min value
                int maxValue = maxQueue.peek();
                int minValue = minQueue.peek();
                if (maxValue - minValue <= limit) {
                    longest = maxQueue.size();
                    break;
                }
                left++;
                right++;
            }
        }
        return longest;
    }

    public int longestSubarray2(int[] nums, int limit) {

        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < nums.length && left < nums.length) {
            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);

            if (maxQueue.peek() - minQueue.peek() <= limit) {
                ans = Math.max(ans, right - left + 1);
                right++;
                continue;
            }

            maxQueue.remove((Integer) nums[left]);
            minQueue.remove((Integer) nums[left]);
            left++;
            right++;
        }
        return ans;
    }

    public int longestSubarray3(int[] nums, int limit) {
        PriorityQueue<Integer> minQueue = new PriorityQueue(Comparator.naturalOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue(Comparator.reverseOrder());
        int left = 0;
        int right = 0;
        int record = 1; // record current longestSubarray
        while (right < nums.length) {
            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);

            if (maxQueue.peek() - minQueue.peek() <= limit) {
                record = Math.max(record, right - left + 1);
                right++;
                continue;
            }
            // if > limit
            maxQueue.remove(nums[left]);
            minQueue.remove(nums[left]);
            left++;
            right++;
        }
        return record;
    }

    public static void main(String[] args) {
        // [8,2,4,7]
        // 4

        // [4,2,2,2,4,4,2,2]
        // 0

        int[] rantings = new int[]{4,2,2,2,4,4,2,2};
        System.out.println(new Solution1438().longestSubarray3(rantings,0));

    }
}