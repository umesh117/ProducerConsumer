package com.umesh.motadata;

import com.umesh.motadata.dto.MessageData;
import com.umesh.motadata.dto.MessageQueue;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author umesh.b
 * ProducerConsumer
 */
class ProducerTest {

    @Test
    void testQueueFull() {
        MessageQueue queue = new MessageQueue();
        int i = 0;
        while (i < 1000) {
            if (queue.isFull()) {
                break;
            }
            MessageData data = new MessageData(UUID.randomUUID().toString(), (0.0 + i++), 0);
            System.out.println(Instant.now() + " : " + "Producer: " + Instant.now() + " : " + data);
            queue.add(data);
        }
        assertEquals(true, queue.isFull());
    }
}
