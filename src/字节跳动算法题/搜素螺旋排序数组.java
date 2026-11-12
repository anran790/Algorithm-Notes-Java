package 字节跳动算法题;

/**
 * 5.搜索旋转排序数组
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出target 的数组下标: 4
 */
public class 搜素螺旋排序数组 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int n = 0;
        System.out.println(searchArr(nums, n));
    }

    private static int searchArr(int[] nums, int n) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == n) {
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                if (nums[mid] > n && n >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] > n && n <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
