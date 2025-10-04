public class ContainerWithMostWater {
  public static int maxArea(int[] height) {
    int i = 0;
    int j = height.length - 1;
    int maxwater = 0;
    while (i < j) {
      int currht = Math.min(height[i], height[j]);
      int width = j - i;
      int area = currht * width;
      maxwater = Math.max(maxwater, area);
      if (height[i] < height[j]) {
        i++;
      } else {
        j--;
      }
    }
    return maxwater;
  }

  public static void main(String[] args) {
    int[] height = {1,8,6,2,5,4,8,3,7};
    System.out.println(maxArea(height));
  }
}
