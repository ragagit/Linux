/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: raga
 *
 * Created on February 19, 2018, 11:15 AM
 */

#include <cstdlib>
#include <list>
#include <iostream>
#include <vector>
#include <mutex>
#include <sstream>


using namespace std;

class ReverseList{
private:
    vector<string>m_list;
    
public:
    ReverseList( vector<string>& inlist):m_list(inlist){
        
    }
    
    void reverseList(){

        vector<string>::iterator it;
        vector<string> reverse;
        
        if( m_list.size() < 2)
            return;
        
        for( it = m_list.end(); it != m_list.begin()-1; it--){
           reverse.push_back(*it);
        }
        
        vector<string>::iterator it_;
        for( it_= reverse.begin(); it_ != reverse.end(); it_++){
            cout << *it_ << endl;
        }
    }
};

class MySingleton{
   
private:
    
    static MySingleton *instance;
    static mutex lock;
    
    
    MySingleton(){
        instance = NULL;
    }
    
public:
    
    static MySingleton *getInstance(){
        unique_lock<mutex> ul(lock);
        if( instance == NULL){
            instance = new MySingleton();
            return instance;
        }else{
            return instance;
        }
            
    }
    
};

void getChars(string str){
    
    //char[] tmp = { 'a', 'b', 'c' };
    char tmp1[] = {'a', 'b'};
    stringstream ss;
    
    char* rev = new char[str.size()];
    
    int idx = str.size() - 1;
    
    for( char c : str){
        rev[idx--] = c;
    }
        cout << rev << endl;
        
        char *start = &str[0];
        char *end = &str[str.size()-1];
        char temp;
        
        while( start < end ){
            
            temp = *start;
            *start = *end;
            *end = temp;
            start++;
            end--;
            
        }
        
        cout << str << endl;
    
//        string str1;
//        for( int j = 0; j < 2;  j++)
//            //str1[j] = (tmp1[j]);
//            ss << tmp1[j];
//        
//    str1 = ss.str();
//    cout << str[0] << endl;
//    cout << str1 << endl;
    
}

MySingleton *MySingleton::instance = NULL;
mutex MySingleton::lock;

int main(int argc, char** argv) {

//    vector<string> list;  
//    list.push_back("One");
//    list.push_back("Two");
//    list.push_back("Three");
//    list.push_back("Four");
//    list.push_back("Five");
//    list.push_back("Six");
//    list.push_back("Seven");
//    list.push_back("Eight");
//    list.push_back("Nine");
//    list.push_back("Ten");    
//    ReverseList revList(list);    
//    revList.reverseList();
    
//    MySingleton *ms1 = ms1->getInstance();
//    MySingleton *ms2 = ms2->getInstance();
//    
//    cout << ms1 << endl;
//    cout << ms2 << endl;
    getChars("Hello Wolrd!");
    
    return 0;
    
}

