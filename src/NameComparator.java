import java.util.Comparator;
/**NameComparator is a Comparator that compares Users name.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class NameComparator implements Comparator<User>{
    /**
     * Indicates which name is first lexicographically.
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return Indicates if user 2 is greater, lesser, or equal lexicographically than user 1
     */
    public int compare(User o1, User o2) {
        return (o1.getUserName().compareTo(o2.getUserName()));
    }
}
