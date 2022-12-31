import java.util.Scanner;

public class RealEstate {

    private User[] users;
    private Property[] properties;
    public static final City[] CITIES = {City.CITY_1,City.CITY_2,City.CITY_3,City.CITY_4,City.CITY_5,
            City.CITY_6,City.CITY_7,City.CITY_8,City.CITY_9,City.CITY_10};

    public RealEstate()
    {
       // צריך להשאיר ריק?
    }

    public void createUser()
    {
        Scanner scanner = new Scanner(System.in);
        String userName;
        String password;
        String cellPhone;
        int mediator;
        boolean mediatorToBoolean;
        System.out.print("Please enter name: ");
        userName = scanner.nextLine();
        System.out.print("Please enter 1 if you mediator OR 2 if you DONT mediator ");
        mediator = scanner.nextInt();
        while (isExistUserName(userName) ||(mediator !=1 && mediator!=2) )
        {
            //לפנות את 1 ו2 לפינל
            if (isExistUserName(userName))
            {
                System.out.print(" The name is already used.");
                userName = scanner.nextLine();
            }
            if (mediator !=1 && mediator!=2)
            {
                System.out.print("Please enter 1 OR 2, 1 if you mediator OR 2 if you DONT mediator");
                mediator = scanner.nextInt();
            }
        }

        if (mediator == 1)
        {
            mediatorToBoolean = true;
        }else {
            mediatorToBoolean = false;
        }
        User newUser = new User(userName, null, null,mediatorToBoolean);
        System.out.println("Please enter password: ");
        System.out.println("The password have to be at least 5 in length, ");
        System.out.println("at least contains one digit, ");
        System.out.println("at least contains one of the following char '$' '%' '_'");
        password = scanner.nextLine();
        scanner.nextInt();
        newUser.setPassword(password);
        while (newUser.getPassword() == null)
        {
            System.out.print("The password invalid.Please enter new password: ");
            System.out.println("The password have to be at least 5 in length, ");
            System.out.println("at least contains one digit, ");
            System.out.println("at least contains one of the following char '$' '%' '_'");
            password = scanner.nextLine();
            newUser.setPassword(password);
        }
        System.out.println("Please enter cellPhone: ");
        cellPhone = scanner.nextLine();
        scanner.nextLine();
        newUser.setCellPhone(cellPhone);
        while (newUser.getPassword()==null)
        {
            System.out.print("The cellPhone invalid.Please enter new cellPhone: ");
            cellPhone = scanner.nextLine();
            newUser.setCellPhone(cellPhone);
        }

       addUser(newUser);
        System.out.println("The user created successfully");
    }

    public User userLogin()
    {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String password = scanner.nextLine();
        User userLogin = checkUserExist(name,password);
        return userLogin;
    }

