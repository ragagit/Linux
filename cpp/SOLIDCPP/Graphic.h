/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Graphic.h
 * Author: raga
 *
 * Created on January 28, 2018, 11:25 AM
 */

#ifndef GRAPHIC_H
#define GRAPHIC_H

#include <iostream>

using namespace std;

class Graphic
{
public:
  virtual void print() const = 0;
  virtual ~Graphic() {}
};
 
class Ellipse : public Graphic
{
public:
  void print() const {
    cout << "Ellipse \n";
  }
};
 
class CompositeGraphic : public Graphic
{
public:
  void print() const {
    for(Graphic * a : graphicList_) {
      a->print();
    }
  }
 
  void add(Graphic *aGraphic) {
    graphicList_.push_back(aGraphic);
  }
 
private:
  vector<Graphic*>  graphicList_;
};

#endif /* GRAPHIC_H */

