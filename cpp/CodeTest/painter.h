/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   painter.h
 * Author: raga
 *
 * Created on January 30, 2018, 12:53 PM
 */

#ifndef PAINTER_H
#define PAINTER_H

#include "turtle.h"

class Painter
{
        Turtle* turtle;
public:
        Painter( Turtle* turtle )
                :       turtle(turtle){}

        bool DrawCircle(int, int, int){
                turtle->PenDown();
                return true;
        }
};

#endif /* PAINTER_H */

