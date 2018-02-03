/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   AsyncFilter.h
 * Author: raga
 *
 * Created on January 30, 2018, 6:53 PM
 */

#ifndef ASYNCFILTER_H
#define ASYNCFILTER_H

#include <mutex>
#include <vector>
#include <thread>

using namespace std;

class AsyncFilter{
    
public:
    AsyncFilter( mutex& mutex, condition_variable& cv, vector<float>& data) : _mutex(mutex), _cv(cv), _signalData(data){}
    void start(){
        _thread = thread{[this]{
            
            warmUp();
            {
                unique_lock<mutex> lock{_mutex};
                _cv.wait(lock);
                filterSignal();
            }
            _cv.notify_one();
        }
            
            
        };
    }
    
    void setData(vector<float>& data){ _signalData = data; }
    const vector<float>& data() const { return _signalData; }
private:
    void warmUp(){
        // Prepare filter
    }
    
    void filterSignal(){
        
    }
    
    thread _thread;
    mutex& _mutex;
    condition_variable& _cv;
    vector<float>& _signalData;
};

void loadData( vector<float>& outData ){
    
}



#endif /* ASYNCFILTER_H */

