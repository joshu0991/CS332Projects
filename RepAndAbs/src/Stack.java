/**
  * Bloch's Stack example pages 56, 2nd edition
  * Used in SWE/CS 332 to discuss AF/RI
  */

import java.util.*;

public class Stack {

    private Object[] elements;
    private int size = 0;

    public Stack() {
      this.elements = new Object[0];
    }

    private Stack(Object[] e) {
        this.elements = e;
        size = e.length;
    }

    public void push (Object e) {
      ensureCapacity();
      elements[size++] = e;
    }

    public Object pop () {
      if (size == 0) throw new IllegalStateException("Stack.pop");
      Object result = elements[--size];
      elements[size] = null;
      return result;
    }

    public Object top () {
      if (size == 0) throw new IllegalStateException("Stack.top");
      return elements[(size-1)];
    }

    private void ensureCapacity() {
       if (elements.length == size) {
          Object oldElements[] = elements;
          elements = new Object[2*size + 1];
          System.arraycopy(oldElements, 0, elements, 0, size);
       }
    }

    public String toString() {
        String s = new String();
        for (int i = 0; i < size; i++) {
            s += elements[i].toString() + "\n";
        }
        return s;
    }

    private boolean checkNulls() {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                return false;
            }
        }
        // No nulls were found
        return true;
    }
    
    // Rep invariant is:
    // elements isn't null
    // size is a positive integer
    // no items in the list are null
    public boolean repOk() {
        if (elements != null && size >= 0 && checkNulls() ) {
            return true;
        }
        return false;
    }
}