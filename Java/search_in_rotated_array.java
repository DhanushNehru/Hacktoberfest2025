class SearchRotatedArray {
    public int search(int[] nums, int target) {
        int f=0;
        int l=nums.length-1;
        int n = nums.length - 1;
        if (target > nums[n]) {
            while (f <= l) {
                int mid = f + (l - f) / 2;
                if (nums[mid] < nums[n]) {
                    l = mid - 1;
                } else {
                    if (nums[mid] > target) {
                        l = mid - 1;
                    } else if (nums[mid] < target) {
                        f = mid + 1;
                    } else return mid;
                }
            }
        }
        else {
            while (f <= l) {
                int mid = f + (l - f) / 2;
                if (nums[mid] > nums[n]) {
                    f = mid + 1;
                } else {
                    if (nums[mid] > target) {
                        l = mid - 1;
                    } else if (nums[mid] < target) {
                        f = mid + 1;
                    } else return mid;
                }
            }
        }
        return -1;
    }
}
