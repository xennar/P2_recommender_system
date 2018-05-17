package Managers;

import Framework.ObjectScore;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

//This class controls the neighbors of each user.
public class Neighbor_Manager {

    //This list contains a list of all current users current neighbors' similarity scores in the form of ObjectScores.
    private HashMap<RatingsWatcher<Movie>, ArrayList<ObjectScore<RatingsWatcher<Movie>>>> Neighbor_Lists;
    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;

    //This map can return the user by being given the user's ID as key.
    private HashMap<Integer, RatingsWatcher<Movie>> IdToUserMap;

    //The constructor prepares the Map and list for use by initializing them.
    public Neighbor_Manager(ArrayList<RatingsWatcher<Movie>> ListOfUsers) {
        Neighbor_Lists = new HashMap<>();
        this.ListOfUsers = ListOfUsers;
        IdToUserMap = new HashMap<>();
        for (RatingsWatcher<Movie> u : this.ListOfUsers)
            IdToUserMap.put(u.GetID(), u);
    }

    //This method finds the neighbors of an user.
    public ArrayList<ObjectScore<RatingsWatcher<Movie>>> GetNewNeighbors(RatingsWatcher<Movie> Current_User, int NumberOfNeighbors) {
        //The method "GetSimilarityScores" is called to get the similarity scores in the from of ObjectScore objects containing the user and the score associated with that user.
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighborsAndScores = GetSimilarityScores(Current_User, ListOfUsers, NumberOfNeighbors);

        //The list is sorted by score so that the least similar neighbors are first.
        ListOfNeighborsAndScores.sort(Comparator.comparing(ObjectScore::GetScore));
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighbors = new ArrayList<>();

        //The method then adds the most similar neighbors, found in the end of the list, to the new list, with the number specified by the wished number of neighbors.
        for (int counter = ListOfNeighborsAndScores.size() - 1; counter > ListOfNeighborsAndScores.size() - (NumberOfNeighbors + 1); counter--)
            ListOfNeighbors.add(ListOfNeighborsAndScores.get(counter));
        Neighbor_Lists.put(Current_User, ListOfNeighbors);

        //The new neighbor IDs are added to the user's List.
        Current_User.GetNeighborIDs().clear();
        for (ObjectScore<RatingsWatcher<Movie>> os : ListOfNeighbors)
            Current_User.GetNeighborIDs().add(os.GetObject().GetID());

        return ListOfNeighbors;
    }

    //This method finds the similarity scores of the user's current neighbors and returns a List of ObjectScores containing the similarity score and neighbor.
    public ArrayList<ObjectScore<RatingsWatcher<Movie>>> GetExistingNeighborsSimilarityScores(RatingsWatcher<Movie> Current_User, int NumberOfNeighbors) {

        //The NeighborIDs are translated to their users, and added to a list.
        ArrayList<RatingsWatcher<Movie>> ListOfExistingNeighbors = new ArrayList<>();
        for (Integer id : Current_User.GetNeighborIDs()) {
            ListOfExistingNeighbors.add(GetUserFromID(id));
        }

        //The neighbors receive their similarity scores.
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighborsAndScores = GetSimilarityScores(Current_User, ListOfExistingNeighbors, NumberOfNeighbors);

        //The list is sorted by score so that the least similar neighbors are first.
        ListOfNeighborsAndScores.sort(Comparator.comparing(ObjectScore::GetScore));
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighbors = new ArrayList<>();

        //The method then adds the most similar neighbors, found in the end of the list, to the new list, with the number specified by the wished number of neighbors.
        for (int counter = ListOfNeighborsAndScores.size() - 1; counter > ListOfNeighborsAndScores.size() - (NumberOfNeighbors + 1); counter--)
            ListOfNeighbors.add(ListOfNeighborsAndScores.get(counter));
        Neighbor_Lists.put(Current_User, ListOfNeighbors);

        //The new neighbor IDs are added to the users List.
        Current_User.GetNeighborIDs().clear();
        for (ObjectScore<RatingsWatcher<Movie>> os : ListOfNeighbors)
            Current_User.GetNeighborIDs().add(os.GetObject().GetID());

        return ListOfNeighbors;
    }

    //This method finds all the similarity scores for all users that get passed through ListOfUsers, and returns a list of ObjectScore with the score and the User.
    private ArrayList<ObjectScore<RatingsWatcher<Movie>>> GetSimilarityScores(RatingsWatcher<Movie> Current_User, ArrayList<RatingsWatcher<Movie>> ListOfUsers, int NumberOfNeighbors) {

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
        return ListOfNeighborsAndScores;
    }

    //Getter which returns the list of neighbors.
    public ArrayList<RatingsWatcher<Movie>> GetNeighborList(RatingsWatcher<Movie> Current_User) {
        ArrayList<RatingsWatcher<Movie>> ListOfNeighbors = new ArrayList<>();
        for (ObjectScore<RatingsWatcher<Movie>> n : Neighbor_Lists.get(Current_User)) {
            ListOfNeighbors.add(n.GetObject());
        }
        return ListOfNeighbors;
    }


    public RatingsWatcher<Movie> GetUserFromID(int ID) {
        return IdToUserMap.get(ID);
    }

}


