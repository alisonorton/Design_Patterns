package Decorator;

import java.io.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name to read: ");
        String filename = scanner.nextLine();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             Writer writer = new OutputStreamWriter(System.out)) {
            
            Output output = new StreamOutput(writer);
            
            boolean addingDecorators = true;
            while (addingDecorators) {
                System.out.println("\nChoose a decorator:");
                System.out.println("1. BracketOutput");
                System.out.println("2. NumberedOutput");
                System.out.println("3. TeeOutput");
                System.out.println("4. FilterOutput");
                System.out.println("5. Done");
                System.out.print("Enter choice: ");

                int choice;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();  // Clear invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        output = new BracketOutput(output);
                        System.out.println("BracketOutput applied.");
                        break;
                    case 2:
                        output = new NumberedOutput(output);
                        System.out.println("NumberedOutput applied.");
                        break;
                    case 3:
                        System.out.print("Enter file name for TeeOutput: ");
                        String teeFilename = scanner.nextLine();
                        Output teeFileOutput = new StreamOutput(new FileWriter(teeFilename));
                        output = new TeeOutput(output, teeFileOutput);
                        System.out.println("TeeOutput applied.");
                        break;
                    case 4:
                        System.out.println("Choose a filter:");
                        System.out.println("1. ContainsDigit");
                        System.out.println("2. LongerThanTenChars");
                        System.out.print("Enter filter choice: ");

                        int filterChoice;
                        if (scanner.hasNextInt()) {
                            filterChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine();  // Clear invalid input
                            continue;
                        }

                        Predicate predicate = (filterChoice == 1) ? new ContainsDigit() : new LongerThanTenChars();
                        output = new FilterOutput(output, predicate);
                        System.out.println("FilterOutput applied.");
                        break;
                    case 5:
                        addingDecorators = false;
                        break;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            }

            System.out.println("\nProcessing file...\n");

            String line;
            while ((line = reader.readLine()) != null) {
                output.write(line);
            }

            // System.out.println("\nProcessing complete.");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
