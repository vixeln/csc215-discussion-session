import java.util.Scanner;
public class Attendance {
    static String[] students = {
    };
    public static void main(String[] args) {
        String student = pickRandomStudent();
//        Don't write here
        System.out.println(student);
    }


    public static String pickRandomStudent() {
        if (students.length != 0) {
            int randomNum = (int) Math.round(Math.random() * (students.length - 1));

            return students[randomNum];
        } else return null;

    }
}
