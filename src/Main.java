import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int[] nums = {3, 2, 4};
        int target = 6;
        int n = nums.length;
        int[] arr = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }
        System.out.println(arr);
    }

}