    public  boolean postNewProperty(User user)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isPostNewPropertySuccessfully = true;
        if (!isAuthorizedToPublish(user))
        {
            System.out.println("Sorry, you can't publish anymore.You took advantage of the quota");
            isPostNewPropertySuccessfully = false;
        } else {
            printCityName(CITIES);
            String cityName = scanner.nextLine();
            City userCityInput = getCity(cityName);
            if (userCityInput == null)
            {
                System.out.println("Sorry, the city not exist");
                isPostNewPropertySuccessfully = false;
            }else {
                System.out.println(userCityInput.printableStreetsList());
                String streetInCity = scanner.nextLine();
                if (!userCityInput.checkStreetInCity(streetInCity))
                {
                    System.out.println("Sorry, the street not exist in the city");
                    isPostNewPropertySuccessfully = false;
                }else {
                    System.out.println("Which type of property do you like to publish?");
                    System.out.println("Please press 1 for NORMAL APARTMENT");
                    System.out.println("Please press 2 for PENTHOUSE");
                    System.out.println("Please press 3 for PRIVATE HOUSE");
                    Integer tapeOfProperty = scanner.nextInt();
                    Property newProperty =new Property(userCityInput,streetInCity,null,0,
                            tapeOfProperty,false,null,0,user);
                   if (newProperty.getType() == null)
                   {
                       System.out.println("This type of property not exist");
                       isPostNewPropertySuccessfully = false;
                   }else {
                       if (newProperty.getType() == Property.NORMAL_APARTMENT ||
                               newProperty.getType() == Property.PENTHOUSE )
                       {
                           System.out.println("Please enter the floor of the property");
                           int floorProperty = scanner.nextInt();
                           newProperty.setFloor(floorProperty);
                       }
                       System.out.println("How many rooms does the property have?");
                       Integer roomNum = scanner.nextInt();
                       newProperty.setRoomNum(roomNum);
                       System.out.println("What is the house number?");
                       Integer houseNum = scanner.nextInt();
                       newProperty.setHouseNum(houseNum);
                       System.out.println("Press 1 if the property for rent OR press 2 if the property for sale");
                       int propertyRentOrSale = scanner.nextInt();
                       if (propertyRentOrSale == 1)
                       {
                           newProperty.setForRent(true);
                       }
                       while (propertyRentOrSale!= 1 && propertyRentOrSale !=2)
                       {
                           System.out.println("Please press 1 OR 2");
                           System.out.println("Press 1 if the property for rent OR press 2 if the property for sale");
                           propertyRentOrSale = scanner.nextInt();
                           if (propertyRentOrSale == 1)
                           {
                               newProperty.setForRent(true);
                           }
                       }
                       System.out.println("What is the required price for the property?");
                       float propertyPrice = scanner.nextFloat();
                       newProperty.setPrice(propertyPrice);
                       addProperty(newProperty);
                   }

                }
            }

        }
        return isPostNewPropertySuccessfully;
    }

    public void printAllProperties()
    {
        if (this.properties == null)
        {
            System.out.println("NO properties in the system");
        }else {
            for (int i = 0; i<this.properties.length; i++)
            {
                System.out.println("The properties in the system:");
                System.out.println("number " + (i+1));
                System.out.println(this.properties[i]);
                System.out.println();
            }
        }
    }

    public Property[] search()
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("At each step you can enter -999 to IGNORE that parameter");
        System.out.println("Press 1 if you want property for rent OR press 2 if you want property for sale");
        int propertyRentOrSale = scanner.nextInt();
        while (propertyRentOrSale!= Property.FOR_RENT && propertyRentOrSale != Property.FOR_SALE
                && propertyRentOrSale!=-999)
        {
            System.out.println("Please press 1 OR 2 OR -999");
            System.out.println("Press 1 if you want property for rent OR press 2 if you want property for sale");
            propertyRentOrSale = scanner.nextInt();

        }
        System.out.println("Which type of property you looking for?");
        System.out.println("Please press 1 for NORMAL APARTMENT");
        System.out.println("Please press 2 for PENTHOUSE");
        System.out.println("Please press 3 for PRIVATE HOUSE");
        Integer tapeOfProperty = scanner.nextInt();
        while (tapeOfProperty!= Property.NORMAL_APARTMENT && tapeOfProperty !=Property.PENTHOUSE
                && tapeOfProperty !=Property.PRIVATE_HOUSE && tapeOfProperty!=-999)
        {
            System.out.println("Please choose between 1 to 3 OR -999");
            System.out.println("Please press 1 for NORMAL APARTMENT");
            System.out.println("Please press 2 for PENTHOUSE");
            System.out.println("Please press 3 for PRIVATE HOUSE");
            tapeOfProperty = scanner.nextInt();
        }
        System.out.println("How many rooms?");
        Integer roomsNum = scanner.nextInt();

        System.out.println("Enter Min Price");
        float minPrice = scanner.nextInt();

        System.out.println("Enter Max Price");
        float maxPrice = scanner.nextInt();
        Property[] propertiesByParameters = createPropertiesByParameters(propertyRentOrSale,tapeOfProperty,roomsNum,minPrice,maxPrice);
        //לבדוק ולטפל במקרה של כפילויות של נכסים לדוגמא נכס שהוא גם להשכרה וגם 4 חדרים יספר פעמיים
        return propertiesByParameters;
    }

    private int countPropertiesByParameters (int propertyRentOrSale,Integer tapeOfProperty,
                                               Integer roomsNum,float minPrice,float maxPrice)
    {   int countPropertiesByParameters = 0;
        if (this.properties!=null)
        {
            for (int i = 0; i< this.properties.length; i++)
            {
               if (propertyRentOrSale!=-999)
               {
                   if (propertyRentOrSale == Property.FOR_RENT)
                   {
                       if (this.properties[i].isForRent())
                       {
                           countPropertiesByParameters++;
                       }
                   }else {
                       if (!this.properties[i].isForRent())
                       {
                           countPropertiesByParameters++;
                       }
                   }
               }else if (tapeOfProperty!=-999)
                {
                    if (tapeOfProperty == Property.NORMAL_APARTMENT)
                    {
                        if (this.properties[i].getType() ==Property.NORMAL_APARTMENT )
                        {
                            countPropertiesByParameters++;
                        }

                    } else if (tapeOfProperty == Property.PENTHOUSE) {
                        if (this.properties[i].getType() ==Property.PENTHOUSE )
                        {
                            countPropertiesByParameters++;
                        }
                    }else if (this.properties[i].getType() ==Property.PRIVATE_HOUSE)
                    {
                        countPropertiesByParameters++;
                    }
                }else if (roomsNum!=-999)
                {
                    if (this.properties[i].getRoomNum() == roomsNum)
                    {
                        countPropertiesByParameters++;
                    }
                }else if (minPrice !=-999)
                {
                    if (this.properties[i].getPrice()>=minPrice)
                    {
                        countPropertiesByParameters++;
                    }
                }else if (maxPrice !=-999)
                {
                    if (this.properties[i].getPrice()<=maxPrice)
                    {
                        countPropertiesByParameters++;
                    }
                }

            }
        }
        return countPropertiesByParameters;
    }

    private Property[] createPropertiesByParameters (int propertyRentOrSale,Integer tapeOfProperty,
                                             Integer roomsNum,float minPrice,float maxPrice  )
    {   Property[] propertiesByParameters = null;
        int countPropertiesByParameters = countPropertiesByParameters(propertyRentOrSale,tapeOfProperty,
                roomsNum, minPrice, maxPrice);
        if (this.properties!=null && countPropertiesByParameters >0)
        {   propertiesByParameters = new Property[countPropertiesByParameters];
            int indexPropertiesByParameters=0;
            for (int i = 0; i< this.properties.length; i++)
            {
                if (propertyRentOrSale!=-999)
                {
                    if (propertyRentOrSale == Property.FOR_RENT)
                    {
                        if (this.properties[i].isForRent())
                        {
                           propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                           indexPropertiesByParameters++;
                        }
                    }else {
                        if (!this.properties[i].isForRent())
                        {
                            propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                            indexPropertiesByParameters++;
                        }
                    }
                }else if (tapeOfProperty!=-999)
                {
                    if (tapeOfProperty == Property.NORMAL_APARTMENT)
                    {
                        if (this.properties[i].getType() ==Property.NORMAL_APARTMENT )
                        {
                            propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                            indexPropertiesByParameters++;
                        }

                    } else if (tapeOfProperty == Property.PENTHOUSE) {
                        if (this.properties[i].getType() ==Property.PENTHOUSE )
                        {
                            propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                            indexPropertiesByParameters++;
                        }
                    }else if (this.properties[i].getType() ==Property.PRIVATE_HOUSE)
                    {
                        propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                        indexPropertiesByParameters++;
                    }
                }else if (roomsNum!=-999)
                {
                    if (this.properties[i].getRoomNum() == roomsNum)
                    {
                        propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                        indexPropertiesByParameters++;
                    }
                }else if (minPrice !=-999)
                {
                    if (this.properties[i].getPrice()>=minPrice)
                    {
                        propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                        indexPropertiesByParameters++;;
                    }
                }else if (maxPrice !=-999)
                {
                    if (this.properties[i].getPrice()<=maxPrice)
                    {
                        propertiesByParameters[indexPropertiesByParameters] = this.properties[i];
                        indexPropertiesByParameters++;
                    }
                }

            }
        }
        return propertiesByParameters;
    }


    public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int removeNum;
        Property[] userProperties= listUserProperties( user);
        if(userProperties==null){
            System.out.print("There is not property");

        }
        else{ printUserProperty(userProperties);
            System.out.println("Which property you want to remove?");
            removeNum= scanner.nextInt();
            while (removeNum>userProperties.length || removeNum<1){
                System.out.println("chose number between 1 to " + userProperties.length);
                removeNum=scanner.nextInt();
            }
            userProperties[removeNum-1]=null;
        }

    }
    private void printUserProperty(Property[] userProperties){
        if(userProperties!=null){
            for (int i =0; i<userProperties.length; i++){
                System.out.println("property number "+i);
                System.out.println(userProperties[i]);
                System.out.println();
            }
        }
    }


    public void printProperties(User user)
    {
        Property[] listUserProperties = listUserProperties(user);
        if (listUserProperties ==null)
        {
            System.out.println("The user have NO properties in the system");
        }else {
            for (int i = 0; i<listUserProperties.length; i++)
            {
                System.out.println("The properties of the user: ");
                System.out.println("number " + (i+1));
                System.out.println(listUserProperties[i]);
                System.out.println();
            }
        }
    }

    private Property[] listUserProperties(User user)
    {
        Property[] listUserProperties = null;

        if (this.properties != null)
        {
            int countUserProperties =0;
            for (int i = 0; i<this.properties.length; i++)
            {
                if (this.properties[i].getUserPublish() == user)
                {
                    countUserProperties++;
                }
            }
            if (countUserProperties != 0)
            {
                listUserProperties = new Property[countUserProperties];
                int indexOfListUserProperties = 0;
                for (int i = 0; i<this.properties.length; i++)
                {
                    if (this.properties[i].getUserPublish() == user)
                    {
                        listUserProperties[indexOfListUserProperties] = this.properties[i];
                    }
                }

            }
        }
        return listUserProperties;
    }
    private void addProperty( Property newProperty)
    {
        if (this.properties == null)
        {
            Property[] newProperties = new Property[1];
            newProperties[0]=newProperty;
            this.properties= newProperties;
        }else {
            Property[] newProperties = new Property[this.properties.length+1];
            for (int i = 0; i<this.properties.length; i++)
            {
                newProperties[i] = this.properties[i];
            }
            newProperties[this.properties.length]=newProperty;
            this.properties= newProperties;
        }
    }

    private City getCity(String cityName)
    {
        City getCity = null;
        for (int i = 0; i < CITIES.length; i++)
        {
            if (CITIES[i].isEquals(cityName))
            {
                getCity = CITIES[i];
                break;
            }
        }
        return getCity;
    }
    private void printCityName (City[] cities)
    {
        for (int i = 0; i < cities.length; i++)
        {
            System.out.print( cities[i].getName() + ",");

        }
    }

    private boolean isAuthorizedToPublish(User user)
    {
        boolean isAuthorizedToPublish = true;
        if (this.properties!= null)
        {
            int countPublish = 0;
            for (int i=0; i<this.properties.length;i++)
            {
                if (this.properties[i].getUserPublish() == user)
                {
                    countPublish++;
                }
            }

            if (user.isMediator())
            {
                if (countPublish==5)
                {
                    isAuthorizedToPublish = false;
                }
            }else {
                if (countPublish==2)
                {
                    isAuthorizedToPublish = false;
                }
            }
        }

        return isAuthorizedToPublish;
    }

    private User checkUserExist(String name, String password) {
        User userExist = null;
        if (this.users != null)
        {
            for (int i=0; i<this.users.length;i++)
            {
                if (this.users[i].isEquals(name,password) )
                {
                    userExist = this.users[i];
                    break;
                }
            }
        }
       return  userExist;
    }


    private void addUser(User newUser)
    {
       if (this.users == null)
       {
           User[] newUsers = new User[1];
           newUsers[0]=newUser;
           this.users = newUsers;
       }else {
           User[] newUsers = new User[this.users.length + 1];
           for (int i = 0; i<this.users.length; i++)
           {
               newUsers[i]=this.users[i];
           }
           newUsers[this.users.length]=newUser;
           this.users = newUsers;
       }
    }

    private boolean isExistUserName(String name)
    {
        boolean isExistUserName = false;
        if (this.users!=null)
        {
            for (int i = 0; i<this.users.length; i++)
            {
                if (this.users[i].getName()== name)
                {
                    isExistUserName = true;
                    break;
                }
            }
        }
        return isExistUserName;
    }
}
