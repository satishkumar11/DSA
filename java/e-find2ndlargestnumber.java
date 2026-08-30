class find2ndlargestnumber {
    public static void main(String[] args) {
        int[] nums = { 10, 5, 25, 8, 30, 15 };

        int first = nums[0];
        int second = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > first) {
                second = first;
                first = nums[i];
            }
            else if(nums[i] > second && nums[i] != first) {
                second = nums[i];
            }
        }
        System.out.println(second);
    }
}
