double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
    double s;
    int b;
    int t[nums1Size+nums2Size];
int i=0; int j=0; int k=0;
// Merging two sorted arrays
    while((i<nums1Size)&&(j<nums2Size))
    {   if(nums1[i]<nums2[j])
     {  t[k]=nums1[i];
        i++;
        k++;
     }
      else
     {  t[k]=nums2[j];
        j++;
        k++;
    }
    }
// If there are remaining elements in nums1
    if(i== nums1Size)
    {
        while(j<nums2Size)
        {   t[k]=nums2[j];
            j++;
            k++;
        }
    }
// If there are remaining elements in nums2
    if(j== nums2Size)
    {
        while(i<nums1Size)
        {   t[k]=nums1[i];
            i++;
            k++;
        }
    }
// Calculate the median
    if((nums1Size+nums2Size)%2!=0)
    {   // Odd length, return the middle element
        b=t[(nums1Size+nums2Size)/2];
        s=(double)b;
        return(s);
    }
    else 
    {   // Even length, return the average of the two middle elements
        s=((double)((t[(nums1Size+nums2Size)/2])+(t[((nums1Size+nums2Size)/2)-1])))/2;
        return(s);
    }
}