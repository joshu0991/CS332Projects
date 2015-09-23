/**
  * @author Joshua Lilly G00561467 
  * Bloch's Stack example pages 56, 2nd edition
  * Used in SWE/CS 332 to discuss AF/RI
  */
public class Stack {

   private Object[] elements;
   private int size = 0;

   /**
    * Default construct a new immutable stack.
    */
   public Stack() {
       this.elements = new Object[0];
   }

   /**
    * Create a new stack with the given object[]
    * @param e the object array
    */
    private Stack(Object[] e) {
        this.elements = e;
        size = e.length;
    }
   /**
    * Private constructor for adding an object to the stack.
    * @param current
    * @param toAdd
    */
    private Stack(Object[] current, Object toAdd) {
        this.elements = current;
        this.size = current.length;
        elements[(size - 1)] = toAdd;
   }

    /**
     * Make a brand new stack that contains the given element.
     * @param e the object to add to our new stack
     * @return a new stack containing the value.
     */
    public Stack push (Object e) {
        // We will use ensure capacity just to resize our array
        Object[] elements = ensureCapacity();
        return new Stack(elements, e);
   }
    
    /**
     * Pop an element from the stack the caller needs to get a reference to the top of the stack
     * first by calling top before calling pop
     * @return A new stack minus the top of the stack.
     */
    public Stack pop () {
        if (size == 0) throw new IllegalStateException("Stack.pop");
        Object[] newArray = new Object[(size - 1)];
        System.arraycopy(elements, 0, newArray, 0, (size - 1));
        return new Stack(newArray);
   }

   /**
    * Observer function used to get a reference to the top of the stack.
    * @return the element on top of the stack. If it exist else throw an exception.
    */
    public Object top () {
        if (size == 0) throw new IllegalStateException("Stack.top");
        return elements[(size - 1)];
   }

    /**
     * Increases out array size by one as to ensure we have a place to put the new object.
     * @return An array that is one larger than the previous. Or a new array of current size if it
     * for some reason is larger. 
     */
    private Object[] ensureCapacity() {
        if (elements.length == size) {
            Object newElements[];
            Object oldElements[] = elements;
            // We'll only make it one larger since we will have to create a new object next time anyway
            newElements = new Object[size + 1];
            if (oldElements.length > 0) {
                System.arraycopy(oldElements, 0, newElements, 0, (size));
            }
            return newElements;
        }
        return new Object[size];
    }
}