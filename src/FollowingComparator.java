import java.util.Comparator;
/**
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class FollowingComparator implements Comparator<User> {
    public int compare(User u1, User u2){
        int res =  Integer.compare(u2.getFollowingCount(), u1.getFollowingCount());
        if(res == 0){
            return new NameComparator().compare(u1, u2);
        }
        return res;
    }
}
