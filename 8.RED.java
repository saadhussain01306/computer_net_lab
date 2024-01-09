/*Write a program to implement random early detection (RED) 
congestion control algorithm*/

import java.util.Random;
import java.util.Scanner;

public class RedCongestionControl {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the maximum number of packets:");//number of packets to be sent
        int maxPackets = scanner.nextInt();

        System.out.println("Enter the queue size:");//size of the queue the packets can be stored
        int queueSize = scanner.nextInt();

        System.out.println("Enter the maximum probability:");
        double maxProbability = scanner.nextDouble();

        System.out.println("Enter the minimum probability:");//used to calculated the drop probabilty (max-min)
        double minProbability = scanner.nextDouble();

        System.out.println("Enter the threshold value:");//the value after which the congestion control comes to action 
        int threshold = scanner.nextInt();

        simulateCongestion(maxPackets, queueSize, maxProbability, minProbability, threshold);
    }

    private static void simulateCongestion(int maxPackets, int queueSize, double maxProbability, double minProbability, int threshold) {
        Random rand = new Random(System.currentTimeMillis());
        int queueLength = 0;

        for (int i = 0; i < maxPackets; i++) {
            double dropProbability = calculateDropProbability(queueLength, queueSize, maxProbability, minProbability, threshold);

            if (queueLength >= threshold && rand.nextDouble() < dropProbability) {
                System.out.println("Packet dropped (CONGESTION AVOIDANCE)");
                //checking the threshold value and the probabilty to check whether to accept or reject the packet
            } else {
                System.out.println("Packet accepted " + (i + 1));
                queueLength++;
            }
        }
    }

    private static double calculateDropProbability(int currentQueueLength, int queueSize, double maxProbability, double minProbability, int threshold) {
        double slope = (maxProbability - minProbability) / (queueSize - threshold);
        return minProbability + slope * (currentQueueLength - threshold);
    }
}


/*
output:-
Enter the maximum number of packets:
100
Enter the queue size:
20
Enter the maximum probability:
0.8
Enter the minimum probability:
0.2
Enter the threshold value:
10
Packet accepted 1
Packet accepted 2
Packet accepted 3
Packet accepted 4
Packet accepted 5
Packet accepted 6
Packet accepted 7
Packet accepted 8
Packet accepted 9
Packet accepted 10
Packet accepted 11
Packet accepted 12
Packet accepted 13
Packet accepted 14
Packet accepted 15
Packet accepted 16
Packet accepted 17
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 19
Packet accepted 20
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 23
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 33
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 40
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 47
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)


Output 2:-
Enter the maximum number of packets:
50 
Enter the queue size:
10
Enter the maximum probability:
0.6
Enter the minimum probability:
0.4
Enter the threshold value:
5
Packet accepted 1
Packet accepted 2
Packet accepted 3
Packet accepted 4
Packet accepted 5
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 9
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 13
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 17
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 19
Packet accepted 20
Packet accepted 21
Packet accepted 22
Packet accepted 23
Packet accepted 24
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 27
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 29
Packet dropped (CONGESTION AVOIDANCE)
Packet accepted 31
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)

*/
