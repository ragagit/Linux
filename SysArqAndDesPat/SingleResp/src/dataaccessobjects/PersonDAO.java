/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccessobjects;

import java.util.List;

/**
 *
 * @author raga
 */
public interface PersonDAO {
    public void insert(Personn person);
    public void update( Personn person);
    List<Personn> getPeople();  
}
