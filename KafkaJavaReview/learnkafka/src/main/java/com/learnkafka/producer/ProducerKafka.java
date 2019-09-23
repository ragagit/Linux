package com.learnkafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerKafka {

    public static void main(String[] args) {
        Properties properties=new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // This is for Approach 4. It needs to implement Partitioner
        //properties.put("partitioner.class","com.learnkafka.PartitionerImpl");

        KafkaProducer<String,String> myProducer= new KafkaProducer<String,String>(properties);

        try {

            for(int i=0;i<3;i++){
                myProducer.send(new  ProducerRecord<String, String>("my-fifth-topic", "New Message Value : " + Integer.toString(i)));
                //myProducer.send(new  ProducerRecord<String, String>("my-fifth-topic", "url:<local-directory-path>/file"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            myProducer.close();
        }
    }

}
