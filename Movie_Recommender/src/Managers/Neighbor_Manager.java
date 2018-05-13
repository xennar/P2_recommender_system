package Managers;

import Framework.ObjectScore;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


public class Neighbor_Manager {
    private HashMap<RatingsWatcher<Movie>, ArrayList<ObjectScore<RatingsWatcher<Movie>>>> Neighbor_Lists;
    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;

    public Neighbor_Manager(ArrayList<RatingsWatcher<Movie>> ListOfUsers) {
        Neighbor_Lists = new HashMap<>();
        this.ListOfUsers = ListOfUsers;
    }

    public ArrayList<ObjectScore<RatingsWatcher<Movie>>> GetNewNeighbors(RatingsWatcher<Movie> Current_User, int NumberOfNeighbors) {
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
        for (RatingsWatcher<Movie> n : ListOfUsers) {
            NeighborAverage = n.GetUsersAverageScore();
            SumOfScoreDifference = 0;
            SumOfUserDifference = 0;
            SumOfNeighborDifference = 0;
            for (Movie m : n.GetRatedProducts()) {
                if (Current_User.GetRatedProducts().contains(m))
                    Movies_in_common.add(m);
            }
            for (Movie m : Movies_in_common) {
                UserDifference = Current_User.GetProductRating(m) - UserAverage;
                NeighborDifference = n.GetProductRating(m) - NeighborAverage;
                SumOfScoreDifference = +UserDifference * NeighborDifference;
                SumOfUserDifference = +UserDifference * UserDifference;
                SumOfNeighborDifference = +NeighborDifference * NeighborDifference;
            }
            Neighborsimilarity = new ObjectScore<>(n, SumOfScoreDifference / (Math.sqrt(SumOfUserDifference) * Math.sqrt(SumOfNeighborDifference)));
            ListOfNeighborsAndScores.add(Neighborsimilarity);
        }
        ListOfNeighborsAndScores.sort(Comparator.comparing(ObjectScore::GetScore));
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighbors = new ArrayList<>();
        for (int counter = 0; counter < NumberOfNeighbors; counter++)
            ListOfNeighbors.add(ListOfNeighborsAndScores.get(counter));
        Neighbor_Lists.put(Current_User, ListOfNeighbors);
        return ListOfNeighbors;
    }

    public ArrayList<RatingsWatcher<Movie>> GetNeighborList(RatingsWatcher<Movie> Current_User) {
        ArrayList<RatingsWatcher<Movie>> ListOfNeighbors = new ArrayList<>();
        for (ObjectScore<RatingsWatcher<Movie>> n : Neighbor_Lists.get(Current_User)) {
            ListOfNeighbors.add(n.GetObject());
        }
        return ListOfNeighbors;
    }
}


