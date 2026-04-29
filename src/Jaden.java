import java.util.Scanner;

public class Jaden {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the nba goat: ");
        String name = input.nextLine().toLowerCase();

        if(name.contains("curry") || name.contains("jordan") ){
            System.out.println("Correct!");
        }else if (name.contains("lebron"))
        {
            System.out.println("no");
        }else {
            System.out.println("Wrong!");
        }

        System.out.print("Enter the soccer goat : ");
        String name2 = input.nextLine().toLowerCase();


        if(name.contains("ronaldo") || name.contains("pele") ){
            System.out.println("Correct!");
        }else if (name.contains("messi"))
        {
            System.out.println("hell no");
        }else {
            System.out.println("Wrong!");
        }
    }
}