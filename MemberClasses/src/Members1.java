import java.util.ArrayList;
import java.util.List;

public class Members1 {

    // Members is a mutable record of organization membership
    // AF: member[i] is a member of the organization 
    // rep-inv: Members contains no null values members is never null. Duplcates are allowed

    List<String> members;   // the representation

    public Members1()
    {
        members = new ArrayList<String>();
    }
    
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
     * Remove all instances of the given person from the list.
     * @param person the person to completely remove
     * Post conditions: Member is removed from the group
     * @return true when the operation is successful false otherwise
     * @throws IllegalArgumentException if person is null
     */
    public boolean leave(String person) 
    { 
        if (person != null)
        {
            boolean success = false;
            while (members.contains(person)) {
                success = members.remove(person);
            }
            return success;
        }
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
    
    // This rep shows that duplicates are in the list and that ir is okay
    // the code still works just fine
    public boolean repOk1()
    {
        if (checkForDuplicates())
        {
            return true;
        }
        return false;
    }
    
    public boolean repOk2()
    {
        if (checkForNulls())
        {
            return true;
        }
        return false;
    }
    
    private boolean checkForNulls()
    {
        for (int i = 0; i < members.size(); i++)
        {
            if (members.get(i) == null)
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkForDuplicates()
    {
        for (int i = 0; i < members.size(); i++)
        {
            String check = members.get(i);
            for (int j = i + 1; j < members.size(); j++)
            {
                if (check.equalsIgnoreCase(members.get(j)))
                {
                    return true;
                }
            }
        }
        return false;
    }
 }

