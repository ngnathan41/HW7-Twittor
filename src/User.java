import java.io.Serializable;
/** User represents a user of Twittor and contains details about the user.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class User implements Serializable {
    private String userName;
    private int indexPos;
    private static int userCount = 0;
    private int followerCount;
    private int followingCount;

    /**
     * Instantiates a new User object with the given name.
     * @param userName Name to assign to user.
     */
    public User(String userName) {
        this.userName = userName;
        this.indexPos = userCount;
        userCount++;
        followerCount = 0;
        followingCount = 0;
    }

    /**
     * Returns the total number of users.
     * @return number of users.
     */
    public static int getUserCount() {
        return userCount;
    }

    /**
     * Sets the number of users.
     * @param userCount number of users to set.
     */
    public static void setUserCount(int userCount) {
        User.userCount = userCount;
    }

    /**
     * Returns the index position of a user.
     * @return index position of user.
     */
    public int getIndexPos() {
        return indexPos;
    }

    /**
     * Sets the index position of a user.
     * @param indexPos index position to set.
     */
    public void setIndexPos(int indexPos) {
        this.indexPos = indexPos;
    }

    /**
     * Returns the username of a user.
     * @return username of user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of a user.
     * @param userName name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the String representation of a user
     * @return String of user details.
     */
    public String toString(){
        return String.format("Name: %s, Index: %s, Followers: %s, Following: %s"
          , userName, indexPos, followerCount, followingCount);
    }

    /**
     * Returns the follower count of a user.
     * @return follower count of user.
     */
    public int getFollowerCount() {
        return followerCount;
    }

    /**
     * Sets the follower count of a user.
     * @param followerCount follower count to set.
     */
    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    /**
     * Returns the following count of a user.
     * @return following count of user.
     */
    public int getFollowingCount() {
        return followingCount;
    }

    /**
     * Sets the follower count of a user.
     * @param followingCount following count to set.
     */
    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }
}
