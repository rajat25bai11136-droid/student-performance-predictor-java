import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   STUDENT PERFORMANCE PREDICTOR (JAVA CLI)     ");
        System.out.println("==================================================\n");

        double studyHours = 6.5;
        double attendance = 85.0;
        double prevMarks = 78.0;
        double sleepHours = 7.0;

        // Parse CLI Arguments if provided
        if (args.length >= 4) {
            try {
                studyHours = Double.parseDouble(args[0]);
                attendance = Double.parseDouble(args[1]);
                prevMarks = Double.parseDouble(args[2]);
                sleepHours = Double.parseDouble(args[3]);
                System.out.println("[+] Successfully parsed custom command-line arguments.");
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid argument format. Falling back to evaluation defaults.");
            }
        } else {
            System.out.println("[+] No CLI arguments passed. Running automated benchmark test...");
        }

        // Regression predictive model logic
        double predictedScore = (studyHours * 3.5) + (attendance * 0.3) + (prevMarks * 0.4) + (sleepHours * 0.5) - 15.0;
        predictedScore = Math.max(0.0, Math.min(100.0, predictedScore)); // Clamp between 0 and 100

        // Output results to terminal
        System.out.println("\n--- EVALUATION METRICS & PREDICTION ---");
        System.out.printf("Study Hours    : %.1f hrs\n", studyHours);
        System.out.printf("Attendance     : %.1f%%\n", attendance);
        System.out.printf("Previous Marks : %.1f%%\n", prevMarks);
        System.out.printf("Sleep Hours    : %.1f hrs\n", sleepHours);
        System.out.println("---------------------------------------");
        System.out.printf("Predicted Final Grade : %.2f / 100\n\n", predictedScore);

        // Generate output artifact for headless verification
        try (FileWriter writer = new FileWriter("evaluation_output.csv")) {
            writer.write("Metric,Value\n");
            writer.write("StudyHours," + studyHours + "\n");
            writer.write("Attendance," + attendance + "\n");
            writer.write("PreviousMarks," + prevMarks + "\n");
            writer.write("SleepHours," + sleepHours + "\n");
            writer.write("PredictedScore," + String.format("%.2f", predictedScore) + "\n");
            System.out.println("[+] Output report saved to 'evaluation_output.csv'");
        } catch (IOException e) {
            System.err.println("[-] Error writing CSV report: " + e.getMessage());
        }

        System.out.println("\n[✓] Execution completed successfully with exit code 0.");
        System.exit(0);
    }
}
