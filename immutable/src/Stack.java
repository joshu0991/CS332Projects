/**
  * Bloch's Stack example pages 56, 2nd edition
  * Used in SWE/CS 332 to discuss AF/RI
  */

import java.util.*;

public class Stack {

   private Object[] elements;
   private int size = 0;
   // for the poped elements
   private Object lastPoped = null; 
   
   public Stack() {
     this.elements = new Object[0];
   }

   // For creating a new stack on pop
   private Stack(Object[] e) {
	   this.elements = e;
	   size = e.length;
	}
   
   private Stack(Object[] current, Object toAdd) {
	   this.elements = current;
	   this.size = current.length;
	   elements[(size - 1)] = toAdd;
   }

   // e object to add to stack
   public Stack push (Object e) {
     Object[] elements = ensureCapacity();
     return new Stack(elements, e);
   }

   // Returns a new stack with one less elements how to return an instance of top?
   public Stack pop () {
     if (size == 0) throw new IllegalStateException("Stack.pop");
     Object poped = elements[size - 1];
     Object[] newArray = new Object[(size - 1)];
     System.arraycopy(elements, 0, newArray, 0, (size - 1));
     Stack ns = new Stack(newArray);
     ns.lastPoped = poped;
     return ns;
   }
   
   public Object getPoped() {
	   return lastPoped;
   }

   public final Object top () {
     if (size == 0) throw new IllegalStateException("Stack.top");
     final Object r = elements[(size - 1)];
     return r;
   }

   // Returns a new array of a proper size
   private Object[] ensureCapacity() {
      if (elements.length == size) {
          Object newElements[];
          Object oldElements[] = elements;
    	  // We'll only make it one larger since we will have to create a new object next time anyway
          newElements = new Object[size + 1];
          if (oldElements.length > 0) {
              System.arraycopy(oldElements, 0, newElements, 0, (size + 1));
         }
         return newElements;
      }
      return new Object[size];
   }

}