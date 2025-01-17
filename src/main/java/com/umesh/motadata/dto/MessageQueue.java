package com.umesh.motadata.dto;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author umesh.b
 * ProducerConsumer
 */
public class MessageQueue {

    private final BlockingQueue<MessageData> queue = new LinkedBlockingQueue<>(3);


    /**
     * if the queue is full, it will wait till the space is available
     *
     * @param data : newly created data element
     * @return true if the data is inserted, false otherwise
     */
    public boolean add(MessageData data) {
        try {
            queue.put(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * waits for 1 second to retrieve data otherwise returns null
     * @return messageData
     */
    public MessageData poll() {
        try {
            return queue.poll(1L, TimeUnit.SECONDS);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks if the queue is empty or not
     * @return true or false
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Checks if the queue is full or not
     * @return true or false
     */
    public boolean isFull() {
        return queue.remainingCapacity() == 0;
    }
}
