package com.today.roc.go.understand.算法.快速排序;

import lombok.extern.slf4j.Slf4j;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年04月21日 11:28*
 * log.info()
 */
@Slf4j
public class QuickSort {

    /**
     * 核心原理：找到基准数(初始为数组第一个，第一次排序后便确定位置了)
     * 循环遍历右边小于基准的往左挪
     * 循环遍历左边大于基准的往右挪
     * 挪就是左右两边交换
     * 基准数处于某个位置
     * 边界问题：在最左/右边
     * 继续进行问题：左边一位或右边一位
     * <p>
     * 6,3,2,7,5,9,6
     * 5,3,2,6,7,9,6
     * 5,3,2,6
     * 7,9,6
     */

    public void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int newLow = low;
            int newHigh = high;
            //基准值
            int pivot = arr[low];
            //当low = high时 就退出，表示已经处理完
            while (low < high) {
                //当高位比基准小时，需要放左边
                while (arr[high] >= pivot && low < high) {
                    high--;
                }
                //选中需要放在左边的值
                int left = arr[high];
                arr[low] = arr[high];
                //不使用swap减少了数据放置的消耗
                //swap(arr, low, high);
                log.info("选中放在左边的值：{}", left);

                //当低位比基准大时，需要放右边
                while (arr[low] <= pivot && low < high) {
                    low++;
                }
                //选中放在右边的值
                int right = arr[low];
                log.info("选中放在右边的值：{}", right);
                arr[high] = arr[low];
                //swap(arr, low, high);
            }
            //排序完确定基准的位置 ，以基准作为分割，左边比基准小，右边比基准大
            arr[low] = pivot;
            log.info("low,{},high,{}", low, high);
            //对分割之后的子数组进行排序
            quickSort(arr, newLow, low - 1);
            quickSort(arr, low + 1, newHigh);
        }
    }

    public int getIndex(int arr[], int low, int high) {
        int pivot = arr[low];
        //当low = high时 就退出，表示已经处理完
        while (low < high) {
            //当高位比基准小时，需要放左边
            while (arr[high] >= pivot && low < high) {
                high--;
            }
            //选中需要放在左边的值
            int left = arr[high];
            arr[low] = arr[high];
            //不使用swap减少了数据放置的消耗
            //swap(arr, low, high);
            log.info("选中放在左边的值：{}", left);

            //当低位比基准大时，需要放右边
            while (arr[low] <= pivot && low < high) {
                low++;
            }
            //选中放在右边的值
            int right = arr[low];
            log.info("选中放在右边的值：{}", right);
            arr[high] = arr[low];
            //swap(arr, low, high);
        }
        arr[low] = pivot;
        return low;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
//        new QuickSort().quickSort(arr, 0, arr.length - 1);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
        int[] arr1 = new int[]{4, 3, 31, 2, -1, 6, 12, 1, 2, 3};
        new QuickSort().findIndexMaxFromK(arr1, 5 - 1, 0, arr1.length - 1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
    }

    public void swap(int arr[], int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    public void findIndexMaxFromK(int[] arr, int k, int low, int high) {
        if (k >= arr.length) {
            throw new RuntimeException("选择位置不在数据范围内");
        }
        int newLow = low;
        int newHigh = high;
        //乱序数组，找出第k+1大的数
        int pivot = arr[low];
        while (low < high) {
            while (arr[high] >= pivot && low < high) {
                high--;
            }
            //交换数据
            arr[low] = arr[high];
            while (arr[low] <= pivot && low < high) {
                low++;
            }
            arr[high] = arr[low];
        }
        System.out.println("基准值的索引位置"+low);
        int index = low;
        arr[index] = pivot;

        if (low == high) {
            if (index == k) {
                System.out.println("找到第" + k + "大的值" + pivot);
                return;
            } else if (index < k) {
                //对右边排序
                findIndexMaxFromK(arr, k, index + 1, newHigh);
            } else {
                //对左边排序
                findIndexMaxFromK(arr, k, newLow, index - 1);
            }
        }
    }

}
