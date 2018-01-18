/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

/**
 *
 * @author raga
 */
public class AlgorithmFactory {
    public static final int SHORTEST_PATH = 0;
    public static final int SPANNING_PATH = 1;
    
    //This is where we encapsulate object creation
    public static Algorithmm createAlgorithm( int type ){
        
        switch( type ){
            case SHORTEST_PATH:
                return new ShortestPath();
                
            case SPANNING_PATH:
                return new SpanningTree();
                
        }
        
        return null;
        
    }
    
}
