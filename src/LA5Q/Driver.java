package LA5Q;
//This program is used to create a boarding and disembarking list assuming the passengers have
//already purchased their tickets with randomly assigned seats. In the real world name, seat number,
//and disability values would be taken from scanning the passengers ticket at the check in.
//I used a stack structure so the first passenger in is the last passenger out. This way there
//will be fewer people in your way when entering and leaving the plane. I also made it so that
//people with a disability are the first to enter and last to leave as they will need the most
//space to get in and out.The final order is broken into groups of 10
//so that not too many people are entering the plane at once.

import java.util.*;

public class Driver {
    public static void main(String[] args) {
        myHeader(1);
        Scanner scanner = new Scanner(System.in);
        //new stack for passenger and disabled passengers so eac group can be sorted separately before combining
        //could have also been an arraylist
        Stack passengers = new Stack();
        Stack disabledPassengers = new Stack();
        //will hold the final order of all passengers
        Stack finalOrder = new Stack();


        int planeSize;
        //setting max plane size so seats that dont exist cant be assigned to passengers
        while (true) {
            try {
                System.out.print("\nWelcome to Aeroplane Check-In\n"
                        + "How many Seats on plane: ");
                //getting entire line so no buffer issues
                planeSize = Integer.parseInt(scanner.nextLine());
                if (planeSize > 0) {
                    break;
                } else {
                    throw new RuntimeException("Must be above 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        int loop = 0;
        //first loop gives user options to start adding passenger or to exit the application
        while (loop == 0) {
            System.out.printf("\nPassenger Check-In Program\n" +
                    "\t1. Passenger Check-In (Add Passengers)\n" +
                    "\t2. Exit\n" +
                    "Please choose an option: ");
            String firstChoice = scanner.nextLine();
            switch (firstChoice) {
                case "1"://users can add passengers to plane or view order once done adding all passengers
                    //user cannot add more passengers once option 2 is selected as elements will be popped from the array
                    //when making disembarking list
                    //all input checks could be mitigated in real world as a machine would scan input directly from ticket
                    int loop1 = 0;
                    while (loop1 == 0) {
                        System.out.print("\nPassenger Check-In\n" +
                                "\t1. Add Passenger\n" +
                                "\t2. Done adding all passengers\n" +
                                "Please choose an option: ");
                        String secondChoice = scanner.nextLine();
                        switch (secondChoice) {
                            case "1"://get user info and create Passengerclass and add to appropriate stack
                                String name;
                                int seatNum;
                                System.out.print("\nEnter Passenger Name: ");
                                name = scanner.nextLine();
                                while (true) {
                                    try {//check to make sure seat is open and on plane
                                        System.out.print("Enter Passenger Seat: ");
                                        seatNum = Integer.parseInt(scanner.nextLine());
                                        if (seatNum < 1 || seatNum > planeSize) {
                                            throw new RuntimeException("Invalid Seat Number (Must be between 1 and " + planeSize + ")\n");
                                        }
                                        for(int i = 0; i < passengers.getSize(); i++){
                                            if(passengers.getTestArray()[i].getSeatNum() == seatNum){
                                                throw new RuntimeException("Seat Already Taken\n");
                                            }
                                        }
                                        for(int i = 0; i < disabledPassengers.getSize(); i++){
                                            if(disabledPassengers.getTestArray()[i].getSeatNum() == seatNum){
                                                throw new RuntimeException("Seat Already Taken\n");
                                            }
                                        }
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.print("Invalid Seat Number (Must be integer)\n");
                                    } catch (RuntimeException e) {
                                        System.out.print(e.getMessage());
                                    }
                                }
                                while (true) {
                                    System.out.print("Does the passenger have a disability (Yes or No): ");
                                    String disabilityString = scanner.nextLine();
                                    //check to make sure valid input
                                    if (disabilityString.equalsIgnoreCase("yes") || disabilityString.equalsIgnoreCase("no")) {
                                        if (disabilityString.equalsIgnoreCase("yes")) {
                                            disabledPassengers.push(new PassengerClass(seatNum, name, true));
                                        } else {
                                            passengers.push(new PassengerClass(seatNum, name, false));
                                        }
                                        break;
                                    } else {
                                        System.out.print("Invalid Input\n");
                                    }
                                }
                                break;
                            case "2":
                                //sorting individual stacks based on ascending seats using custom comparable method
                                Arrays.sort(passengers.getTestArray());
                                Arrays.sort(disabledPassengers.getTestArray());

                                //pushing the disabled passengers to the final array first, so they will be the first to board plane
                                //since popping from array, the highest seats will be the first ones on the finalOrderlist
                                int x = disabledPassengers.getSize();
                                for (int i = 0; i < x; i++) {
                                    finalOrder.push(disabledPassengers.pop());
                                }//pushing the rest of the passengers to same list
                                int y = passengers.getSize();
                                for (int i = 0; i < y; i++) {
                                    finalOrder.push(passengers.pop());
                                }

                                //printing out from current stack to fill plane from the back with
                                //disabled people getting on first
                                //also formatting into smaller groups to avoid crowding
                                System.out.print("\nBoarding Order\n\nGroup 1\n");
                                int counter = 1;
                                for (int i = 0; i < finalOrder.getSize(); i++) {
                                    System.out.print(finalOrder.getTestArray()[i].getName() + "\n");
                                    if((i+1) % 10 == 0){// breaking array into groups of 10 so only small groups enter plane at one time
                                        counter = counter + 1;
                                        System.out.print("\nGroup" + counter +"\n");
                                    }
                                }

                                //popping from stack so people get out in the reverse order that they came in
                                System.out.print("\nDisembarking Order\n\nGroup 1\n");
                                int counter1 = 1;
                                int size = finalOrder.getSize();
                                for (int i = 0; i < size; i++) {
                                    System.out.print(finalOrder.pop().getName() + "\n");
                                    if((i+1) % 10 == 0){// breaking array into groups of 10 so only small groups enter plane at one time
                                        counter1 = counter1 + 1;
                                        System.out.print("\nGroup" + counter1 +"\n");
                                    }
                                }

                                loop1 = 1;
                                loop = 1;
                                break;
                            default:
                                System.out.print("Invalid Input\n");
                                break;
                        }
                    }

                    break;
                case "2":
                    loop = 1;
                    break;
                default://check to make sure valid option os selected
                    System.out.println("Invalid Option");
                    break;
            }
        }
        System.out.println("\nThanks for using Passenger Check-In");
        myFooter(1);
    }

    public static void myHeader(int q) {//method printing out header
        System.out.printf("=========================================================== \n" +
                "Lab Exercise 5-Q%d \n" +
                "Prepared By: Yakesh Umachandran\n" +
                "Student Number: 251303571\n" +
                "Goal of this Exercise: Create a passenger boarding and disembarking program\n" +
                "===========================================================\n", q);
    }
    public static void myFooter(int q) {//method printing out footer
        System.out.printf("=========================================================== \n" +
                "Completion of Lab Exercise 5-Q%d is successful!\n" +
                "Signing off - Yakesh\n" +
                "===========================================================\n", q);
    }

}
