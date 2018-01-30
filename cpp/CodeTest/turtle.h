/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   turtle.h
 * Author: raga
 *
 * Created on January 30, 2018, 12:50 PM
 */

#ifndef TURTLE_H
#define TURTLE_H

#pragma once

class Turtle {

public:

  virtual ~Turtle() {}
  virtual void PenUp() = 0;
  virtual void PenDown() = 0;
  virtual void Forward(int distance) = 0;
  virtual void Turn(int degrees) = 0;
  virtual void GoTo(int x, int y) = 0;
  virtual int GetX() const = 0;
  virtual int GetY() const = 0;

};

#endif /* TURTLE_H */

