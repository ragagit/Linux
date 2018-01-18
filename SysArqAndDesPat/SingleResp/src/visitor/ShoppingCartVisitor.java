/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

/**
 *
 * @author raga
 */
public interface ShoppingCartVisitor {
    public double visit( Table table );
    public double visit( Chair chair );
}
