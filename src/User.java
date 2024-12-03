import java.io.Serializable;
/**
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

    public User(String userName) {
        this.userName = userName;
        this.indexPos = userCount;
        userCount++;
        followerCount = 0;
        followingCount = 0;
    }

    public static int getUserCount() {
        return userCount;
    }

    public static void setUserCount(int userCount) {
        User.userCount = userCount;
    }

    public int getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(int indexPos) {
        this.indexPos = indexPos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString(){
        return "User: " + userName + ", Index: " + indexPos;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }
}
