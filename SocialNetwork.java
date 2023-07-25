import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SocialNetwork
{
    private HashMap<Integer, TreeSet<Integer>> network;

    public SocialNetwork()
    {
        network = new HashMap<>();
    }

    //Part 1 Scanning and Adding into dataset/graph
    public void scanFile(String fileName) throws FileNotFoundException
    {
        int i,n,c,a,b;
        Scanner sc = new Scanner(new File(fileName));

        n = sc.nextInt();
        c = sc.nextInt();

        for(i=0;i<c;i++)
        {
            a = sc.nextInt();
            b = sc.nextInt();
            createEdge(a,b);
        }

        sc.close();
    }

    //Helper Function to create an edge for 2 nodes
    private void createEdge(int personA, int personB)
    {
        network.computeIfAbsent(personA, k -> new TreeSet<>()).add(personB);
        network.computeIfAbsent(personB, k -> new TreeSet<>()).add(personA);
    }

    //Part 2 Display User Friends
    public void displayFriends(int ID)
    {
        if(!network.containsKey(ID))
        {
            System.out.println("");
            System.out.println("Person "+ID+" does not exist");
            return;
        }

        //Sorting and Displaying
        Set<Integer> friends = network.get(ID);
        //TreeSet<Integer> sortedSet = new TreeSet<>();
        //sortedSet.addAll(friends);
        System.out.println("");
        System.out.println("Person "+ID+" has "+friends.size()+" friends");
        System.out.println("List of friends: "+ friends);
    }

    public void checkConnection(int personA, int personB)
    {
        if(!network.containsKey(personA) || !network.containsKey(personB))
        {
            System.out.println("Person A or B is not a real person");
        }

        if(personA == personB)
        {
            System.out.println("Person A and B are the same people");
            System.out.println("");
        }

        //Helper Function goes in here
    }

    /* 
    //Helper function to check the path
    private ArrayList<Integer> checkPath(int personA, int personB, ArrayList<Integer> path, ArrayList<Integer> visit)
    {
        int i,j,control;

        for(i=0;i<network.get(personA).size();i++)
        {

        }
    }
    */

    public static void main(String args[]) throws FileNotFoundException
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.print("Input File Path: ");
        String path = sc.nextLine();
        SocialNetwork network = new SocialNetwork();
        network.scanFile(path);
        System.out.println("Graph is Loaded!");

        do
        {
            System.out.println("MAIN MENU");
            System.out.println("[1] Get friend list");
            System.out.println("[2] Get connection");
            System.out.println("[3] Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if(choice == 1)
            {
                System.out.print("Enter ID of person: ");
                int id = sc.nextInt();
                network.displayFriends(id);
            }

            else if(choice == 2)
            {
                System.out.print("Enter ID of first person: ");
                int personA = sc.nextInt();
                System.out.print("Enter ID of second person: ");
                int personB = sc.nextInt();
                network.checkConnection(personA, personB);
            }

            else if(choice == 3)
            {
                System.out.println("Terminating Program");
            }

            else
            {
                System.out.println("Invalid Input, please try again");
            }
        }while(choice != 3);

        sc.close();
    }
}
