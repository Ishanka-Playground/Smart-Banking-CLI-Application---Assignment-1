import java.util.Arrays;
import java.util.Scanner;

public class Assignment1 {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String ADD_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String CHECK_AC_BALANCE = "Check Account Balance";
        final String DROP_EXISTING_AC = "Drop Existing Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        String[] idArray = new String[0];
        double[] balanceArray = new double[0];
        String[] nameArray = new String[0];

        do {
    
            final String APP_TITLE = String.format("%s%s%s",
            COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch (screen){

                case DASHBOARD:

                    System.out.println(Arrays.toString(nameArray));
                    System.out.println(Arrays.toString(idArray));
                    System.out.println(Arrays.toString(balanceArray));


                System.out.println("\t[1]. Open New Account");
                System.out.println("\t[2]. Deposit Money");
                System.out.println("\t[3]. Withdraw Money");
                System.out.println("\t[4]. Transfer Money");
                System.out.println("\t[5]. Check Account Balance");
                System.out.println("\t[6]. Drop Existing Account");
                System.out.println("\t[7]. Exit\n");
                System.out.print("\tEnter an option to continue: ");
                int option = SCANNER.nextInt();
                SCANNER.nextLine();

                    switch (option){
                        case 1: screen = ADD_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = CHECK_AC_BALANCE; break;
                        case 6: screen = DROP_EXISTING_AC; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;


                case ADD_ACCOUNT:
                
                    boolean valid;
                    String name;
                    String id = "";

                    do {

                        // ID
                        valid = true;
                        
                        if (idArray.length !=0) {
                            System.out.printf("\tID: SDB-%05d ",idArray[idArray.length-1]+1); // automatic
                            id = String.format("SDB-%05d", idArray[idArray.length-1]+1);
                        }  
                        else {
                            System.out.printf("\tID: SDB-%05d ",1);
                            id = String.format("SDB-%05d", 1);

                        }

                        System.out.println();

                        // Name
                        System.out.print("\tName :");
                        name = SCANNER.nextLine().strip();

                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG, "Name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || 
                                Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf(ERROR_MSG, "Invalid name");
                                valid = false;
                                break;
                            }
                        }

                    } while (!valid);
    
                
                    //Initial Payment
                    double initialDeposit;
                    do {
                        valid = true;
                        System.out.print("\tInitial Deposit : Rs ");
                        initialDeposit = SCANNER.nextDouble();
                        SCANNER.nextLine();
                            if (initialDeposit < 5000){
                                System.out.printf(ERROR_MSG, "Insuficient fund");
                                valid = false;
                                continue;
                            }             
                    } while (!valid);

                    
                    String[] newIdArray = new String[idArray.length+1];
                    double[] newBalanceArray = new double[balanceArray.length + 1];
                    String[] newNameArray = new String[nameArray.length + 1];

                    for (int i = 0; i < nameArray.length; i++) {
                        newIdArray[i]=idArray[i];
                        newNameArray[i] = nameArray[i];
                        newBalanceArray[i] = balanceArray[i];
                    }

                    newIdArray[newIdArray.length-1]= id;
                    newNameArray[newNameArray.length-1] = name;
                    newBalanceArray[newBalanceArray.length-1] = initialDeposit;

                    idArray = newIdArray;
                    nameArray = newNameArray;
                    balanceArray = newBalanceArray;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, 
                        String.format("%s:%s has been saved successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;


            }
            
        } while (true);



        
    }
}