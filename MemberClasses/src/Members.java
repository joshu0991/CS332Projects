import java.util.List;

public class Members {

    // Members is a mutable record of organization membership
    // AF: ??
    // rep-inv: ??

    List<String> members;   // the representation

    /**
     * Adds a person to the group
     * @param person the given person to add to the group
     * Post conditions: person becomes a member
     * @return true on successful insertion false otherwise
     * @throws IllegalArgumentException when trying to add a null element
     */
    public boolean join (String person)
    {
        if (person != null)
            return members.add(person);
        throw new IllegalArgumentException("Nulls are not allowed so can not add");
    }
    
    /**
     * Remove the given person from the list.
     * @param person the person to remove
     * Post conditions: Member is removed from the group
     * @return true when the operation is successful false otherwise
     * @throws IllegalArgumentException if person is null
     */
    public boolean leave(String person) 
    { 
        if (person != null)
            return members.remove(person);
        throw new IllegalArgumentException("Nulls are not allowed so can not remove");
    }
    
    /**
     * Check through the members of the list to see if the given person is a member
     * @param person Person to search for 
     * Post conditions:
     * @return true if person is in list false otherwise
     * @throws IllegalArgumentException if object to search for is null
     */
    public boolean isMember(String person)
    {
        if (person != null)
            return members.contains(person);
        throw new IllegalArgumentException("Nulls are not allowed in the list so searching is futile");
    }
 }

