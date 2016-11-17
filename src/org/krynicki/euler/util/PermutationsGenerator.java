package org.krynicki.euler.util;

/**
 * Created by K on 2016-11-17.
 */
public class PermutationsGenerator {
    public static int convert(int[] values, int start, int len) {
        int result = 0;
        for (int i = start; i < start + len && i < values.length; i++) {
            result *= 10;
            result += values[i];
        }
        return result;
    }

    public void next(int[] nums) {
        int p = nums.length - 2;
        while (p >= 0 && nums[p] > nums[p + 1]) {
            p--;
        }

        if (p < 0) {
            reverse(nums, 0, nums.length - 1); // closes loop
            return;
        }

        int q = nums.length - 1;
        while (nums[q] < nums[p]) {
            q--;
        }

        swap(nums, p, q);
        reverse(nums, p + 1, nums.length - 1);
    }

    public void prv(int[] nums) {
        int p = nums.length - 2;
        while (p >= 0 && nums[p] < nums[p + 1]) {
            p--;
        }

        if (p < 0) {
            reverse(nums, 0, nums.length - 1); // closes loop
            return;
        }

        int q = nums.length - 1;
        while (nums[q] > nums[p]) {
            q--;
        }

        swap(nums, p, q);
        reverse(nums, p + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int p, int q) {
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }
}
