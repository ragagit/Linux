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
public class NameIterator implements Iterator{

    private String[] names;
    private int index;

    public NameIterator(String[] names) {
        this.names = names;
        this.index = 0;
    }
    
    
    @Override
    public boolean hasNext() {
        return index < names.length;
    }

    @Override
    public Object next() {
        
        if( hasNext() ){
            return this.names[index++];
        }     
        
        return null;
    } 
}
