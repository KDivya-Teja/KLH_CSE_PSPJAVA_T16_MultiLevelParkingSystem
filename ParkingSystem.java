import java.util.*;
import java.io.*;
class Vehicle {
    String plate;
        Vehicle(String plate) {
        this.plate = plate.toUpperCase();
    }
}

public class ParkingSystem {
    static String[][] parking = new String[3][5];
    static ArrayList<String> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println("\n--- MULTI LEVEL PARKING SYSTEM ---");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. View Parking");
            System.out.println("4. Search Vehicle");
            System.out.println("5. View Parked Vehicles");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle number: ");
                    Vehicle v = new Vehicle(sc.nextLine());
                    parkVehicle(v);
                    break;

                case 2:
                    System.out.print("Enter vehicle number: ");
                    removeVehicle(sc.nextLine().toUpperCase());
                    break;

                case 3:
                    showParking();
                    break;

                case 4:
                    System.out.print("Enter vehicle number: ");
                    searchVehicle(sc.nextLine().toUpperCase());
                    break;

                case 5:
                    System.out.println("Parked Vehicles: " + vehicles);
                    break;

                case 6:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    static void parkVehicle(Vehicle v) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (parking[i][j] == null) {
                    parking[i][j] = v.plate;
                    vehicles.add(v.plate);

                    System.out.println("Vehicle parked at Floor "
                            + (i + 1) + ", Slot " + (j + 1));

                    saveRecord(v.plate, i + 1, j + 1);
                    return;
                }
            }
        }

        System.out.println("Parking is full.");
    }

    static void removeVehicle(String plate) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (plate.equals(parking[i][j])) {
                    parking[i][j] = null;
                    vehicles.remove(plate);
                    System.out.println("Vehicle removed.");
                    return;
                }
            }
        }

        System.out.println("Vehicle not found.");
    }

    static void searchVehicle(String plate) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (plate.equals(parking[i][j])) {
                    System.out.println("Vehicle found at Floor "
                            + (i + 1) + ", Slot " + (j + 1));
                    return;
                }
            }
        }

        System.out.println("Vehicle not found.");
    }

    static void showParking() {
        for (int i = 0; i < 3; i++) {
            System.out.println("\nFloor " + (i + 1));

            for (int j = 0; j < 5; j++) {
                if (parking[i][j] == null) {
                    System.out.println("Slot " + (j + 1) + ": Empty");
                } else {
                    System.out.println("Slot " + (j + 1) + ": " + parking[i][j]);
                }
            }
        }
    }

    static void saveRecord(String plate, int floor, int slot) {
        try {
            FileWriter file = new FileWriter("parking_records.txt", true);
            file.write(plate + " - Floor " + floor + " - Slot " + slot + "\n");
            file.close();
        } catch (IOException e) {
            System.out.println("Could not save record.");
        }
    }
}
