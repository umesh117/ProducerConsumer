package com.umesh.motadata;

import com.umesh.motadata.dto.MessageData;
import com.umesh.motadata.dto.MessageQueue;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author umesh.b
 * ProducerConsumer
 */
class MessageQueueTest {

    @Test
    void testQueueFill() {
        MessageQueue queue = new MessageQueue();
        assertEquals(true, queue.isEmpty());
        queue.add(new MessageData("1", 1.0, 0));
        assertEquals(false, queue.isFull());
        queue.add(new MessageData("1", 1.0, 0));
        assertEquals(false, queue.isFull());
        queue.add(new MessageData("1", 1.0, 0));
        assertEquals(true, queue.isFull());
    }

    @Test
    void testQueueConsume() {
        MessageQueue queue = new MessageQueue();
        while (!queue.isFull()) {
            queue.add(new MessageData("1", 1.0, 0));
        }
        assertEquals(true, queue.isFull());
        while (!queue.isEmpty()) {
            assertEquals(false, queue.isEmpty());
            queue.poll();
        }
        assertEquals(true, queue.isEmpty());
    }

    @Test
    void testQueueSameElements() {
        MessageQueue queue = new MessageQueue();
        Set<MessageData> messages = new HashSet<>();
        while (!queue.isFull()) {
            MessageData data = new MessageData(UUID.randomUUID().toString(), 1.0, 0);
            messages.add(data);
            queue.add(data);
        }
        while (!queue.isEmpty()) {
            assertEquals(true, messages.contains(queue.poll()));
        }
    }
}
