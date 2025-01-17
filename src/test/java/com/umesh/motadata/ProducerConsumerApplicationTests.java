package com.umesh.motadata;

import com.umesh.motadata.dto.MessageQueue;
import com.umesh.motadata.dto.MessageStats;
import com.umesh.motadata.threads.Consumer;
import com.umesh.motadata.threads.Producer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author umesh.b
 * ProducerConsumer
 */
class ProducerConsumerApplicationTests {

    @Test
    void mainTest() throws InterruptedException {
        int dataSize = 500, producerThreadCount = 1, consumerThreadCount = 1;
        AtomicReference<MessageStats> stats = new AtomicReference<>(new MessageStats());
        MessageQueue queue = new MessageQueue();
        for (int i = 0; i < producerThreadCount; i++) {
            Producer producer = new Producer(queue, dataSize);
            producer.start();
        }
        List<Thread> consumerThreads = new ArrayList<>();
        for (int i = 0; i < consumerThreadCount; i++) {
            Consumer consumer = new Consumer(queue, stats, dataSize * producerThreadCount);
            consumer.start();
            consumerThreads.add(consumer);
        }
        for (Thread thread : consumerThreads) {
            thread.join();
        }
        assertEquals(dataSize * producerThreadCount, stats.get().getTotal());
        assertEquals(dataSize * producerThreadCount, stats.get().getFail() + stats.get().getSuccess());
    }
}
