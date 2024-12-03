import java.util.Comparator;
/** FollowersComparator is a Comparator of User that compares two users follower count.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class FollowersComparator implements Comparator<User> {
    /**
     * Compares the follower count of two users.
     * @param u1 the first object to be compared.
     * @param u2 the second object to be compared.
     * @return Indicates if user 2 has more or less followers compared to user 1. If equal, compares their name.
     */
    public int compare(User u1, User u2) {
        int res =  Integer.compare(u2.getFollowerCount(), u1.getFollowerCount());
        if(res == 0){
            return new NameComparator().compare(u1, u2);
        }
        return res;
    }
}
