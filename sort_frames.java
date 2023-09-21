import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

public class sort1{
 public static void main(String[] args){
 List<int[]> frame=new ArrayList<>();
 System.out.println("Enter no. of frames:");
 Scanner sc=new Scanner(System.in);
 int n=sc.nextInt(); 
 for(int i=0;i<n;i++){
  Random random=new Random();
  int seqNum=random.nextInt(1000)+1;
  System.out.printf("Enter data for %dth frame>>",i+1);
  int data=sc.nextInt();
  frame.add(new int []{seqNum,data});
 }
 System.out.println("\n\nBefore Sorting>>");
 for(int[] i : frame){
  System.out.printf("seqNum->%d,Data->%d\n",i[0] , i[1]);
 }
 frame=sortFrame(frame);
 System.out.println("\n\n After sorting>>");
 for(int[] i : frame){
 System.out.printf("seqNum->%d,Data->%d\n",i[0] , i[1]);
 }
 }
 
 public static List<int []>sortFrame(List<int[] >frame){
  Collections.sort(frame,(a , b) -> Integer.compare(a[0] , b[0]));
  return frame;
  }
 }

/* output:-
  Enter no. of frames:
4
Enter data for 1th frame>>345
Enter data for 2th frame>>213
Enter data for 3th frame>>890
Enter data for 4th frame>>666


Before Sorting>>
seqNum->431,Data->345
seqNum->329,Data->213
seqNum->582,Data->890
seqNum->948,Data->666


 After sorting>>
seqNum->329,Data->213
seqNum->431,Data->345
seqNum->582,Data->890
seqNum->948,Data->666
*/
