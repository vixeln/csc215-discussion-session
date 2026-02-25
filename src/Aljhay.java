import java.util.Scanner;

public class Aljhay {

//        Code Here!

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        welcomeDisplay();

        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Please enter your height in feet and inches (both integers): ");
        int feet = scanner.nextInt();
        int inches = scanner.nextInt();

        int height = (feet * 12) + inches;

        System.out.println("Please enter your weight in lb: ");
        float weight = scanner.nextInt();

        float BMI = BMICalculation(weight, inches);

        displayResults(name, height, weight, BMI);

        displayEndProgram();

    }

    public static void welcomeDisplay() {
        System.out.print("Welcome message");
    }

    public static float BMICalculation(float weight, int height) {
        float BMI = weight/(height * height * 703);

        return BMI;
    }

    public static void displayResults(String name, int height, float weight, float BMI) {
        System.out.println("Summary Report: ");
        System.out.println("Height: " + height + "inches");
        System.out.println("Weight: " + weight + "lb");
        System.out.println("BMI: " + BMI);

    }

    public static void displayEndProgram() {
        System.out.println("The program has ended.");
    }



}

