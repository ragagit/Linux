/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccessobjects;

/**
 *
 * @author raga
 */
public class Personn {
    private String name;
    private int age;
    private String gender;
    private String address;

    public Personn(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personn{" + "name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address + '}';
    }
    
    
    
}
