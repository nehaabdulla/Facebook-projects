package com.baabtra.user.Sprint5;

/**
 * Created by user on 4/18/2016.
 */
public class Bean {
    private String first_name;
    private String last_name;
    private String address;
    private String Mobile_no;
    private String Email;
    // private int a;


    public Bean(String firstname,String lastname,String address,String mobile,String Email) {
        this.first_name=firstname;
        this.last_name=lastname;
        this.address=address;
        this.Mobile_no=mobile;
        this.Email=Email;

    }


    public String getFirstName() {
        return first_name;
    }
    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMobile_no(){
        return Mobile_no;
    }
    public void setMobile_no(String Mobile_no){
        this.Mobile_no= Mobile_no;
    }
    public  String getEmail(){
        return Email;
    }
    public void setEmail(String Email){
        this.Email=Email;
    }

}
