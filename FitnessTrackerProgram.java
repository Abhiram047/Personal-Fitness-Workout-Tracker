import java.util.Scanner;

public class FitnessTrackerProgram {

    public static void main(String[] args) {

        // Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Variables
        int totalCalories = 0;
        int workoutCount = 0;
        char choice;

        System.out.println("====================================");
        System.out.println(" PERSONAL FITNESS & WORKOUT TRACKER ");
        System.out.println("====================================");

        // User enters daily calorie goal
        System.out.print("Enter your daily calorie goal: ");
        int goal = sc.nextInt();

        // ---------------------------------------
        // WHILE LOOP
        // Allows user to enter multiple workouts
        // ---------------------------------------
        while (true) {

            System.out.println("\nWorkout " + (workoutCount + 1));

            System.out.print("Enter Workout Name: ");
            sc.nextLine(); // clear buffer
            String workout = sc.nextLine();

            System.out.print("Enter Calories Burned: ");
            int calories = sc.nextInt();

            totalCalories += calories;
            workoutCount++;

            System.out.print("Do you want to add another workout? (Y/N): ");
            choice = sc.next().charAt(0);

            if (choice == 'N' || choice == 'n') {
                break;
            }
        }

        // Display Workout Summary
        System.out.println("\n========== DAILY REPORT ==========");
        System.out.println("Total Workouts : " + workoutCount);
        System.out.println("Total Calories Burned : " + totalCalories);

        // ---------------------------------------
        // IF-ELSE
        // Check Goal Achievement
        // ---------------------------------------
        if (totalCalories >= goal) {
            System.out.println("Congratulations! Daily Goal Achieved.");
        } else {
            System.out.println("Goal Not Achieved. Keep Going!");
        }

        // ---------------------------------------
        // BMI CALCULATION
        // ---------------------------------------
        System.out.println("\n========== BMI CALCULATOR ==========");

        System.out.print("Enter Weight (kg): ");
        double weight = sc.nextDouble();

        System.out.print("Enter Height (meters): ");
        double height = sc.nextDouble();

        double bmi = weight / (height * height);

        System.out.printf("Your BMI = %.2f\n", bmi);

        // ---------------------------------------
        // ELSE-IF
        // BMI Category
        // ---------------------------------------
        if (bmi < 18.5) {
            System.out.println("Category : Underweight");
        }
        else if (bmi < 25) {
            System.out.println("Category : Normal Weight");
        }
        else if (bmi < 30) {
            System.out.println("Category : Overweight");
        }
        else {
            System.out.println("Category : Obese");
        }

        // ---------------------------------------
        // FOR LOOP
        // Weekly Activity Report
        // ---------------------------------------
        System.out.println("\n========== WEEKLY REPORT ==========");

        for (int day = 1; day <= 7; day++) {
            System.out.println("Day " + day + " : Workout Completed");
        }

        System.out.println("\n====================================");
        System.out.println(" Thank You for Using Fitness Tracker ");
        System.out.println("====================================");

        sc.close();
    }
}