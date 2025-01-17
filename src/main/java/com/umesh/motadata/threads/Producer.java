package com.umesh.motadata.threads;

import com.umesh.motadata.dto.MessageData;
import com.umesh.motadata.dto.MessageQueue;

import java.time.Instant;
import java.util.UUID;

/**
 * @author umesh.b
 * ProducerConsumer
 */
public class Producer extends Thread {

    private MessageQueue queue;
    private int dataSize;

    public Producer(MessageQueue queue, int dataSize) {
        this.queue = queue;
        this.dataSize = dataSize;
    }

    @Override
    public void run() {
        System.out.println(Instant.now() + " : " + "P-thread started");
        createData();
    }

    private void createData() {
        int i = 0;
        while (i < dataSize) {
            if (queue.isFull()) {
                System.out.println(Instant.now() + " : " + "queue is full. Waiting for message processing...");
            }
            MessageData data = new MessageData(UUID.randomUUID().toString(), (0.0 + i++), 0);
            System.out.println(Instant.now() + " : " + "Producer: " + Instant.now() + " : " + data);
            queue.add(data);
        }
    }
}
