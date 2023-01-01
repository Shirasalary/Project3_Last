public class Property {

    private City city;
    private String street;
    private Integer roomNum;
    private float price;
    private Integer type;
    private boolean isForRent;
    private Integer houseNum;
    private int floor;
    private User userPublish;
    public static final int NORMAL_APARTMENT = 1;
    public static final int PENTHOUSE = 2;
    public static final int PRIVATE_HOUSE = 3;
    public static final int FOR_RENT = 1;
    public static final int FOR_SALE = 2;

    //סיבוכיות של o(N)
    public Property(City city,String street,Integer roomNum,float price,Integer type,
                    boolean isForRent,Integer houseNum, int floor,User userPublish){
        this.city=city;
        this.roomNum=roomNum;
        this.price=price;
        this.isForRent=isForRent;
        this.houseNum=houseNum;
        this.floor=floor;
        this.userPublish=userPublish;
        if(!getPrintableType(type).equals("Invalid")) {
            this.type = type;
        }
        if(city.checkStreetInCity(street)){
            this.street=street;
        }
    }

    //סיבוכיות של o(1)
    public void setForRent(boolean forRent) {
        this.isForRent = forRent;
    }

    //סיבוכיות של o(1)
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    //סיבוכיות של o(1)
    public void setFloor(int floor) {
        this.floor = floor;
    }

    //סיבוכיות של o(1)
    public void setPrice(float price) {
        this.price = price;
    }

    //סיבוכיות של o(1)
    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    //סיבוכיות של o(1)
    public Integer getType() {
        return this.type;
    }

    //סיבוכיות של o(1)
    public User getUserPublish() {
        return this.userPublish;
    }


    //סיבוכיות של o(1)
    public Integer getRoomNum() {
        return this.roomNum;
    }

    //סיבוכיות של o(1)
    public boolean isForRent() {
        return this.isForRent;
    }

    //סיבוכיות של o(1)
    public float getPrice() {
        return this.price;
    }

    //סיבוכיות של o(1)
    private String getPrintableType(Integer type)
     {
         String output = "Invalid" ;
         if(type!=null)
         {
             switch (type)
             {
                 case NORMAL_APARTMENT -> output = "Normal apartment";
                 case PENTHOUSE -> output = "Penthouse";
                 case PRIVATE_HOUSE -> output = "Private house";
             }
         }

         return output;
     }

    //סיבוכיות של o(1)
     public String toString()
     {
         String output = "";
         output+= this.city.getName() + " - " + this.street +"\n";
         if(this.type!=null)
         {
             output+=getPrintableType(this.type) + " - ";
             if (!this.isForRent)
             {
                output+= "Not for rent :";
             }else {
                 output+= "for rent :";
             }
             output+=this.roomNum + " rooms, floor " +this.floor + "\n";
             output+= "Price: "+ this.price + "\n";
         }
         output+="Contact info: "+ this.userPublish.getName() +" " +this.userPublish.getCellPhone() +" ";
         if (userPublish.isMediator())
         {
             output+= "(real estate broker)";
         }else {
             output+= "( Not real estate broker)";
         }
         return output;
     }
}
