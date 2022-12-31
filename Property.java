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

    public void setForRent(boolean forRent) {
        this.isForRent = forRent;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public Integer getType() {
        return this.type;
    }

    public User getUserPublish() {
        return this.userPublish;
    }


    public Integer getRoomNum() {
        return this.roomNum;
    }

    public int getFloor() {
        return this.floor;
    }

    public Integer getHouseNum() {
        return this.houseNum;
    }

    public boolean isForRent() {
        return this.isForRent;
    }

    public float getPrice() {
        return this.price;
    }

    private String getPrintableType(Integer type)
     {
         String output;
         switch (type) // צריך להוסיף אם טייפ לא שווה לנל? כמו במחלקת סיטי
         {
             case NORMAL_APARTMENT -> output = "Normal apartment";
             case PENTHOUSE -> output = "Penthouse";
             case PRIVATE_HOUSE -> output = "Private house";
             default -> output = "Invalid";
         }
         return output;
     }

     public String toString()
     {
         String output = "";
         output+= this.city.getName() + " - " + this.street +"\n";
         if(this.type!=null)//צריך צלשנות לאם לא שווה לנל ולבדוק את השאר
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
