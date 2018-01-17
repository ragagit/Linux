/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solid;

/**
 *
 * @author raga
 */
public class Book implements BookInt{
    
    private int numOfpages;
    private String author;
    private BookPersistance BookPer;

    public Book(int numOfpages, String author) {
        this.numOfpages = numOfpages;
        this.author = author;
        this.BookPer = new BookPersistance();
    }

    public int getNumOfpages() {
        return numOfpages;
    }

    public void setNumOfpages(int numOfpages) {
        this.numOfpages = numOfpages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    //print() and save() have no direct relation to numOfPages and author,
    //so they should be moved out
//    @Override
//    public void print(){
//        System.out.println("Print function");
//    }
    
//    @Override
//    public void save(){
//        System.out.println("Saving file");
//    }
    public void save(){
        BookPer.save(this);
    }
    
    @Override
    public String toString() {
        return "Book{" + "numOfpages=" + numOfpages + ", author=" + author + '}';
    }
    
}
