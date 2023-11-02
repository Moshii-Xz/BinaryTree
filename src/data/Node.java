package data;

import java.io.Serializable;

public class Node<E extends Comparable<E>> implements Serializable {
    public E data;
    public Node<E> left;
    public Node<E> right;

    public Node(E data) {
        this.data = data;
    }
}