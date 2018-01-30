/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
#include <gtest/gtest.h>
#include "Box.h"

/*
 How to set up google test:
 * - Get package from 
 *      https://github.com/google/googletest
 *      https://github.com/google/googletest/releases
 * - Unzip it into a directory <gtest_dir>
 * - Create a C++ static library project, like gtest
 * - Add gtest-all.cc and gtest_main.cc  to thee Source Files folder.
 * - Go to the project properties and add <gtest_dir>/include into C++ include directories
 * - Create the project you want to test.
 * - You can create a directory under Test Files and add the cpp test file like this.
 * - Right click on the test folder you just created to go to properties.
 * - Go to linker and libraries click on ... on the right and select add project, choose gtest
 * - Right click on test folder and choose Test to run the test.
 * 
 *  ASSERT_TRUE(condition);	EXPECT_TRUE(condition);     condition is true
    ASSERT_FALSE(condition);	EXPECT_FALSE(condition);    condition is false
 * 
 *  ASSERT_EQ(val1,val2);	EXPECT_EQ(val1,val2);	val1 == val2
    ASSERT_NE(val1,val2);	EXPECT_NE(val1,val2);	val1 != val2
    ASSERT_LT(val1,val2);	EXPECT_LT(val1,val2);	val1 < val2
    ASSERT_LE(val1,val2);	EXPECT_LE(val1,val2);	val1 <= val2
    ASSERT_GT(val1,val2);	EXPECT_GT(val1,val2);	val1 > val2
    ASSERT_GE(val1,val2);	EXPECT_GE(val1,val2);	val1 >= val2
 * 
 *  ASSERT_STREQ(str1,str2);	EXPECT_STREQ(str1,str2);	the two C strings have the same content
    ASSERT_STRNE(str1,str2);	EXPECT_STRNE(str1,str2);	the two C strings have different content
    ASSERT_STRCASEEQ(str1,str2);	EXPECT_STRCASEEQ(str1,str2);	the two C strings have the same content, ignoring case
    ASSERT_STRCASENE(str1,str2);	EXPECT_STRCASENE(str1,str2);	the two C strings have different content, ignoring case
 
 * The rule of thumb is to use EXPECT_* when you want the test to continue to reveal more errors after the assertion failure, 
 * and use ASSERT_* when continuing after failure doesn't make sense. 
 
 * 
 */


// Test with Fixtures
class MyBoxTest: public ::testing::Test{
    
    protected:
        virtual void SetUp(){
            box.setBreadth(3);
            box.setHeight(5);
            box.setLenght(7);
        }
        
        virtual void TearDown(){
            
        }
        
        Box box;
};

//First argument is the test case, the second is the test. A test case can have many test
TEST( TestBox, testVolume ){
    
    Box box( 2, 2 , 3);
    
    EXPECT_EQ( 12, box.CalculateVolume() );
    EXPECT_LT( 10, box.CalculateVolume() );
    
}

TEST_F( MyBoxTest, testBox ){
    
    EXPECT_EQ( 7, box.getLenght() );
    
}
