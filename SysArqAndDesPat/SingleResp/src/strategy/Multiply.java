/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

/**
 *
 * @author raga
 */
public class Multiply implements Strategy{

    @Override
    public void operation(int num1, int num2) {
        System.out.println(num1*num2);
    }
    
}
