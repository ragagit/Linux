/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   ActiveDetectorTask.h
 * Author: raga
 *
 * Created on January 30, 2018, 2:21 PM
 */

#ifndef ACTIVEDETECTORTASK_H
#define ACTIVEDETECTORTASK_H

class IHeartRateSensor{
public:
    virtual int getRate() = 0;
};

class ISweatRateSensor{
public:
    virtual int getRate() = 0;
};

class ActiveDetectorTask{
    
public:
    
    ActiveDetectorTask( float minHeartRate, float minSweatRate ):_minHeartRate(minHeartRate), _minSweatRate(minSweatRate){}
    bool isActive(   IHeartRateSensor& heartRateSensor,   ISweatRateSensor& sweatRateSensor)  {
        return heartRateSensor.getRate() > _minHeartRate && sweatRateSensor.getRate() > _minSweatRate;
    }
    
    float minHeartRate() { return _minHeartRate; }
    float minSweatRate() { return _minSweatRate; }
    
private:
    float _minHeartRate;
    float _minSweatRate;
};


#endif /* ACTIVEDETECTORTASK_H */

