/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: raga
 *
 * Created on February 2, 2018, 11:07 AM
 */

#include <cstdlib>
#include <iostream>
#include <string>
#include <memory>
#include <sstream>

using namespace std;

/*
 * 
 */

class OuterClass{

private:
string outmessage;

public:
    OuterClass(string& msg):outmessage(msg){}
    void showMessage(){
        cout << outmessage << endl;
    }
    
    class InnerClass{
    private:
        string inmessage;
    public:
        InnerClass(string& msg):inmessage(msg){}
        void showMessage(){
            cout << inmessage << " from InnerClass" << endl;
        }
    };
};


//class Singleton
//{
//private:
//	Singleton(const Singleton&) = delete;
//	Singleton & operator=(const Singleton&) = delete;
//	
//
//	static std::unique_ptr<Singleton> instance;
//	static std::once_flag onceFlag;
//public:
//	Singleton() = default;
//
//	static void NofityInit()
//	{
//		std::cout << "Initializing Singleton" << '\n';
//	}
//	static Singleton& Singleton::Instance()
//	{
//		std::call_once(Singleton::onceFlag,[] (){
//			NofityInit();
//			instance.reset(new Singleton); 
//		});
//
//		std::cout << "Getting  Singleton instance" << '\n';
//		return *(instance.get());
//	}
//};

//std::unique_ptr<Singleton> Singleton::instance;
//std::once_flag Singleton::onceFlag;


int main(int argc, char** argv) {
    
//    string msg = "Hello";
//    OuterClass *oc = new OuterClass(msg);
//    OuterClass::InnerClass *ic = new OuterClass::InnerClass(msg);
//    oc->showMessage();
//    ic->showMessage();
//    delete oc;
//    delete ic;
//    
//    
//    Singleton& s1 = Singleton::Instance();
//    Singleton& s2 = Singleton::Instance();
//    

    cout << "Number of Arguments: " << argc << endl; 
    cout << argv[0] << endl;

string line;

getline(cin, line);

if(line.empty()){
    cout << "Empty entry";
}

while( line.compare("0") != 0 ){
    
    cout << line << endl;
    getline(cin, line);
       
}



  
    return 0;
}

