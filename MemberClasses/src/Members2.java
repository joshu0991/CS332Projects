import java.util.List;

public class Members2 {

    // Members is a mutable record of organization membership
    // AF: ??
    // rep-inv: ??

    List<String> members;   // the representation

    /**
     * Adds a person to the group if the person is not in the group yet.
     * @param person to try to add to the group
     * Post conditions: person becomes a member if they are not already
     * @return true on successful insertion false otherwise
     * @throws IllegalArgumentException when trying to add a null element or a person is already
     *         a member of the group. To avoid an exception being thrown here it is best to use 
     *         the isMember function to verify the person doesn't exist in the list before join
     *         is called.
     */
    public boolean join (String person)
    {
        if (person != null && !members.contains(person))
        {
            return members.add(person);
        }
        throw new IllegalArgumentException("Nulls are not allowed so can not add it");
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

