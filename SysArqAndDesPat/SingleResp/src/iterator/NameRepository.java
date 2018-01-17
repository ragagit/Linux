/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

/**
 *
 * @author raga
 */
public class NameRepository {
    private String [] names = { "Adam", "John", "Sarah"};
    
    public Iterator getIterator(){
        return new NameIterator( names );
    }
    
}
