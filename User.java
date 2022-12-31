public class User {

    private String name;
    private String password;
    private String cellPhone;
    private boolean isMediator;

    public User(String name,String password,String cellPhone,boolean isMediator)
    {
        this.name=name;
        if (checkPassword (password))
        {
            this.password= password;
        }
        if (checkCellPhone(cellPhone))
        {
            this.cellPhone= cellPhone;
        }
        this.isMediator= isMediator;
    }


    public String toString()
    {
        String output ="";
        output += "Name: "+ this.name +"\n";
        if (this.isMediator())
        {
            output+= "real estate broker \n" ;
        }else {
            output+= "Not real estate broker \n" ;
        }
        if (this.password != null)
        {
            output +="Password: "+ this.password + "\n";
        }
        if (this.cellPhone!= null)
        {
            output+= "CellPhone: "+ this.cellPhone;
        }

        return output;
    }

    public boolean isEquals(String name, String password)
    {
        boolean isEquals = false;
        if (this.name == name && this.password == password)
        {
            isEquals=true;
        }
        return isEquals;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCellPhone() {
        return this.cellPhone;
    }

    public boolean isMediator() {
        return this.isMediator;
    }

    public void setCellPhone(String cellPhone)
    {
        if (checkCellPhone(cellPhone)){
            this.cellPhone = cellPhone;
        }
    }

    public void setPassword(String password)
    {
        if (checkPassword(password)){
            this.password = password;
        }

    }

    public boolean checkPassword (String password)
    {
      boolean isCorrectPassword = true;
      if (password!=null){
          if(password.length()<5)
          {
              isCorrectPassword = false;
          } else if (countDigitsInText(password)<1) {
              isCorrectPassword = false;
          } else if (!password.contains("$")&&!password.contains("%")&&!password.contains("_") ) {
              isCorrectPassword = false;
          }
      }

      return isCorrectPassword;
    }

    public boolean checkCellPhone ( String cellPhone)
    {
        boolean isCorrectPhone = true;
        if (cellPhone!=null)
        {
            if (cellPhone.length()!= 10)
            {
                isCorrectPhone = false;
            }else if (cellPhone.charAt(0) != '0'|| cellPhone.charAt(1) != '5')
            {
                isCorrectPhone = false;
            } else if (countDigitsInText(cellPhone)!=cellPhone.length()) {
                isCorrectPhone = false;
            }
        }

        return isCorrectPhone;
    }

    private int countDigitsInText(String text)
    {
        int countNum = 0;
        for (int i=0; i<text.length();i++)
        {
            if(Character.getNumericValue(text.charAt(i))>=0
                    && Character.getNumericValue(text.charAt(i))<=9 )
                countNum++;

        }
        return countNum;
    }

}
