/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include "ActiveDetectorTask.h"

#include <gtest/gtest.h>
#include <gmock/gmock.h>



using ::testing::AtLeast;

class MyHeartRateSensorMock: public IHeartRateSensor{
public:
    
    MOCK_METHOD0(getRate, int());   
};

class MySweatRateSensorMock: public ISweatRateSensor{
public:
    MOCK_METHOD0(getRate, int());
};

TEST( ActiveDetectorTask, test1){
    
    ActiveDetectorTask adt(23.5, 45.7);
    
    MyHeartRateSensorMock mockHeartSensor;
    MySweatRateSensorMock mockSweatSensor;
    
    EXPECT_CALL( mockHeartSensor, getRate()).WillRepeatedly(testing::Return(54));
    EXPECT_CALL( mockSweatSensor, getRate()).WillRepeatedly(testing::Return(57));
    
    EXPECT_TRUE(adt.isActive(mockHeartSensor, mockSweatSensor));
    
}