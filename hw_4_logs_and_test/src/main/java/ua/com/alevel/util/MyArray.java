package ua.com.alevel.util;

import java.util.Iterator;
import java.util.Random;

public class MyArray<E> implements Array<E> {

    private E[] values;

    public MyArray(){
        values = (E[]) new Object[0];
    }

    @Override
    public boolean add(E e) {
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length + 1];
            System.arraycopy(temp,0,values,0,temp.length);
            values[values.length - 1] = e;
            return false;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
     return false;
    }

    @Override
    public void delete(int index) {
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length - 1];
            System.arraycopy(temp,0,values,0,index);
            int amountElementAfterIndex = temp.length - index - 1;
            System.arraycopy(temp,index + 1, values, index, amountElementAfterIndex);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public E getRandom() {
        int random = new Random().nextInt(values.length);
        return values[random];
    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public void update(int index, E e) {
        values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<E>(values);
    }

    @Override
    public String toString() {
        if (values == null)
            return null;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) builder.append(values[i]);
            else builder.append(values[i]).append(", ");
        }
        return "[" + builder + "]";
    }
}
