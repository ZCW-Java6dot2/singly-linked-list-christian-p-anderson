package com.zipcodewilmington.singlylinkedlist;

/**
 * Created by leon on 1/10/18.
 */

/**
 * LinkedList is part of the Collection framework
 * a linear data structure where the elements are not stored in contiguous locations
 * and every element is a separate object with a data part and address/node part
 */

public class SinglyLinkedList<T extends Comparable<T>> {

    private class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        private T data;
        private Node<T> address;

        public Node(T data) {
            this.data = data;
        }
        
        public Node(T data, Node<T> address) {
            this.data = data;
            this.address = address;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getAddress() {
            return this.address;
        }

        public void setAddress(Node<T> address) {
            this.address = address;
        }

        @Override
        public int compareTo(Node<T> n) {
            return this.data.compareTo(n.data);
        }
    }

    private int size = 0;
    private Node<T> addressHead;

    public SinglyLinkedList(Node<T> addressHead) {
        this.addressHead = addressHead;
    }

    public SinglyLinkedList() {

    }

    // add -- add an element to the list
    public void add (T data) {
        size++;
        if (this.addressHead == null) {
            this.addressHead = new Node<T>(data);
        } else {
            Node<T> addressTail = this.addressHead;

            while(addressTail.getAddress() != null) {
                addressTail = addressTail.getAddress();
            }
            addressTail.setAddress(new Node<T>(data));
        }
    }

    // remove -- remove an element (specified by numeric index) from the list
    public void remove(int index) {
        if(index < 0 || index >= size) {
            throw new RuntimeException("Node does not exist within bounds.");
        } else {
            Node<T> current = this.addressHead;
            Node<T> prior = null;

            for (int i = 0; i < index; i++) {
                prior = current;
                current = current.getAddress();
            }

            if (prior == null) {
                this.addressHead = current.getAddress();
            } else {
                prior.setAddress(current.getAddress());
            }
            size--;
        }
    }

    // contains -- returns true if the element is in the list, false otherwise
    public boolean contains(T data) {
        Node<T> current = this.addressHead;
        for (int i = 0; i < size; i ++) {
            if (current.getData().equals(data)) {
                return true;
            } else {
                current = current.getAddress();
            }
        }
        return false;
    }

    // find -- returns the element's index if it is in the list, -1 otherwise
    public int find(T data) {
        Node<T> current = this.addressHead;
        for (int i = 0; i < size; i++) {
            if (current.getData().equals(data)) {
                return i;
            } else {
                current = current.getAddress();
            }
        }
        return -1;
    }

    // size -- returns the current size of the list
    public Integer size() {
        return size;
    }

    // get -- returns the element at the specified index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Node does not exist");
        } else {
            Node<T> current = this.addressHead;
            for (int i = 0; i < index; i++) {
                current = current.getAddress();
            }
            return current.getData();
        }
    }

    // copy -- returns a new linked list containing the same values (look
    // up deep versus shallow copy)

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        Node<T> current = this.addressHead;

        for (int i = 0; i < this.size(); i++) {
            newList.add(current.getData());
            current = (current.getAddress());
        }
        return newList;
    }

    public void sort() {
        Node<T> lowNode = this.addressHead,
                lowPriorNode = null,
                currentNode,
                lowAddress,
                currentPriorNode,
                tempNode;

        for (int i = 0; i < size; i++) {
            currentNode = lowNode;
            for (int j = i+1; j < size; j++) {
                currentPriorNode = currentNode;
                currentNode = currentNode.getAddress();
                if (currentNode == null) break;

                // Need to swap
                if (currentNode.compareTo(lowNode) < 0) {
                    lowAddress = lowNode.getAddress();
                    // Swap low node first

                    lowNode.setAddress(currentNode.getAddress());
                    if (currentPriorNode != lowNode) {
                        currentPriorNode.setAddress(lowNode);
                    }

                    if (lowPriorNode == null) {
                        if (currentNode != lowAddress) {
                            currentNode.setAddress(lowAddress);
                        } else {
                            currentNode.setAddress(lowNode);
                        }
                        this.addressHead = currentNode;
                    } else {
                        if (currentNode != lowAddress) {
                            currentNode.setAddress(lowAddress);
                        } else {
                            currentNode.setAddress(lowNode);
                        }
                        lowPriorNode.setAddress(currentNode);
                    }
                    tempNode = currentNode;
                    currentNode = lowNode;
                    lowNode = tempNode;
                }
            }
            lowPriorNode = lowNode;
            lowNode = lowNode.getAddress();
        }
    }
}
