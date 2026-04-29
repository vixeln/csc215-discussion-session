import java.util.Scanner;

public class Jaden {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the goat of the nba: ");
        String name = input.nextLine().toLowerCase();

        if(name.contains("curry") || name.contains("jordan") ){
            System.out.println("Correct!");
        }else {
            System.out.println("Wrong!");
        }

    }
}