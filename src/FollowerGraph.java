import java.io.*;
import java.util.*;
/**
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class FollowerGraph implements Serializable {
    private ArrayList<User> users;
    public static final int MAX_USERS = 100;
    private boolean[][] connections;

    public FollowerGraph() {
        users = new ArrayList<>();
        connections = new boolean[MAX_USERS][MAX_USERS];
    }
    public FollowerGraph(ArrayList<User> users) {
        this.users = users;
        connections = new boolean[MAX_USERS][MAX_USERS];
    }

    public void addUser(String userName){
        if(users.size() == MAX_USERS){
            return;
        }
        if(getUserIndex(userName) != -1){
            return;
        }
        users.add(new User(userName));

    }

    public void removeUser(String userName){
        int index = getUserIndex(userName);
        if(index == -1){
            return;
        }

        for(int i = 0; i<users.size(); i++){
            if(connections[index][i] == true){
                users.get(i).setFollowerCount(users.get(i).getFollowerCount() - 1);
            }
        }
        for(int i = 0; i<users.size(); i++){
            if(connections[i][index] == true)
                users.get(i).setFollowingCount(users.get(i).getFollowingCount() - 1);
        }
        for(int i = index; i<connections.length - 1; i++){
            System.arraycopy(connections[i+1], 0, connections[i], 0 , connections[i].length);
        }
        Arrays.fill(connections[connections.length-1], false);

        for(int i = 0; i<connections.length; i++){
            for(int j = index; j<connections[i].length-1; j++){
                connections[i][j] = connections[i][j+1];
            }
            connections[i][connections[i].length-1] = false;
        }
        users.remove(index);
        for(int i = index; i<users.size(); i++){
            users.get(i).setIndexPos(i);
        }
        User.setUserCount(User.getUserCount()-1);
    }
    public void addConnection(String userFrom, String userTo){
        int i = getUserIndex(userFrom);
        int j = getUserIndex(userTo);
        if(i != -1 && j!=-1){
            connections[i][j] = true;
            User from = users.get(i);
            User to = users.get(j);
            from.setFollowingCount(from.getFollowingCount()+1);
            to.setFollowerCount(to.getFollowerCount()+1);
        }
    }

    public void removeConnection(String userFrom, String userTo){
        int i = getUserIndex(userFrom);
        int j = getUserIndex(userTo);
        if(i != -1 && j!=-1){
            connections[i][j] = false;
            User from = users.get(i);
            User to = users.get(j);
            from.setFollowingCount(from.getFollowingCount()-1);
            to.setFollowerCount(to.getFollowerCount()-1);
        }
    }

    public String shortestPath(String userFrom, String userTo){
        int start = getUserIndex(userFrom);
        int end = getUserIndex(userTo);

        if(start == -1 || end == -1){
            return "Invalid users";
        }

        boolean[] visited = new boolean[users.size()];
        int[] travel = new int[users.size()];
        Arrays.fill(travel, -1);


        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();

            if(current == end)
                break;

            for(int i = 0; i < users.size(); i++){
                if(connections[current][i] == true && !visited[i]){
                    queue.add(i);
                    visited[i] = true;
                    travel[i] = current;
                }
            }
        }

        ArrayList<String> path = new ArrayList<>();
        for(int i = end; i != -1; i = travel[i]){
            path.add(users.get(i).getUserName());
        }

        Collections.reverse(path);

        if(getUserIndex(path.get(0)) != start)
            return "No Path";
        return String.join(" -> ", path);
    }

    public int getUserIndex(String user){
        for(User id: users)
            if(id.getUserName().equals(user))
                return id.getIndexPos();
        return -1;
    }

    public List<String> allPaths(String useFrom, String useTo){
        int start = getUserIndex(useFrom);
        int end = getUserIndex(useTo);
        if(start == -1 || end == -1){
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        allPathsDFS(start, end, stack, res);
        Collections.sort(res);
        return res;
    }

    private void allPathsDFS(int cur, int end, Stack<Integer> stack, List<String> res){
        stack.push(cur);

        if(cur == end){
            List<String> path = new ArrayList<>();
            for(int user:stack){
                path.add(users.get(user).getUserName());
            }
            res.add(String.join(" -> ", path));
        }
        else{
            for(int i = 0; i < users.size(); i++){
                if(connections[cur][i] && !stack.contains(i)){
                    allPathsDFS(i, end, stack, res);
                }
            }
        }
        stack.pop();
    }
    public void printAllUsers(Comparator<User> comp){
        ArrayList<User> usersCopy = new ArrayList<>(this.users);
        usersCopy.sort(comp);
        System.out.println("Users:");
        System.out.printf("%-31s %-21s %-21s \n", "User Name", "Number of Followers", "Number of Following");
        String format = "%-40s %-20s %-20s \n";
        for(User id: usersCopy){
            System.out.printf(format, id.getUserName(), id.getFollowerCount(), id.getFollowingCount());
        }

    }

    public void printAllFollowers(String userName){
        int index = getUserIndex(userName);
        if(index != -1){
            System.out.println("Followers:");
            for(int i = 0; i<users.size(); i++){
                if(connections[i][index])
                    System.out.println(users.get(i).getUserName());
            }
        }
    }

    public void printAllFollowings(String userName){
        int index = getUserIndex(userName);
        if(index != -1){
            System.out.println("Following:");
            for(int i = 0; i<users.size(); i++){
                if(connections[index][i])
                    System.out.println(users.get(i).getUserName());
            }
        }
    }

    public List<String> findAllLoops(){
        boolean[][] connectionCopy = new boolean[users.size()][users.size()];
        for(int i = 0; i< users.size(); i++){
            System.arraycopy(connections[i], 0, connectionCopy[i], 0, connectionCopy[i].length);
        }
        List<String> res = new ArrayList<>();
        for(int i = 0; i<users.size(); i++){
            for (int j = 0; j < users.size(); j++) {
                if(connectionCopy[i][j])
                {
                    Stack<Integer> stack = new Stack<>();
                    loopsDFS(j, i, stack, res, connectionCopy);
                    connectionCopy[i][j] = false;
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    private void loopsDFS(int cur, int end, Stack<Integer> stack, List<String> res, boolean[][] connection){
        stack.push(cur);

        if(cur == end){
            List<String> path = new ArrayList<>();
            for(int user:stack){
                path.add(users.get(user).getUserName());
            }
            path.add(0, users.get(end).getUserName());
            res.add(String.join(" -> ", path));
        }
        else{
            for(int i = 0; i < users.size(); i++){
                if(connection[cur][i] && !stack.contains(i)){
                    loopsDFS(i, end, stack, res, connection);
                }
            }
        }
        stack.pop();
    }

    public void loadAllUsers(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                addUser(line.trim());
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllConnections(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(", ");
                addConnection(split[0], split[1]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int size(){
        return users.size();
    }
}
