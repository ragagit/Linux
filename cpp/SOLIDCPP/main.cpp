/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: raga
 *
 * Created on January 26, 2018, 1:31 PM
 */

#include <cstdlib>
#include <vector>
#include "Database.h"
#include "DatabaseHandler.h"
#include "MySQLDatabase.h"
#include "OracleDatabase.h"
#include "Mediator.h"
#include "Graphic.h"

using namespace std;

/*
 * 
 */

void doDepInversion(){

    OracleDatabase *odb = new OracleDatabase();
    MySQLDatabase *mdb = new MySQLDatabase();
    
    DatabaseHandler *dbho = new DatabaseHandler(odb);
    DatabaseHandler *dbhm = new DatabaseHandler(mdb);
    dbho->connect();
    dbho->disconnect();
    dbhm->connect();
    dbhm->disconnect();
    
}


void doComposite(){
    
  // Initialize four ellipses
  const auto_ptr<Ellipse> ellipse1(new Ellipse());
  const auto_ptr<Ellipse> ellipse2(new Ellipse());
  const auto_ptr<Ellipse> ellipse3(new Ellipse());
  const auto_ptr<Ellipse> ellipse4(new Ellipse());
 
  // Initialize three composite graphics
  const auto_ptr<CompositeGraphic> graphic(new CompositeGraphic());
  const auto_ptr<CompositeGraphic> graphic1(new CompositeGraphic());
  const auto_ptr<CompositeGraphic> graphic2(new CompositeGraphic());
 
  // Composes the graphics
  graphic1->add(ellipse1.get());
  graphic1->add(ellipse2.get());
  graphic1->add(ellipse3.get());
 
  graphic2->add(ellipse4.get());
 
  graphic->add(graphic1.get());
  graphic->add(graphic2.get());
 
  // Prints the complete graphic (four times the string "Ellipse")
  graphic->print();
  
}

void doMediator()
{
    FileSelectionDialog fileDialog;

    int i = 1;

  cout << "Exit[0], Filter[1], Dir[2], File[3], Selection[4]: ";

    fileDialog.handleEvent(i - 1);
    //cout << "Exit[0], Filter[1], Dir[2], File[3], Selection[4]: ";
  
}

int main(int argc, char** argv) {

    //doDepInversion();
    doComposite();
    //doMediator();
    
    return 0;
}

