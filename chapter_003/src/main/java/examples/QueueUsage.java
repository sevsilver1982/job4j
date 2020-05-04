package examples;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueUsage {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        //add() - добавляем элемент в конец очереди
        queue.add(9);
        queue.add(8);
        queue.add(7);
        queue.add(6);
        queue.add(5);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(1);
        System.out.println(queue);
        //poll() - удаляем верхний элемент из очереди
        queue.poll();
        System.out.println(queue);
        //remove() - удаляем элемент из очереди
        queue.remove(7);
        System.out.println(queue);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(15);
        System.out.println(priorityQueue);
    }

}