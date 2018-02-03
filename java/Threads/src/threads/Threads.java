/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Threads {

    public static void main(String[] args) {
       
            Message msg = new Message("Message");
            Consumer con = new Consumer(msg);
            Producer prod = new Producer(msg);
            con.start();
            prod.start();
                   
    }

}

class Message {
    private String msg;
    
    public Message(String str){
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String str) {
        this.msg=str;
    }

}
class Consumer extends Thread {

    private Message msg;
    
    Consumer(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        
        synchronized(msg)
        {
            try {
                System.out.println("Consumer id:" + getName() + " Waiting...");
                sleep(1000);
                msg.wait();
                System.out.println("Getting message: " + msg.getMsg());
                System.out.println("Continuing...");
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }

    }
}

class Producer extends Thread {

    private Message msg;
    
    Producer(Message msg) {

        this.msg = msg;
    }

    @Override
    public void run() {
        
        System.out.println("Producer id:" + getName());
        
        synchronized(msg){
            try {
                sleep(7000);
                
                System.out.println("Waking up thread...");
                
                msg.setMsg("Adding message to queue");
                
                msg.notifyAll();
                
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
       
        }
    }
}
