package com.company.humanResources;

import java.util.*;

public class LinkedList<T> implements List<T>{
    private class Node<T>{
        private T data;
        private Node<T> nextNode;

        public Node(T t){
            data = t;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private final Node<T> DEFAULT_HEAD = null;
    private final Node<T> DEFAULT_TAIL = null;
    private static final int DEFAULT_SIZE = 0;

    public LinkedList(){
        this.head = DEFAULT_HEAD;
        this.tail = DEFAULT_TAIL;
        size = DEFAULT_SIZE;
    }

    public LinkedList(Node<T> head){
        this.head = head;
        this.tail = head;
        this.size = 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if(this.head == null && this.size == 0)
            return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        T t = (T) o;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            if(t.equals(iterator.next()))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if(index < size)
                    return true;
                return false;
            }

            @Override
            public T next() {
                if(hasNext()){
                    Node<T> temp = null;
                    for(int i = 0; i < index + 1; i++){
                        temp = head.nextNode;
                    }
                    index++;
                    return temp.data;
                }
                return null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        Node<T> temp = head;
        for(int i = 0; i < size; i++){
            objects[i] = temp.data;
            temp = temp.nextNode;
        }
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) toArray();
    }

    @Override
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t);
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.nextNode = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> temp = head;
        if(temp.data.equals(o)){
            head = head.nextNode;
            return true;
        }
        while (temp.nextNode != null){
            if(temp.nextNode.data.equals(o)){
                if(tail == temp.nextNode){
                    tail = temp;
                }
                temp.nextNode = temp.nextNode.nextNode;
                return true;
            }
            temp = temp.nextNode;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        T[] collection = (T[]) c.toArray();
        for(int i = 0; i < collection.length; i++){
            if(!add(collection[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        T[] collection = (T[]) c.toArray();
        for(int i = 0; i < collection.length; i++){
            if(!remove(collection[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        Node<T> temp = head;
        while (temp.nextNode != null){
            temp.
        }
    }

    @Override
    public T get(int index) {
        T[] ts = (T[]) toArray();
        return ts[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
