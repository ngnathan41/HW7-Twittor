import java.util.Comparator;
/**
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class NameComparator implements Comparator<User>{
    public int compare(User o1, User o2) {
        return (o1.getUserName().compareTo(o2.getUserName()));
    }
}
