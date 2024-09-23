public class RepeatingNumbers{
  
  public static void repeatingNumbers(int[] A, int n){
    for(int i = 1; i < n; i++){
      if((A[i])!=i){
        int temp = A[i];
        A[i] = A[temp];
        A[temp] = temp;
      }
    }
    for(int i = 0; i < n; i++){
      if((A[i])!=i) System.out.print(A[i]+" ");
    }
    System.out.println();
  }

  public static void main(String[] args){
    int[] nums = new int[args.length]; 
    if(nums.length > 0){
      for (int i = 0; i < args.length; i++){
        nums[i] = Integer.parseInt(args[i]);
      }
      repeatingNumbers(nums, nums.length);
    }
    else{
      System.out.println("Please enter your sequence of numbers!");
      return;
    }
  }
}