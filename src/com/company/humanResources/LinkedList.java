package com.company.humanResources;

import org.omg.CORBA.NO_IMPLEMENT;

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
            size--;
            return true;
        }
        while (temp.nextNode != null){
            if(temp.nextNode.data.equals(o)){
                if(tail == temp.nextNode){
                    tail = temp;
                }
                size--;
                temp.nextNode = temp.nextNode.nextNode;
                return true;
            }
            temp = temp.nextNode;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if(head == null)
            return false;
        if(head == tail && c.toArray().length > 1)
            return false;
        T[] ts = (T[])c.toArray();
        Node<T> temp = head;
        for(int i = 0; i < ts.length; i++){
            while(temp.nextNode != null){
                if(!temp.data.equals(ts[i]))
                    return false;
                temp = temp.nextNode;
            }
        }
        return true;
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
        T[] ts = (T[]) c.toArray();
        for(int i = index; i < index + ts.length; i++){
            add(i, ts[i]);
        }
        return true;
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
        if(head == null)
            return false;
        T[] ts = (T[]) c.toArray();
        Node<T> newHead = null;
        Node<T> newTail = null;
        int newSize = 0;
        Node<T> temp = head;
        Node<T> newNode;
        for(int i = 0; i < ts.length; i++){
            while(temp.nextNode != null){
                if(temp.data.equals(ts[i])){
                    newNode = new Node<>(ts[i]);
                    if(newHead == null){
                        newHead = newNode;
                        newTail = newNode;
                    }
                    else{
                        newTail.nextNode = newNode;
                        newTail = newNode;
                    }
                    newSize++;
                    break;
                }
                temp = temp.nextNode;
            }
        }
        this.head = newHead;
        this.tail = newTail;
        this.size = newSize;
        return true;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        Node<T> temp = this.head;
        for(int i = 0; i < index + 1; i++){
            temp = temp.nextNode;
        }
        return temp.data;
    }

    @Override
    public T set(int index, T element) {
        Node<T> temp = this.head;
        for(int i = 0; i < index + 1; i++){
            temp = temp.nextNode;
        }
        T t = temp.data;
        temp.data = element;
        return t;
    }

    @Override
    public void add(int index, T element) {
        Node<T> newNode = new Node<>(element);
        Node<T> prevNode = head;
        Node<T> temp;
        for(int i = 0; i < index; i++){
            prevNode = prevNode.nextNode;
        }
        temp = prevNode.nextNode;
        prevNode.nextNode = newNode;
        prevNode.nextNode.nextNode = temp;
    }

    @Override
    public T remove(int index) {
        Node<T> temp = head;
        Node<T> previous = null;
        for(int i = 0; i < index + 1; i++){
            previous = temp;
            temp = temp.nextNode;
        }
        T t = temp.data;
        previous.nextNode =temp.nextNode.nextNode;
        temp.data = null;
        temp.nextNode = null;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> temp = head;
        int index = 0;
        while(temp.nextNode != null){
            if(temp.data.equals(o))
                return index;
            temp = temp.nextNode;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<T> temp = head;
        int index = 0;
        int res = -1;
        while(temp.nextNode != null){
            if(temp.data.equals(o))
                res = index;
            temp = temp.nextNode;
            index++;
        }
        return res;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
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
                    return LinkedList.this.get(index);
                }
                return null;
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                return index++;
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                LinkedList.this.remove(index);
            }

            @Override
            public void set(T t) {
                LinkedList.this.set(index, t);
            }

            @Override
            public void add(T t) {
                LinkedList.this.add(t);
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int newIndex) {
        return new ListIterator<T>() {
            int index = newIndex;

            @Override
            public boolean hasNext() {
                if(index < size)
                    return true;
                return false;
            }

            @Override
            public T next() {
                if(hasNext()){
                    return LinkedList.this.get(index);
                }
                return null;
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                return index++;
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                LinkedList.this.remove(index);
            }

            @Override
            public void set(T t) {
                LinkedList.this.set(index, t);
            }

            @Override
            public void add(T t) {
                LinkedList.this.add(t);
            }
        };
    }

    @Override
    public LinkedList<T> subList(int fromIndex, int toIndex) {
        T[] ts = (T[]) toArray();
        LinkedList<T> newList = new LinkedList<>();
        for(int i = fromIndex; i < toIndex + 1; i++){
            newList.add(ts[i]);
        }
        return newList;
    }
}
