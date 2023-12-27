/*Write a program to sort frames using appropriate sorting techniques*/
import java.util.*;

public class sort1 {
    public static void main(String[] args) {
        List<int[]> frame = new ArrayList<>();
        System.out.println("Enter no. of frames:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            Random random = new Random();
            int seqNum = random.nextInt(1000) + 1;
            System.out.printf("Enter data for %dth frame>>", i + 1);
            int data = sc.nextInt();
            frame.add(new int[]{seqNum, data});
        }

        System.out.println("\n\nBefore Sorting>>");
        for (int[] i : frame) {
            System.out.printf("seqNum->%d, Data->%d\n", i[0], i[1]);
        }

        frame = sortFrame(frame);

        System.out.println("\n\n After sorting>>");
        for (int[] i : frame) {
            System.out.printf("seqNum->%d, Data->%d\n", i[0], i[1]);
        }
    }

    public static List<int[]> sortFrame(List<int[]> frame) {
        Collections.sort(frame, (a, b) -> Integer.compare(a[0], b[0]));
        return frame;
    }
}

/*output
Enter no. of frames:
4
Enter data for 1th frame>>342
Enter data for 2th frame>>256
Enter data for 3th frame>>890
Enter data for 4th frame>>356


Before Sorting>>
seqNum->703, Data->342
seqNum->654, Data->256
seqNum->704, Data->890
seqNum->876, Data->356


 After sorting>>
seqNum->654, Data->256
seqNum->703, Data->342
seqNum->704, Data->890
seqNum->876, Data->356
*/
