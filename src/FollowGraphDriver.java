import java.io.*;
import java.util.List;
import java.util.Scanner;
/** FollowerGraphDriver represents a CLI for FollowerGraph.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class FollowGraphDriver {
    private static Scanner sc;
    private static FollowerGraph graph;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        File file = new File("follow_graph.obj");;
        //Loads saved data if it exists.
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            graph = (FollowerGraph) ois.readObject();
            System.out.println("follow_graph.obj found.");
            User.setUserCount(graph.size());
        }
        catch (Exception e) {
            System.out.println("follow_graph.obj is not found. New FollowGraph object will be created.");
            graph = new FollowerGraph();
        }

        String commands = """
             ************ Menu ************
             (U) Add User
             (C) Add Connection
             (AU) Load all Users
             (AC) Load all Connections
             (P) Print all Users
             (L) Print all Loops
             (RU) Remove User
             (RC) Remove Connection
             (SP) Find Shortest Path
             (AP) Find All Paths
             (Q) Quit
             """;
        String command;
        CLI:
        while(true){
            System.out.println(commands);
            System.out.println("Enter a selection: ");
            command = sc.nextLine();
            switch(command){
                case "U": addUser(); break;
                case "C": addConnection(); break;
                case "AU": loadUsers(); break;
                case "AC": loadConnections(); break;
                case "P": printUsers(); break;
                case "L": printLoops(); break;
                case "RU": removeUser(); break;
                case "RC": removeConnection(); break;
                case "SP": findShortestPath(); break;
                case "AP": findAllPaths(); break;
                case "Q": break CLI;
            }
        }

        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(graph);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function to add a user to the graph.
     */
    private static void addUser(){
        System.out.print("Please enter the name of the user: ");
        String userName = sc.nextLine().trim();
        graph.addUser(userName);
    }

    /**
     * Helper function to add a connection to the graph.
     */
    private static void addConnection(){
        String source, dest;

        System.out.println("Please enter the source of the connection to add: ");
        source = sc.nextLine().trim();
        System.out.println("Please enter the dest of the connection to add: ");
        dest = sc.nextLine().trim();
        graph.addConnection(source, dest);
    }

    /**
     * Helper function to load users to the graph.
     */
    private static void loadUsers(){
        System.out.print("Enter the file name: ");
        String fileName = sc.nextLine().trim();
        graph.loadAllUsers(fileName);
    }

    /**
     * Helper function to load connections to the graph.
     */
    private static void loadConnections(){
        System.out.print("Enter the file name: ");
        String fileName = sc.nextLine().trim();
        graph.loadAllConnections(fileName);
    }

    /**
     * Helper function to print users based on the given criteria.
     */
    private static void printUsers(){
        String commands = """
                (SA) Sort Users by Name
                (SB) Sort Users by Number of Followers
                (SC) Sort Users by Number of Following
                (Q) Quit
                """;
        String command;
        while(true){
            command = sc.nextLine();
            switch(command){
                case "SA": graph.printAllUsers(new NameComparator()); break;
                case "SB": graph.printAllUsers(new FollowersComparator()); break;
                case "SC": graph.printAllUsers(new FollowingComparator()); break;
                case "Q": return;
            }
        }
    }

    /**
     * Helper function to print the loops in the graph.
     */
    private static void printLoops(){
        List<String> loops = graph.findAllLoops();
        if(loops.isEmpty()){
            System.out.println("There are no loops.");
        }
        else if(loops.size() == 1){
            System.out.println("There is 1 loop:");
        }
        else
            System.out.println("There are a total of " + loops.size() + " loops:");
        for(String loop : loops){
            System.out.println(loop);
        }
    }

    /**
     * Helper function to remove a user from the graph.
     */
    private static void removeUser(){
        System.out.println("Please enter the user to remove: ");
        String userName = sc.nextLine().trim();
        graph.removeUser(userName);
    }

    /**
     * Helper function to remove a connection from the graph.
     */
    private static void removeConnection() {
        String source, dest;
        while (true) {
            System.out.println("Please enter the source of the connection to remove: ");
            source = sc.nextLine().trim();
            if(source == "")
                System.out.println("You can not leave this field empty.");
            if(graph.getUserIndex(source) == -1)
                System.out.println("There is no user with this name," +
                  "Please choose a valid user!");
            else
                break;
        }
        while (true) {
            System.out.println("Please enter the dest of the connection to remove: ");
            dest = sc.nextLine().trim();
            if(dest == "")
                System.out.println("You can not leave this field empty.");
            if(graph.getUserIndex(dest) == -1)
                System.out.println("There is no user with this name, "
                  + "Please choose a valid user!");
            else
                break;
        }
        graph.removeConnection(source, dest);

    }

    /**
     * Helper function to find the shortest path between two users.
     */
    private static void findShortestPath(){
        System.out.println("Please enter the desired source: ");
        String source = sc.nextLine().trim();
        System.out.println("Please enter the desired destination: ");
        String dest = sc.nextLine().trim();
        String path = graph.shortestPath(source, dest);
        System.out.println("The shortest path is: "+ path);
        String[] nodes = path.split(" -> ");
        System.out.println("The number of users in this path is: " + nodes.length);
    }

    /**
     * Helper function to find all paths between two users.
     */
    private static void findAllPaths(){
        System.out.println("Please enter the desired source: ");
        String source = sc.nextLine().trim();
        System.out.println("Please enter the desired destination: ");
        String dest = sc.nextLine().trim();
        List<String> paths = graph.allPaths(source, dest);
        System.out.println("There are a total of " + paths.size() + " paths:");
        for(String path : paths){
            System.out.println(path);
        }
    }
}

