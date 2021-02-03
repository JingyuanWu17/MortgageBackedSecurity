package Util.MBSContainer;

import MBSData.Pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoolsContainer implements Container<Pool>{
    private List<Pool> poolList;
    private Itr itr;

    public PoolsContainer() {
        poolList = new ArrayList<>();
        itr = new Itr();
    }

    @Override
    public boolean add(Pool pool) {
        return poolList.add(pool);
    }

    @Override
    public Pool get(int index) {
        return poolList.get(index);
    }

    @Override
    public Pool remove(int index) {
        return poolList.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return poolList.remove(o);
    }

    @Override
    public int size() {
        return poolList.size();
    }

    @Override
    public boolean isEmpty() {
        return poolList.isEmpty();
    }

    @Override
    public Iterator<Pool> iterator() {
        itr.reset();
        return itr;
    }

    private class Itr implements Iterator<Pool> {
        int idx;

        @Override
        public boolean hasNext() {
            return idx < poolList.size();
        }

        @Override
        public Pool next() {
            return poolList.get(idx++);
        }

        public void reset() {
            idx = 0;
        }
    }
}
