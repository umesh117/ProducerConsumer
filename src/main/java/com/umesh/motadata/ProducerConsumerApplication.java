package com.umesh.motadata;

import com.umesh.motadata.dto.MessageQueue;
import com.umesh.motadata.dto.MessageStats;
import com.umesh.motadata.threads.Consumer;
import com.umesh.motadata.threads.Producer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author umesh.b
 * ProducerConsumer
 */
public class ProducerConsumerApplication {

    public static void main(String[] args) {

        /*
         * dataSize : Number of elements that will be generated by producer thread
         * producerThreadCount : Number of producer thread to generate
         * consumerThreadCount : Number of consumer thread to generate
         */
        int dataSize = 10;
        int producerThreadCount = 1;
        int consumerThreadCount = 1;
        if (args.length == 3) {
            dataSize = Integer.parseInt(args[0]);
            producerThreadCount = Integer.parseInt(args[1]);
            consumerThreadCount = Integer.parseInt(args[2]);
        }

        //success, fail and total, this property is maintained by this variable
        AtomicReference<MessageStats> stats = new AtomicReference<>(new MessageStats());

        MessageQueue queue = new MessageQueue();

        for (int i = 0; i < producerThreadCount; i++) {
            Producer producer = new Producer(queue, dataSize);
            producer.start();
        }

        for (int i = 0; i < consumerThreadCount; i++) {
            Consumer consumer = new Consumer(queue, stats, dataSize * producerThreadCount);
            consumer.start();
        }
        //system execution is done when the data consumed is matched with produced data
    }
}
