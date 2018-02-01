/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: raga
 *
 * Created on January 31, 2018, 11:23 AM
 */

#include <cstdlib>
#include <iostream>
#include <mutex>
#include <pthread.h>
#include <queue>
#include <thread>
#include <sstream>
#include <string>
#include <thread>
#include <chrono>

using namespace std;

class IWorkItem{

protected:    
    string m_message;
    int m_number;
    
public:
    
    virtual string getMessage() = 0;
    virtual int getNumber() = 0;
    void setMessage( string message ){
        m_message = message;
    }
    void setNumber( int number ){
        m_number = number;
    }
    virtual ~IWorkItem(){}
    
};

class WorkItem : public IWorkItem{
    
public:
    
    WorkItem( const string message, const int number ){
        setMessage(message);
        setNumber(number);
    }
    
    virtual string getMessage(){
        return m_message;
    }
    virtual int getNumber(){
        return m_number;
    }
    virtual ~WorkItem(){}
    
};


class MyQueue{
    
    private:
        queue<IWorkItem *> items;
        mutex mx;
        condition_variable cv;
        
    public:
        void addItem( IWorkItem* item ){
            unique_lock<mutex> lock(mx);
            items.push(item);
            cv.notify_all();
        }
        
        IWorkItem* getItem(){
            unique_lock<mutex> lock(mx);
            while( items.size() == 0 ){
                cout << "No items in queue, waiting ..." << endl;          
                cv.wait(lock);
            }
            
            IWorkItem* item = items.front();
            items.pop();
            
            return item;
        }  
        
        int getSize(){
            unique_lock<mutex> lock(mx);
            return items.size();
        }
    
};

class MyConsumer{
    
private:
    MyQueue& queue;
    
public:
    MyConsumer( MyQueue& queue ) : queue(queue){
        
    }
    
    void processItem(){

        IWorkItem* item; 
        
        while( queue.getSize() ){
            item =  nullptr;
            item = queue.getItem(); 
            if( item ){
                cout << "Thread:" << this_thread::get_id() << " " << item->getMessage() << " " << item->getNumber() << endl;           
                delete item;
            }
            this_thread::sleep_for(std::chrono::seconds(1));
        }
    }
    
    void run(){
        
        thread t1 = thread(&MyConsumer::processItem, this);
        thread t2 = thread(&MyConsumer::processItem, this);
        //cout << "Thread: " << t1.get_id() << " created" << endl;
        //cout << "Thread: " << t2.get_id() << " created" << endl;
        t1.join();
        t2.join();
    }
  
    
};




int main(int argc, char** argv) {
    
    
    MyQueue queue;
    IWorkItem * item;
    string msg = "Message";
    
    for( int i = 0; i < 10; i++ ){
     
        item = new WorkItem( msg, i );
        queue.addItem( item );
        
    }
    
    MyConsumer Con1(queue);

    
    Con1.run();

    
    
   
   
    return 0;
}

