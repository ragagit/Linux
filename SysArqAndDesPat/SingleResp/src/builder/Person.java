/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

/**
 *
 * @author raga
 */
public class Person {
    
    private String name;
    private String email;
    private String address;
    private int age;
    //if we want to add one more
    private String university;
    
    public Person( Builder builder){
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.age = builder.age;
        this.university = builder.university;
    }
    
    public static class Builder{
        private final String name; //Mandatory
        private final String email;//Mandatory
        private String address;
        private int age;
        private String university;

        public Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }
        
        public Builder setAddress(String address){
            this.address = address;
            return this;
        }
        
        public Builder setAge( int age ){
            this.age = age;
            return this;
        }
        
        public Builder setUniversity( String university ){
            this.university = university;
            return this;
        }
        
        public Person build(){
            return new Person(this);
        }

        
        public String showInfo(){
            return "name=" + this.name + " " + "email=" + this.email;
        }
       
    }
    
    @Override
    public String toString() {
         return "Builder{" + "name=" + name + ", email=" + email + ", address=" + address + ", age=" + age + ", university=" + university + '}';
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    
 
}
