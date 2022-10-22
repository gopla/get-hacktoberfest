import java.util.Scanner;
public class SandClock
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number of rows : ");
 
        int baris = sc.nextInt();            
        for (int i= 0; i<= baris-1 ; i++)
        {
            for (int j=0; j <i; j++)
            {
                System.out.print(" ");
            }
            for (int k=i; k<=baris-1; k++) { System.out.print("*" + " "); } System.out.println(""); } 
            for (int i= baris-1; i>= 0; i--)
        {
            for (int j=0; j< i ;j++)
            {
                System.out.print(" ");
            }
            for (int k=i; k<=baris-1; k++)
            {
                System.out.print("*" + " ");
            }
            System.out.println("");
        }
        sc.close();
    }
}
