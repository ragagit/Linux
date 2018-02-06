/*
*
* 
*
*/
package com.raga.javajdbc;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * 1.- Create a new project in NetBeans, New Project->Maven->Java Application
 * 2.- In pom.xml add Spring Framework dependencies 
 * 3.- Add main class and ApplicationContext context = new 
 *                          AnnotationConfigApplicationContext(Config.class);
 *     NOTE: Config.class is the class with the @Bean and @Configuration annotation.
 * 4.- Add a properties file in src/main/resources and in configuration class use @PropertySource
 * 5.- Create a class defining your table.
 * 6.- Create your config class adding @Configuration, @ComponentScan and @PropertySource if needed.
 * 7.- Create service interface. This could implement the CRUD methods. PersonService
 * 8.- Implement the interface.
 * 9.- Create Dao interface
 * 10.-Implement interface
 * 11.-Create database with CREATE DATABASE ragadb; use ragadb;
 * 12.-Create database table
 *      mysql> CREATE TABLE Person ( person_id MEDIUMINT NOT NULL AUTO_INCREMENT,
        -> first_name varchar(45),
        -> last_name varchar(45),
        -> age int NOT NULL,
        -> PRIMARY KEY (person_id) );
 * 13.-In the PersonDao implementation use BeanPropertyRowMapper(Person.class) to map from query to object
 */
public class JavaJDBC {
    
    public static void main( String args[] ){
  
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        PersonService personService = (PersonService) context.getBean("personService");
 
//        Person yashwant = new Person(1, "Yashwant", "Chavan", 32);
//        Person mahesh = new Person(2, "Mahesh", "Patil", 25);
//        Person vishal = new Person(3, "Vishal", "Naik", 40);
// 
//        personService.addPerson(yashwant);
//        personService.addPerson(mahesh);
//        personService.addPerson(vishal);
        
        
        System.out.println("Find All");
        List < Person > persons = personService.findAll();
        for (Person person: persons) {
            System.out.println(person);
        }
 
        System.out.println("Delete person Id = 3");
        int deleteMe = 3;
        personService.deletePerson(deleteMe);
 
        Person yashwant = new Person();
        yashwant.setFirstName("Yashwant - Updated");
        yashwant.setLastName("Chavan - Updated");
        yashwant.setAge(40);
 
        System.out.println("Update person Id = 1");
        int updateMe = 1;
        personService.editPerson(yashwant, updateMe);
 
        System.out.println("Find person Id = 2");
        Person person = personService.find(2);
        System.out.println(person);
 
        System.out.println("Find All Again");
        persons = personService.findAll();
        for (Person p: persons) {
            System.out.println(p);
        }
    }
    
}
