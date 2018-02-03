/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javatestbucket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author raga
 */

 class Employee {
 
    
    @JsonProperty("emp_id")
    private int empId = 1016;
    private String name = "Nataraja Gootooru";
    private String designation = "Programmer";
    private String department = "Java2Novice";
    //@JsonIgnore
    private int salary = 20000;
    private Date doj;
     
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("************************************");
        sb.append("\nempId: ").append(empId);
        sb.append("\nname: ").append(name);
        sb.append("\ndesignation: ").append(designation);
        sb.append("\ndepartment: ").append(department);
        sb.append("\nsalary: ").append(salary);
        sb.append("\n************************************");
        return sb.toString();
    }
     
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    } 
    public Date getDoj() {
        return doj;
    }
 
    public void setDoj(Date doj) {
        this.doj = doj;
    }
}


public class MyJason {
 
    public static void main(String[] a){
         
        Employee emp = new Employee();
        ObjectMapper mapperObj = new ObjectMapper();
         
        try {
            
            //Writing the object to file and screen
            File outFile = new File("output.txt");            
            String jsonStr = mapperObj.writeValueAsString(emp);
            System.out.println(jsonStr);
            mapperObj.writeValue(outFile, emp);
            
            
            //Reading the file
            File inFile = new File("output.txt");
            Employee emp_ = mapperObj.readValue(inFile, Employee.class);
            System.out.println(emp_);
                       
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // JSON string to map
        String jsonStr = "{\"name\":\"Nataraj\", \"job\":\"Programmer\"}";
        Map<String,String> resultMap = new HashMap<String,String>();
        ObjectMapper mapperObj1 = new ObjectMapper();
         
        System.out.println("Input Json: "+jsonStr);
        try {
            resultMap = mapperObj1.readValue(jsonStr, 
                            new TypeReference<HashMap<String,String>>(){});
            System.out.println("Output Map: "+resultMap);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //map to JASON
        ObjectMapper mapperObj2 = new ObjectMapper();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("name", "Java2Novice");
        inputMap.put("site", "http://java2novice.com");
        // convert map to JSON String
        try {
            String jsonResp = mapperObj2.writeValueAsString(inputMap);
            System.out.println(jsonResp);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // JSON pretty format
        ObjectMapper mapperObj3 = new ObjectMapper();
         
        try {
            // get Employee object as a json string
            String jsonStr1 = mapperObj3.writeValueAsString(emp);
            System.out.println("json output in compact mode:\n");
            System.out.println(jsonStr);
            String jsonPrettyStr 
                    = mapperObj3.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
            System.out.println("\n\njson output in pretty print mode:\n");
            System.out.println(jsonPrettyStr);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // You can use @JasonProperty("name") to name properties
        // Also you can use @JasonIgnore to ignore properties.
        // You can use @JasonPropertyOrder to specify an order, the orser is usually random
        // If you want to ignore NULL or empties 
        // @JsonInclude(Include.NON_NULL)
        // @JsonInclude(Include.NON_EMPTY)
        
        // Reading a node
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            // reading json input from the file and mapping to object
            File jsonInputFile = new File("output.txt");
             
            JsonNode rootNode = mapper.readTree(jsonInputFile);
            // read employee id
            JsonNode empId = rootNode.path("emp_id");
            System.out.println(empId.asInt());
            
            // read employee name
            JsonNode empName = rootNode.path("name");
            System.out.println(empName.asText());
            // read direct reports
//            JsonNode drNode = rootNode.path("direct_reports");
//            Iterator<JsonNode> itr = drNode.getElements();
//            System.out.println("\nDirect reports:");
//            while (itr.hasNext()) {
//                JsonNode temp = itr.next();
//                System.out.println(temp.getTextValue());
//            }
             
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // To read from URL
//        ObjectMapper mapper1 = new ObjectMapper();
//        try {
//            Post usrPost = mapper.readValue(new URL("http://jsonplaceholder.typicode.com/posts/7"), Post.class);
//            System.out.println(usrPost);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
    }
}
