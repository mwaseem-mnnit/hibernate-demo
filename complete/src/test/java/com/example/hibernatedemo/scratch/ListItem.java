package com.example.hibernatedemo.scratch;

public abstract class ListItem {
    protected ListItem previousItem = null;
    protected ListItem nextItem = null;
    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    abstract ListItem getNext();

    abstract ListItem getPrevious();

    abstract void setNextItem(ListItem n);

    abstract void setPreviousItem(ListItem p);

    abstract int CompareTo(ListItem item);
}