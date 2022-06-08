package com.gh.tone.lc;

public class Q287 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 4};
        System.out.println(findDuplicate(arr));
        System.out.println(findDuplicate2(arr));
    }

    public static int findDuplicate(int[] arr) {
        int len = arr.length;
        int start = 1;
        int end = len - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int counter = 0;
            for (int num : arr) {
                if (num <= mid) {
                    counter++;
                }
            }
            if (counter > mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * 快慢指针
     * @param nums
     * @return
     */
    public static int findDuplicate2(int[] nums) {
        int fast = nums[nums[0]];
        int slow = nums[0];
        while (fast != slow) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        slow = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
