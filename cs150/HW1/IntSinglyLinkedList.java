
import java.util.NoSuchElementException;

public class IntSinglyLinkedList { 
    private static class Node {
        int value;
        Node next;
       
        Node(int value) {
            this.value = value;
            this.next = null;
        } 
    }

        Node head;
        int size;

    public IntSinglyLinkedList() {
       
        head = null;
        size = 0;
    }
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;

    }
    public void addLast(int value) {
        Node newNode = new Node(value);
        if(head ==null) {
            head = newNode;
        
        }
        else
            {
            Node current = head;
            while(current.next != null) {
                current = current.next;
           
            }
            current.next = newNode;
        
        }
        size++;
    }
    public int get(int index) {
        if (index < 0 || index>=size) {
            throw new IndexOutOfBoundsException("Index: "+ index);
       
        }
        Node current = head;
       
        for (int i = 0; i < index;i++) {
            current = current.next;

        }
        return current.value;
    }
    public int removeFirst() {
        if(head == null) {
           throw new NoSuchElementException("List is Empty.");

        }
        int value = head.value;
        head = head.next;
        size--;
       
        return value;
    }
    public boolean removeValue(int value) {
        if(head == null){
            return false;
        }
        if(head.value == value) {
            head = head.next;
            size--;
            return true;
        }
       
        Node current = head;
        
        while(current.next != null) {
           
            if(current.next.value == value) {
               
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;


    }
    public int size() {
        return size;

    }
    public boolean isEmpty() {
        return size == 0;

    }
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node current = head;
        while(current != null) {

            sb.append(current.value);
            if(current.next != null) {
                sb.append(", ");
            }

            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
}