package com.umesh.motadata;

import com.umesh.motadata.dto.MessageQueue;
import com.umesh.motadata.dto.MessageStats;
import com.umesh.motadata.threads.Consumer;
import com.umesh.motadata.threads.Producer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author umesh.b
 * ProducerConsumer
 */
class ConsumerTest {

    @Test
    void testQueueEmpty() throws Exception {
        MessageQueue queue = new MessageQueue();
        AtomicReference<MessageStats> stats = new AtomicReference<>(new MessageStats());
        int dataSize = 1000;
        Producer producer = new Producer(queue, dataSize);
        producer.start();
        Consumer consumer = new Consumer(queue, stats, dataSize );
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(true, queue.isEmpty());
    }

}
