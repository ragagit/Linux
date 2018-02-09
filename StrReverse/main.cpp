/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: raga
 *
 * Created on February 7, 2018
 */

#include <cstdlib>
#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

/*
 * class to manipulate a string
 */

class ManipulateString{
    
private:
    string m_sInputString;
    
public:
    ManipulateString( const string inStr ):m_sInputString(inStr){
        
    }
    
    void getWordsFromStr( char delim, vector<string>& words );
    void reverseStrWords();
    void reverseStr();
    void collectWordsWithRorF( vector<string>& out );
    void sortWordsWithRF();
    
};

/*
 * This routine gets the words contained in a string separated by a delimeter
 * the result is placed in a vector.
 */
void ManipulateString::getWordsFromStr( char delim, vector<string>& words ){

    stringstream ss(m_sInputString);
    string word;
    
    while(getline(ss, word, delim)){
        words.push_back(word);
    }
}

/*
 * This function gets the words from a string and place them in reverse order
 */
void ManipulateString::reverseStrWords(){
    
    stringstream ss;
    vector<string> words;
  
    
    getWordsFromStr(' ', words);
    
    reverse( words.begin(), words.end() );
    
    for( vector<string>::iterator it = words.begin(); it != words.end(); it++){
        ss << *it << " ";
    }  
   
    
    cout << ss.str() << endl;    
    
}

/*
 * This function reverses a string
 */
void ManipulateString::reverseStr( ){
    
    string str = m_sInputString;
    
    reverse( str.begin(), str.end() );
       
    cout << str << endl;    
    
}

/*
 * This function gathers all the words containing r,R,f or F and return them in a vector.
 * this function could be improved by passing any character(s) maybe using variadics
 */
void ManipulateString::collectWordsWithRorF(vector<string>& out){
    
    vector<string> words;
    
    getWordsFromStr( ' ', words );
    
    for( vector<string>::iterator it = words.begin(); it != words.end(); it++){
        string str = *it;
        
        if( str.find("r") != std::string::npos  || 
            str.find("R") != std::string::npos  ||
            str.find("f") != std::string::npos  ||
            str.find("F") != std::string::npos  ){
            out.push_back(*it);
        }
    }    
      
}


/*
 * This function gets the words containing r,R,f or F and and sort them alphabetically
 * using a lambda expression
 */
void ManipulateString::sortWordsWithRF(){
    
    vector<string> in;
    
    collectWordsWithRorF( in );
        
    sort( in.begin(), in.end(), [](string& a, string& b){
        int i=0;
  
        while ((i < a.length()) && (i < b.length()))
        {
            if (tolower (a[i]) < tolower (b[i])) 
                return true;
            else if (tolower (a[i]) > tolower(b[i])) 
                return false;
            i++;
        }

        if (a.length() < b.length()) 
            return true;
        else 
            return false;
    
    } );
    
    for( string s : in ){
        cout << s << endl;
    }
   
    
}

int main(int argc, char** argv) {

    const string str = "Based in Waterloo, Ontario, and founded in 2007, 2G Robotics Inc. offers mechanical, electrical and software products and services for the mobile robotic industry, with particular emphasis on underwater robotic systems.";
        
    ManipulateString manStr(str);
    
    // Req. 1 - Reverse complete string (Option 1)
    manStr.reverseStr();
    
    // Req. 1 - Reverse string Words (Option 2)
    manStr.reverseStrWords();
  
    // Req. 2 - Collect words with r and f and sorted alphabetically 
    manStr.sortWordsWithRF() ;

    
    return 0;
}

