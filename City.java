public class City {

    private String name;
    private Integer region;
    private String[] streetList;
    public static final int NEGEV = 1;
    public static final int SOUTH = 2;
    public static final int CENTRAL = 3;
    public static final int SHARON = 4;
    public static final int NORTH = 5;
    public static final String[] STREETS1 = {"kinoor", "or", "kaplan", "harishonim"};
    public static final String[] STREETS2 = {"limon", "narkis", "tut", "dolev"};

    public static final City CITY_1 =new City("ashdod",2,STREETS1);
    public static final City CITY_2 =new City("ashkelon",2,STREETS2);
    public static final City CITY_3 =new City("dimona",1,STREETS1);
    public static final City CITY_4 =new City("beer sheva",1,STREETS2);
    public static final City CITY_5 =new City("tel aviv",3,STREETS1);
    public static final City CITY_6 =new City("rishon",3,STREETS2);
    public static final City CITY_7 =new City("hod hasharon",4,STREETS1);
    public static final City CITY_8 =new City("rahanana",4,STREETS2);
    public static final City CITY_9 =new City("tveria",5,STREETS1);
    public static final City CITY_10 =new City("rmat hagolan",5,STREETS2);

//סיבוכיות של o(1)
    public City(String name,Integer region,String[] streetList)
    {
        this.name= name;
        if (!getPrintableRegion(region).equals("Invalid"))
        {
            this.region= region;
        }
        this.streetList = streetList;
    }

    //סיבוכיות של o(1)
    public String getName()
    {
        return this.name;
    }

    //סיבוכיות של o(1)
    public boolean isEquals(String name)
    {
        boolean isEquals = false;
        if (this.name.equals(name))
        {
            isEquals = true;
        }
        return isEquals;
    }

    //סיבוכיות של o(N)
    public String toString()
    {
        String output="";
        output+="Name: " + this.name + "\n";
        if (this.region!=null)
        {
            output+= "Region: "+getPrintableRegion(this.region) +"\n";
        }
        if (this.streetList != null)
        {
            output+= "Street list: " + "\n";
            output+= printableStreetsList();
        }

        return output;
    }
    //סיבוכיות של o(1)
    private String getPrintableRegion(Integer region)
    {
        String output = "Invalid";
        if (region!=null)
        {  switch (region)
            {
                case NEGEV -> output = "Negev";
                case SOUTH -> output = "South";
                case CENTRAL -> output = "Central";
                case SHARON -> output = "Sharon";
                case NORTH -> output = "North";
            }
        }
        return output;
    }

    //סיבוכיות של o(N)
    public boolean checkStreetInCity(String street)
    {
        boolean isInCity = false;
        if(this.streetList != null) {

            for (int i = 0; i < this.streetList.length; i++) {
                if (this.streetList[i].equals(street)) {
                    isInCity = true;
                    break;
                }
            }
        }
        return isInCity;
    }

    //סיבוכיות של o(N)
    public String printableStreetsList()
    {
        String arrayValue ="";
        if (this.streetList!=null)
        {
            for (int i = 0; i < this.streetList.length; i++)
            {
              arrayValue+= this.streetList[i] +", ";
            }
        }
        return arrayValue;
    }
}
