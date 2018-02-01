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
#include <list>

using namespace std;

// -- pthreads ---
//template <typename T> class wqueue
//{
//    list<T>   m_queue;
//    pthread_mutex_t m_mutex;
//    pthread_cond_t  m_condv;
//
//public:
//    wqueue() {
//        pthread_mutex_init(&m_mutex, NULL);
//        pthread_cond_init(&m_condv, NULL);
//    }
//    ~wqueue() {
//        pthread_mutex_destroy(&m_mutex);
//        pthread_cond_destroy(&m_condv);
//    }
//    void add(T item) {
//        pthread_mutex_lock(&m_mutex);
//        m_queue.push_back(item);
//        pthread_cond_signal(&m_condv);
//        pthread_mutex_unlock(&m_mutex);
//    }
//    T remove() {
//        pthread_mutex_lock(&m_mutex);
//        while (m_queue.size() == 0) {
//            pthread_cond_wait(&m_condv, &m_mutex);
//        }
//        T item = m_queue.front();
//        m_queue.pop_front();
//        pthread_mutex_unlock(&m_mutex);
//        return item;
//    }
//    int size() {
//        pthread_mutex_lock(&m_mutex);
//        int size = m_queue.size();
//        pthread_mutex_unlock(&m_mutex);
//        return size;
//    }
//};
//
//class WorkItem_
//{
//    string m_message;
//    int    m_number;
//
//  public:
//    WorkItem_(const char* message, int number)
//          : m_message(message), m_number(number) {}
//    ~WorkItem_() {}
//
//    const char* getMessage() { return m_message.c_str(); }
//    int getNumber() { return m_number; }
//};
//
//class ConsumerThread : public Thread
//{
//    wqueue<WorkItem_*>& m_queue;
//
//  public:
//    ConsumerThread(wqueue<WorkItem_*>& queue) : m_queue(queue) {}
//
//    void* run() {
//        // Remove 1 item at a time and process it. Blocks if no items are
//        // available to process.
//        for (int i = 0;; i++) {
//            printf("thread %lu, loop %d - waiting for item...\n",
//                  (long unsigned int)self(), i);
//            WorkItem_* item = (WorkItem_*)m_queue.remove();
//            printf("thread %lu, loop %d - got one item\n",
//                  (long unsigned int)self(), i);
//            printf("thread %lu, loop %d - item: message - %s, number - %d\n",
//                  (long unsigned int)self(), i, item->getMessage(),
//                   item->getNumber());
//            delete item;
//        }
//        return NULL;
//    }
//};

// -- std::threads ---

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

    // -- POSIX Threads --
//    if ( argc != 2 ) {
//        printf("usage: %s <iterations>\n", argv[0]);
//        exit(-1);
//    }
//    int iterations = atoi(argv[1]);
//
//    // Create the queue and consumer (worker) threads
//    wqueue<WorkItem*>  queue;
//    ConsumerThread* thread1 = new ConsumerThread(queue);
//    ConsumerThread* thread2 = new ConsumerThread(queue);
//    thread1->start();
//    thread2->start();
//
//    // Add items to the queue
//    WorkItem* item;
//    for (int i = 0; i < iterations; i++) {
//        item = new WorkItem("abc", 123);
//        queue.add(item);
//        item = new WorkItem("def", 456);
//        queue.add(item);
//        item = new WorkItem("ghi", 789);
//        queue.add(item);
//        sleep(2);
//    }
//
//    // Ctrl-C to end program
//    sleep(1)
//    printf("Enter Ctrl-C to end the program...\n");
//    while (1);
//    exit(0);
   
   
    return 0;
}

