import java.util.Scanner;

public class Main {
    public static final int CREATE_USER = 1;
    public static final int LOGIN = 2;
    public static final int OUT_FROM_PROGRAM= 3;

    public static final int PUBLISH_PROPERTY= 1;
    public static final int REMOVE_PROPERTY= 2;
    public static final int PRINT_ALL_PROPERTIES= 3;
    public static final int PRINT_USER_PROPERTIES= 4;
    public static final int SEARCH_BY_PARAMETERS= 5;
    public static final int BACK_TO_MAIN_MENU= 6;
    public static void main(String[] args) {

        int inputMainMenu;
        int inputSecondaryMenu = 0;
        boolean isSuccessfullyPosted = false;
        Scanner scanner = new Scanner(System.in);
        RealEstate realEstateUser = new RealEstate();
        User userLogin = null;
        System.out.println("Welcome to our real estate system");
        do {

            System.out.println("Please choose from the following 3 options");
            System.out.println("Press 1 for create user");
            System.out.println("Press 2 for login");
            System.out.println("Press 3 for end the program");
            inputMainMenu = scanner.nextInt();

            if (!checkInputMainMenu(inputMainMenu))
            {
                System.out.println("Please press number between 1 to 3 ");
                inputMainMenu = scanner.nextInt();
            }
            if (inputMainMenu == OUT_FROM_PROGRAM)
            {
                System.out.println("The Program is ended, goodbye!");
                break;
            }else if (inputMainMenu == CREATE_USER)
            {
                realEstateUser.createUser();

            } else if (inputMainMenu == LOGIN)
            {
                userLogin = realEstateUser.userLogin();
               if (userLogin== null)
               {
                   System.out.println("This user not EXIST");
                   break;
               }else {
                   do {
                       System.out.println("Please choose from the following 6 options");
                       System.out.println("Press 1 for publish new property");
                       System.out.println("Press 2 for remove publish property");
                       System.out.println("Press 3 for see all the properties in the system");
                       System.out.println("Press 4 for see  all your properties ");
                       System.out.println("Press 5 for search property by parameters");
                       System.out.println("Press 6 for back to MAIN MENU");
                       inputSecondaryMenu = scanner.nextInt();

                       if (inputSecondaryMenu == BACK_TO_MAIN_MENU) {
                           break;
                       }
                       if (!conditionSecondaryMenu(inputSecondaryMenu) )
                       {
                           System.out.println("Please press number between 1 to 6 ");
                           inputSecondaryMenu = scanner.nextInt();
                       }
                       if (inputSecondaryMenu == BACK_TO_MAIN_MENU)
                       {
                           break;
                       }else if (inputSecondaryMenu==PUBLISH_PROPERTY)
                       {
                           isSuccessfullyPosted = realEstateUser.postNewProperty(userLogin);
                           if (isSuccessfullyPosted)
                           {
                               System.out.println("The new property SUCCESSFULLY posted ");
                           }else {
                               System.out.println("You enter incorrect data ");
                           }
                       } else if (inputSecondaryMenu == REMOVE_PROPERTY) {
                           realEstateUser.removeProperty(userLogin);
                       } else if (inputSecondaryMenu==PRINT_ALL_PROPERTIES) {
                           realEstateUser.printAllProperties();
                       } else if (inputSecondaryMenu == PRINT_USER_PROPERTIES) {
                           realEstateUser.printProperties(userLogin);
                       } else if (inputSecondaryMenu == SEARCH_BY_PARAMETERS) {
                              realEstateUser.search();
                       }
                   }while (!checkInputSecondaryMenu(inputSecondaryMenu)||
                           conditionSecondaryMenu(inputSecondaryMenu) || !isSuccessfullyPosted);


               }
            }

        }while ( conditionMainMenu(inputMainMenu) || !checkInputMainMenu(inputMainMenu)||userLogin== null
                || inputSecondaryMenu == BACK_TO_MAIN_MENU);

    }


    //סיבוכיות של o(1)
    public static boolean conditionMainMenu(int inputMainMenu)
    {
       return ((inputMainMenu==CREATE_USER || inputMainMenu== LOGIN ));
    }

    //סיבוכיות של o(1)
    public static boolean checkInputMainMenu (int inputMainMenu)
    {
        return (inputMainMenu>=1 && inputMainMenu<=3);
    }

    //סיבוכיות של o(1)
    public static boolean conditionSecondaryMenu(int inputSecondaryMenu)
    {
        return (( inputSecondaryMenu==PUBLISH_PROPERTY ||inputSecondaryMenu==REMOVE_PROPERTY ||
                inputSecondaryMenu==PRINT_ALL_PROPERTIES || inputSecondaryMenu==PRINT_USER_PROPERTIES
                || inputSecondaryMenu==SEARCH_BY_PARAMETERS));

    }

    //סיבוכיות של o(1)
    public static boolean checkInputSecondaryMenu (int inputSecondaryMenu)
    {
        return (inputSecondaryMenu>=1 && inputSecondaryMenu<=6);
    }

}
