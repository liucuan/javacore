package algorithm;

import java.util.Arrays;

/**
 * 计数排序 花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max
 *
 * 开辟一块新的空间创建新的数组 B，长度为 ( max - min + 1)
 *
 * 数组 B 中 index 的元素记录的值是 A 中某元素出现的次数
 *
 * 最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数
 */
public class CountingSort {

    public static int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return countingSort(arr);
    }

    private static int[] countingSort(int[] arr) {
        int maxValue = getMaxValue(arr);
        int minValue = getMinValue(arr);
        int bucketLen = maxValue - minValue + 1;
        int[] bucket = new int[bucketLen];
        //记录数据出现的次数
        for (int v : arr) {
            bucket[v - minValue]++;
        }
        int sortedIndex = 0;
        for (int i = 0; i < bucketLen; i++) {
            while (bucket[i]-- > 0) {
                arr[sortedIndex++] = i + minValue;
            }
        }
        return arr;
    }

    private static int getMaxValue(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    private static int getMinValue(int[] arr) {
        int min = arr[0];
        for (int i : arr) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 3, 2, 5, 6, 4, 5, 6, 9};
        System.out.println(Arrays.toString(sort(arr)));
    }
}
