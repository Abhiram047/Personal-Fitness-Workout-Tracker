import java.io.*;
import java.util.*;

// ---------- MODULE 4: class and object ----------
class Workout {
    String name;
    int minutes;

    Workout(String name, int minutes) {      // constructor
        this.name = name;
        this.minutes = minutes;
    }

    int calories() {                         // strength workout: 8 kcal per minute
        return minutes * 8;
    }
}

// ---------- MODULE 4: inheritance ----------
class Cardio extends Workout {
    Cardio(String name, int minutes) {
        super(name, minutes);
    }

    int calories() {                         // cardio burns more: 10 kcal per minute
        return minutes * 10;
    }
}

public class FitnessTracker {

    // ---------- MODULE 3: methods ----------
    static double calcBMI(double weight, double height) {
        return weight / (height * height);
    }

    // ---------- MODULE 3: recursion ----------
    static int total(int[] a, int i) {
        if (i == a.length) {
            return 0;                        // base case
        }
        return a[i] + total(a, i + 1);       // calls itself
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---------- MODULE 1: variables, input and output ----------
        System.out.print("Enter your name: ");
        String user = sc.nextLine();
        System.out.print("Enter your weight (kg): ");
        double weight = sc.nextDouble();
        System.out.print("Enter your height (m): ");
        double height = sc.nextDouble();

        double bmi = calcBMI(weight, height);
        System.out.println("\nHello " + user + "!");
        System.out.println("Your BMI: " + Math.round(bmi * 10) / 10.0);

        // ---------- MODULE 2: if-else ----------
        if (bmi < 18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi < 25) {
            System.out.println("Category: Normal");
        } else {
            System.out.println("Category: Overweight");
        }

        // ---------- MODULE 3: array + MODULE 2: for loop ----------
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int[] week = new int[7];
        System.out.println("\nEnter workout minutes for each day:");
        for (int i = 0; i < 7; i++) {
            System.out.print(days[i] + ": ");
            week[i] = sc.nextInt();
        }

        System.out.print("Enter your weekly goal (minutes): ");
        int goal = sc.nextInt();

        int activeDays = 0;
        for (int i = 0; i < week.length; i++) {
            if (week[i] > 0) {
                activeDays++;
            }
        }
        int totalMinutes = total(week, 0);             // recursion
        System.out.println("\nActive days: " + activeDays);
        System.out.println("Total minutes: " + totalMinutes);
        if (totalMinutes >= goal) {
            System.out.println("Weekly goal achieved!");
        } else {
            System.out.println("Goal not achieved, you need " + (goal - totalMinutes) + " more minutes");
        }

        // ---------- MODULE 4: objects in an ArrayList ----------
        ArrayList<Workout> list = new ArrayList<>();
        System.out.print("\nHow many workouts did you do today? ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("Workout " + i + ":");
            System.out.print("  Name (one word): ");
            String wname = sc.next();
            System.out.print("  Type (1 = Cardio, 2 = Strength): ");
            int type = sc.nextInt();
            System.out.print("  Minutes: ");
            String input = sc.next();

            // ---------- MODULE 4: exception handling ----------
            try {
                int mins = Integer.parseInt(input);
                if (type == 1) {
                    list.add(new Cardio(wname, mins));
                } else {
                    list.add(new Workout(wname, mins));
                }
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input! Minutes must be a number. Workout skipped.");
            }
        }

        System.out.println("\nToday's workouts:");
        for (Workout w : list) {
            System.out.println(w.name + " - " + w.minutes + " min - " + w.calories() + " kcal");
        }

        // ---------- MODULE 5: strings ----------
        System.out.print("\nSearch a workout by name: ");
        String search = sc.next();
        boolean found = false;
        for (Workout w : list) {
            if (w.name.equalsIgnoreCase(search)) {
                System.out.println("Found workout: " + w.name.toUpperCase());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Workout not found");
        }

        // ---------- MODULE 5: library algorithm (Arrays.sort) ----------
        int[] sorted = week.clone();
        Arrays.sort(sorted);
        System.out.println("Longest workout day: " + sorted[sorted.length - 1] + " min");

        // ---------- MODULE 5: file I/O ----------
        try {
            FileWriter fw = new FileWriter("workout_log.txt");
            for (Workout w : list) {
                fw.write(w.name + " " + w.minutes + " min\n");
            }
            fw.close();

            System.out.println("\nReading from file:");
            Scanner fileScanner = new Scanner(new File("workout_log.txt"));
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("File error");
        }

        // ---------- MODULE 6: streams ----------
        int totalCalories = list.stream().mapToInt(w -> w.calories()).sum();
        System.out.println("\nTotal calories burned: " + totalCalories);
    }
}
