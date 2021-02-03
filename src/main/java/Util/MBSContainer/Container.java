package Util.MBSContainer;

import java.util.Iterator;

public interface Container<E> {
    boolean add(E e);

    E get(int index);

    E remove(int index);

    boolean remove(Object o);

    int size();

    boolean isEmpty();

    Iterator<E> iterator();
}
