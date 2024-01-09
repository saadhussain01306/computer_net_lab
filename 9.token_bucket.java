/*Token Bucket for congestion control*/

/* In this the bucket is filled with constant rate called token_generation_rate untill the bucket is full */

import java.util.Scanner;
import java.util.*;

public class Token_bucket{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int token_remaining=0;//total tokens in the bucket
        int token_requested,token_sent;
        System.out.println("Enter the bucket capacity");
        int bucket_capacity=in.nextInt();
        System.out.println("Enter the Token generation rate (Rate at which tokens are sent to the bucket)");
        int token_gen_rate=in.nextInt();
        System.out.println("Enter the number of Cycles the host computer sends the Tokens to the bucket(at constant rate)");
        int n=in.nextInt();
        System.out.println(String.format("%s\t%s\t%s\t%s", "Time_t", "Tokens Requested", "Tokens Sent", "Tokens Remaining in bucket"));
        for(int i=0;i<n;i++){
            token_requested=token_gen_rate;
            if(token_requested+token_remaining>bucket_capacity){
                token_sent=bucket_capacity-token_remaining;
                token_remaining=bucket_capacity;
            }
            else{
                token_sent=token_requested;
                token_remaining+=token_requested;
            }
            System.out.println(String.format("%d\t\t%d\t\t%d\t\t%d", i + 1, token_requested, token_sent, token_remaining));
        }
    }
}

/*OUTPUT:-
Enter the bucket capacity
5
Enter the Token generation rate (Rate at which tokens are sent to the bucket)
2
Enter the number of Cycles the host computer sends the Tokens to the bucket(at constant rate)
6
Time_t  Tokens Requested    Tokens Sent   Tokens Remaining in bucket
1             2                    2       2
2             2                    2       4
3             2                    1       5
4             2                    0       5
5             2                    0       5
6             2                    0       5
*/    

/*OUTPUT:-
Enter the bucket capacity
5
Enter the Token generation rate (Rate at which tokens are sent to the bucket)
2
Enter the number of Cycles the host computer sends the Tokens to the bucket(at constant rate)
6
Time_t  Tokens Requested    Tokens Sent   Tokens Remaining in bucket
1             2                    2       2
2             2                    2       4
3             2                    1       5
4             2                    0       5
5             2                    0       5
6             2                    0       5
*/
