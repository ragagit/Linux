/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccessobjects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raga
 */
public class Database_ implements PersonDAO{

    List<Personn> people;
    
    public Database_() {
        this.people = new ArrayList<>();
    }

    
    @Override
    public void insert(Personn person) {
        people.add(person);
    }

    @Override
    public void update(Personn person) {
        people.remove(person);
    }

    @Override
    public List<Personn> getPeople() {
        return this.people;
    }
    
}
