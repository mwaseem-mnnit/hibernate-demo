package com.example.hibernatedemo.scratch;
public class LinkedList_1 {
    private ListItem root;

    public LinkedList_1(ListItem root) {
        this.root = root;
    }

    public ListItem getRoot() {
        return root;
    }

    public void setRoot(ListItem root) {
        this.root = root;
    }

    public void printList(ListItem root)
    {
        if (root == null) {
            System.out.println("This list is empty.");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.getNext();
            }
        }
    }

    public boolean addItem(ListItem newItem)
    {
        if (this.root == null) { // If no other item on list, newItem = first item on list
            this.root = newItem;
            return true;
        }
        ListItem current = this.root; // current = first item on list
        while (current != null) { // As long as current exists, do
            int comparison = (current.CompareTo(newItem)); // Alphabetically compares newItem and current. >0 = newItem comes AFTER current. <0 = newItem comes BEFORE current. 0 = newItem and current are identical.
            // ........................................................................................................
            if (comparison < 0) { // newItem should be inserted AFTER current
                if (current.getNext() != null) { // If the list contains another item after current, then
                    current = current.getNext(); // current becomes next item in list.
                    // outcome AFTER-NOT LAST YET is completed and the while loop will repeat until newItem is either last or no longer comes after
                } else { // if current is only item on list, then
                    current.setNextItem(newItem); // newItem comes after current
                    newItem.setPreviousItem(current); // current comes before after
                    return true; // outcome AFTER-LAST completed ---> drop out of the method/loop
                } // ........................................................................................................
            } else if (comparison > 0) { // newItem comes BEFORE current
                if (current.getPrevious() != null) { // if current is not first in list, then
                    current.getPrevious().setNextItem(newItem); // newItem comes after item that comes before new item (1. previous 2. newItem)
                    newItem.setPreviousItem(current.getPrevious()); // previous comes before newItem
                    newItem.setNextItem(current); // current comes after newItem (1.previous 2. newItem 3. current)
                    current.setPreviousItem(newItem); // newItem comes before current


                    current.getPrevious().setNextItem(newItem);
                    newItem.setPreviousItem(current.getPrevious());
                    newItem.setNextItem(current);
                    current.setPreviousItem(newItem);
                    current = newItem.getPrevious();
                    int comparison2 = (current.CompareTo(newItem));
                    if (comparison2 < 0) {
                        return true;
                    }
                    return true;
                    // outcome BEFORE-NOT FIRST YET is completed and the while loop will repeat until newItem is either first or no longer comes before
                } else { // if current is first in list
                    newItem.setNextItem(this.root); // newItem comes before root (=current)
                    this.root.setPreviousItem(newItem); // root (=current) comes after newItem
                    this.root = newItem; // newItem is now root
                    return true;  // outcome BEFORE-FIRST completed ---> drop out of the method/loop
                }// ........................................................................................................
            } else { // curent == newItem. As a duplicate, newItem is not added to the list
                System.out.println(newItem.getValue() + " is already part of the List.class Duplicate not added.");
                return false; // drop out of the method/loop without adding newItem to the list
            }   }
        return false; // drop out of the loop without adding newItem to the list (how you would ever get here I don't know
    }

    public static void main(String[] args)
    {
        LinkedList_1 list1 = new LinkedList_1(null);
        list1.printList(list1.getRoot());

        String stringData = "Harrier Ken Klaasje Titus Evrart Manaaba Cindy Eyckhead";
        String[] data = stringData.split(" ");
        for (int i = 0; i < (data.length); i++) {
            list1.addItem(new Node(data[i]));
        }
        list1.printList(list1.getRoot());
    }
}