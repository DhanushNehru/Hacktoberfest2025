class duplicate {
    public List<Integer> findDuplicates(int[] nums) {
        int n=nums.length;
        int i=0;
        List<Integer> list=new ArrayList<Integer>();
       while(i<n)
       {
           if(nums[nums[i]-1]==nums[i])
           {
               i++;
           }
           else
           {
               int temp=nums[i];
               nums[i]=nums[nums[i]-1];
               nums[temp-1]=temp;
           }
       }
       for(i=0;i<n;i++)
       {
           if(nums[i]!=i+1)
           {
               list.add(nums[i]);
           }
       }
        return list;
    }
}
