/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Box.cpp
 * Author: raga
 * 
 * Created on January 29, 2018, 9:27 PM
 */

#include "Box.h"

    Box::Box() : length(0), breadth(0), height(0) {

    }

    Box::Box(int l, int b, int h) : length(l), breadth(b), height(h) {

    }

    Box::Box(const Box& other) {
        length = other.length;
        breadth = other.breadth;
        height = other.height;
    }

    int Box::getLenght() {
        return length;
    }

    int Box::getBreadth() {
        return breadth;
    }

    int Box::getHeight() {
        return height;
    }

    long Box::CalculateVolume() {
        return length * breadth * height;
    }

    bool Box::operator<(const Box &b) {

        if (length < b.length)
            return true;
        else if ((breadth < b.breadth) && (length == b.length))
            return true;
        else if ((height < b.height) && (breadth == b.breadth) && (length == b.length))
            return true;
        else
            return false;
    }

Box::~Box() {
}

