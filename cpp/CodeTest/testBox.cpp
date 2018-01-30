/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <gtest/gtest.h>
#include "Box.h"

//First argument is the test case, the second is the test. A test case can have many test
TEST( TestBox, testVolume ){
    
    Box box( 2, 2 , 3);
    
    EXPECT_EQ( 12, box.CalculateVolume() );
    EXPECT_LT( 10, box.CalculateVolume() );
    
}


