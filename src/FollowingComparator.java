import java.util.Comparator;
/** FollowingComparator is a Comparator that compares Users following count.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class FollowingComparator implements Comparator<User> {
    /**
     * Compares the following count of two users.
     * @param u1 the first object to be compared.
     * @param u2 the second object to be compared.
     * @return Indicates whether user 2 has more or less following than user 1. If equal, compares their name.
     */
    public int compare(User u1, User u2){
        int res =  Integer.compare(u2.getFollowingCount(), u1.getFollowingCount());
        if(res == 0){
            return new NameComparator().compare(u1, u2);
        }
        return res;
    }
}
