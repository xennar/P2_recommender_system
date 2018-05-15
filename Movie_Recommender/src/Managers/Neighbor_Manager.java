package Managers;

import Framework.ObjectScore;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

//This Map controls the neighbors of each user.
public class Neighbor_Manager {
    private HashMap<RatingsWatcher<Movie>, ArrayList<ObjectScore<RatingsWatcher<Movie>>>> Neighbor_Lists;
    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private HashMap<Integer, RatingsWatcher<Movie>> IdToUserMap;

    //The constructor prepares the Map and list for use by initializing them.
    public Neighbor_Manager(ArrayList<RatingsWatcher<Movie>> ListOfUsers) {
        Neighbor_Lists = new HashMap<>();
        this.ListOfUsers = ListOfUsers;
        IdToUserMap = new HashMap<>();
        for(RatingsWatcher<Movie> u : this.ListOfUsers)
            IdToUserMap.put(u.GetID(), u);
    }



    //This method finds the neighbors of an user.
    public ArrayList<ObjectScore<RatingsWatcher<Movie>>> GetNewNeighbors(RatingsWatcher<Movie> Current_User, int NumberOfNeighbors) {

        //The first thing done is making all the variables needed to hold all information.
        ArrayList<Movie> Movies_in_common = new ArrayList<>();
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighborsAndScores = new ArrayList<>();
        ObjectScore<RatingsWatcher<Movie>> Neighborsimilarity;
        double UserDifference;
        double NeighborDifference;
        double UserAverage = Current_User.GetUsersAverageScore();
        double NeighborAverage;
        double SumOfScoreDifference;
        double SumOfUserDifference;
        double SumOfNeighborDifference;

        //If the user already has neighbors when read from the csv file, then those replace the entire list of users.
        if(Current_User.GetNeighborIDs().size() != 0){
            ListOfUsers = new ArrayList<>();
            for(Integer id : Current_User.GetNeighborIDs()){
                ListOfUsers.add(GetUserFromID(id));
            }
        }

        //The method goes through each RatingsWatcher that is not the current user anc checks for similarity.
        for (RatingsWatcher<Movie> n : ListOfUsers) {
            if (!n.equals(Current_User)) {

                //The following variables are cleared for use.
                NeighborAverage = n.GetUsersAverageScore();
                SumOfScoreDifference = 0;
                SumOfUserDifference = 0;
                SumOfNeighborDifference = 0;
                Movies_in_common.clear();

                //The list of movies that the user and potential neighbor has in common are found.
                for (Movie m : n.GetRatedProducts()) {
                    if (Current_User.GetRatedProducts().contains(m))
                        Movies_in_common.add(m);
                }

                //Then, if the two have movies in common, the information used in each field of the Pearson correlation
                // function are calculated and added together as neccesary. Details can be found in the implementation
                // described in the report.
                if (Movies_in_common.size() != 0) {
                    for (Movie m : Movies_in_common) {
                        if (n.GetRatedProducts().contains(m)) {
                            UserDifference = Current_User.GetProductRating(m) - UserAverage;
                            NeighborDifference = n.GetProductRating(m) - NeighborAverage;
                            SumOfScoreDifference += UserDifference * NeighborDifference;
                            SumOfUserDifference += UserDifference * UserDifference;
                            SumOfNeighborDifference += NeighborDifference * NeighborDifference;
                        }
                    }
                    //The new Object is initialized and added to the list.
                    Neighborsimilarity = new ObjectScore<>(n, SumOfScoreDifference / (Math.sqrt(SumOfUserDifference) * Math.sqrt(SumOfNeighborDifference)));
                    ListOfNeighborsAndScores.add(Neighborsimilarity);
                }
            }
        }
        //The list is sorted by score so that the least similar neighbors are first.
        ListOfNeighborsAndScores.sort(Comparator.comparing(ObjectScore::GetScore));
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighbors = new ArrayList<>();

        //The method then adds the most similar neighbors, found in the end of the list, to the new list, with the number specified by the wished number of neighbors.
        for (int counter = ListOfNeighborsAndScores.size() - 1; counter > ListOfNeighborsAndScores.size() - (NumberOfNeighbors + 1); counter--)
            ListOfNeighbors.add(ListOfNeighborsAndScores.get(counter));
        Neighbor_Lists.put(Current_User, ListOfNeighbors);

        //The new neighbor IDs are added to the users List.
        Current_User.GetNeighborIDs().clear();
        for(ObjectScore<RatingsWatcher<Movie>> os : ListOfNeighbors)
            Current_User.GetNeighborIDs().add(os.GetObject().GetID());

        return ListOfNeighbors;
    }

    //Getter which returns the list of neighbors.
    public ArrayList<RatingsWatcher<Movie>> GetNeighborList(RatingsWatcher<Movie> Current_User) {
        ArrayList<RatingsWatcher<Movie>> ListOfNeighbors = new ArrayList<>();
        for (ObjectScore<RatingsWatcher<Movie>> n : Neighbor_Lists.get(Current_User)) {
            ListOfNeighbors.add(n.GetObject());
        }
        return ListOfNeighbors;
    }

    RatingsWatcher<Movie> GetUserFromID(int ID){
        return IdToUserMap.get(ID);
    }
}


