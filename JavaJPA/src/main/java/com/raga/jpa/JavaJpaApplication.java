package com.raga.jpa;

import com.raga.jpa.dao.IPersonDao;
import com.raga.jpa.entity.Person;
import com.raga.service.IPersonService;
import com.raga.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*

- Set up MySQL and prepare the database. If you haven't created the database do so with:

    create database [IF NOT EXIST] dbname;

    CREATE TABLE Person ( person_id MEDIUMINT NOT NULL AUTO_INCREMENT,
    -> first_name varchar(45),
    -> last_name varchar(45),
    -> age int NOT NULL,
    -> PRIMARY KEY (person_id) );

    alter table Person add email varchar(40) after last_name;

    insert into person values (3, 'John', 'Smith', 'jsmith@server', 25);

    update person set email='patil@server.com' where person_id=2;

    alter table Person add telephone varchar(15) after email;

    alter table person drop column telephone;

    delete from person where first_name='Mahesh';

- Create a SpringBoot Initializer Maven project with Web (this includes tomcat), JPA and MySQL
- In the application.properties add configuration for your mysql database

    spring.datasource.url=jdbc:mysql://localhost:3306/ragadb
    spring.datasource.username=root
    spring.datasource.password=P4ssw0rd
    spring.datasource.tomcat.max-wait=20000
    spring.datasource.tomcat.max-active=50
    spring.datasource.tomcat.max-idle=20
    spring.datasource.tomcat.min-idle=15

    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
    spring.jpa.properties.hibernate.id.new_generator_mappings = false
    spring.jpa.properties.hibernate.format_sql = true

    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE 

- Add Entity using @Entity and @Id and @GeneratedValue. Add Constructor and setter and getters for the columns.
- Add an interface for DAO
- Add a class that implements DAO interface. Add @Transactional, @Repository and @PersistenceContext EntityManager
- Add the service interface and implement the interface. In the implementation add an @Autowired to PersonDao
- Add the controller. Add an @Autowired to the PersonService interface.
- Add javax.servlet.jsp.jstl, taglibs, org.apache.tomcat.embed dependencies to pom.xml file.
- Add JSP pages in src/main/webapp/WEB-INF/views 
- Add in application.properties
    spring.mvc.view.prefix = /WEB-INF/views/
    spring.mvc.view.suffix = .jsp
    spring.mvc.static-path-pattern=/resources/**
- Make sure you have in your controller for MVC @Controller, this will link to the view. I had @RestController
  and the system wasn't taking me to the jsp page.
- In your controller use @RequestParam to get parameters from the form, use the Model to put info for the page

    @RequestMapping(value="person/showPerson")
    public String showPerson(@RequestParam("id") long id, Model model){
        
        Person person = personService.getPersonById(id);
        model.addAttribute("fName", person.getFirst_name());
        model.addAttribute("lName", person.getLast_name());
        model.addAttribute("email", person.getEmail());
        model.addAttribute("age", person.getAge());
        
        return "showPerson";
    }

- Add in the jsp page with the form proper name
    <form action="person/showPerson" method="post" >
      <table>
        <tr>
          <td>Enter Person id</td>
          <td><input id="id" name="id"></td>
          <td><input type="submit" value="Submit"></td>
        </tr>
      </table>
    </form>


*/

@SpringBootApplication
public class JavaJpaApplication implements CommandLineRunner{

    @Autowired
    //private IPersonDao personDao;
    private IPersonService personService;
    
    public static void main(String[] args) {
		SpringApplication.run(JavaJpaApplication.class, args);
    }
    
    @Override
    public void run(String... strings) throws Exception {
        
        try{
            //personService.addPerson(new Person("Sam", "Jackson", "sjackson@server.com", 42));
            //personDao.addPerson(new Person("Samuel", "Jackson", "sjackson@server.com", 42));
            //List<Person> personsList = personDao.getAllPersons();
            
//            for( Person person : personsList ){
//                System.out.println(person.getFirst_name());
//            }
        }catch( Exception e){
            throw new UnsupportedOperationException("Operation not supported");
        }
    }

	
}
