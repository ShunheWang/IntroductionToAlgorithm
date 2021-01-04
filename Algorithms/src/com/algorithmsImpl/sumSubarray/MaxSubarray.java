package com.algorithmsImpl.sumSubarray;

public class MaxSubarray
{
    public static void main(String[] args)
    {
        int[] array = { 1, -2, -3, 4, 5, 6, -3, 4, -11 };

        int[] result = MaxSubarray.findMaxSubarray(array, 0, array.length - 1);

        for (int i = 0; i < result.length; i++)
        {
            System.out.println(result[i]);

        }

    }

    public static int[] findMaxCrossingSubarray(int[] array, int low, int mid, int high)
    {

        int leftsum = Integer.MIN_VALUE;
        int sum1 = 0;
        int maxleft = 0;

        for (int i = mid; i >= 0; i--)
        {
            sum1 = sum1 + array[i];

            if (sum1 > leftsum)
            {
                leftsum = sum1;
                maxleft = i;
            }
        }

        int rightsum = Integer.MIN_VALUE;
        int sum2 = 0;
        int maxright = 0;

        for (int j = mid + 1; j <= high; j++)
        {
            sum2 = +array[j];

            if (sum2 > rightsum)
            {
                rightsum = sum2;
                maxright = j;
            }
        }
        int[] result = new int[3];

        result[0] = maxleft;
        result[1] = maxright;
        result[2] = leftsum + rightsum;

        return result;
    }

    public static int[] findMaxSubarray(int[] array, int low, int high)
    {
        if (high == low)
        {
            int[] result = { low, high, array[low] };
            return result;
        } else
        {
            int mid = (int) Math.floor((low + high) / 2);
            int[] left = new int[3];
            int[] right = new int[3];
            int[] cross = new int[3];

            left = findMaxSubarray(array, low, mid);
            right = findMaxSubarray(array, mid + 1, high);
            cross = findMaxCrossingSubarray(array, low, mid, high);

            if (left[2] >= right[2] && left[2] >= cross[2])
                return left;
            else if (right[2] >= left[2] && right[2] >= cross[2])
                return right;
            else
                return cross;
        }

    }

}