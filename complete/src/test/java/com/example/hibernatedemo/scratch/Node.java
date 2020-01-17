package com.example.hibernatedemo.scratch;
public class Node extends ListItem
{

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem getNext() {
        return this.nextItem;
    }
    @Override
    ListItem getPrevious() {
        return this.previousItem;
    }
    @Override
    void setNextItem(ListItem n) {
        this.nextItem = n;
    }
    @Override
    void setPreviousItem(ListItem p) {
        this.previousItem = p;
    }
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    int CompareTo(ListItem item)
    {
        if (item != null) {
            return ((String)super.getValue()).compareTo((String)item.getValue());
        } else {
            return -1;
        }
    }
}