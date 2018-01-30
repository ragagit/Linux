/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Box.h
 * Author: raga
 *
 * Created on January 29, 2018, 9:27 PM
 */

#ifndef BOX_H
#define BOX_H

#include <iostream>

using namespace std;

class Box {
private:
    int length, breadth, height;

public:

    Box();
    Box(int l, int b, int h);
    Box(const Box& other);   
    ~Box();
    int getLenght();
    int getBreadth();
    int getHeight();
    long CalculateVolume();
    bool operator<(const Box &b);

    friend ostream& operator<<(ostream& out, const Box& B) {
        out << B.length << " " << B.breadth << " " << B.height;
        return out;
    }
};

#endif /* BOX_H */

