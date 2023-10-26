import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Reservation {
    private int pnr;
    private int trainNumber;
    private String dateOfJourney;
    private String origin;
    private String destination;

    public Reservation(int pnr, int trainNumber, String dateOfJourney, String origin, String destination) {
        this.pnr = pnr;
        this.trainNumber = trainNumber;
        this.dateOfJourney = dateOfJourney;
        this.origin = origin;
        this.destination = destination;
    }

    public int getPNR() {
        return pnr;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }


    public String toString() {
        return "PNR: " + pnr + ", Train Number: " + trainNumber + ", Date: " + dateOfJourney +
                ", From: " + origin + ", To: " + destination;
    }
}

public class Main {
    private List<Reservation> reservations = new ArrayList<>();
    private int nextPNR = 1;

    public static void main(String[] args) throws ParseException, IOException {
        Main reservationSystem = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            try {
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.makeReservation(scanner);
                    break;
                case 2:
                    reservationSystem.cancelReservation(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system");
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("\n Invalid Choice.");
            scanner.next();
        }
    }
            scanner.close();
            System.exit(0);
    }

    private void makeReservation(Scanner scanner) throws ParseException  {
        System.out.print("Enter Train Number: ");
        int trainNumber = scanner.nextInt();
        System.out.print("Enter a date (dd/mm/yyyy):");
        String dateOfJourney = scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(dateOfJourney);
        System.out.print("Enter Origin: ");
        String origin = scanner.next();
        System.out.print("Enter Destination: ");
        String destination = scanner.next();

        int pnr = nextPNR++;
        Reservation reservation = new Reservation(pnr, trainNumber, dateOfJourney, origin, destination);
        reservations.add(reservation);

        System.out.println("Reservation Successful. Your PNR is: " + pnr);
    }

    private void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR to cancel: ");
        int pnr = scanner.nextInt();

        Reservation reservationCancel = null;
        for (Reservation reservation : reservations) {
            if (reservation.getPNR() == pnr) {
                reservationCancel = reservation;
                break;
            }
        }

        if (reservationCancel != null) {
            reservations.remove(reservationCancel);
            System.out.println("Reservation with PNR " + pnr + " has been canceled.");
        } else {
            System.out.println("Reservation with PNR " + pnr + " not found.");
        }
    }
}
