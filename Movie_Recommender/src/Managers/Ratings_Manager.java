package Managers;

import Framework.FileReader;
import Framework.ObjectScore;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Ratings_Manager {

    private ArrayList<Movie> ListOfMovies;
    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;

    public Ratings_Manager(FileReader reader, ArrayList<RatingsWatcher<Movie>> ListOfUsers, ArrayList<Movie> ListOfMovies, String PathToRatings) {
        reader.ReadRatings(ListOfUsers, ListOfMovies, PathToRatings);

        this.ListOfMovies = ListOfMovies;
        this.ListOfUsers = ListOfUsers;
    }

    public ArrayList<Movie> GetListOfMovies() {
        return ListOfMovies;
    }

    public ArrayList<RatingsWatcher<Movie>> GetListOfUsers() {
        return ListOfUsers;
    }

    public Movie GetRecommendation(RatingsWatcher<Movie> CurrentUser, Neighbor_Manager neighbor_manager, int NumberOfNeighbors) {
        ObjectScore<Movie> RecommendationScore;
        ArrayList<ObjectScore<Movie>> ListOfRecommendableMovies = new ArrayList<>();
        HashMap<Movie, ObjectScore<Movie>> MapOfRecommendableMovies = new HashMap<>();
        HashSet<Movie> MoviesInCommon = new HashSet<>();

        double SumOfNeighborContribution;
        double SumOfNeighborSimilarities;

        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighbors = neighbor_manager.GetNewNeighbors(CurrentUser, NumberOfNeighbors);
        for (ObjectScore<RatingsWatcher<Movie>> n : ListOfNeighbors)
            MoviesInCommon.addAll(n.GetObject().GetRatedProducts());

        for (Movie m : MoviesInCommon) {
            if (!CurrentUser.GetRatedProducts().contains(m)) {
                SumOfNeighborContribution = 0;
                SumOfNeighborSimilarities = 0;
                for (ObjectScore<RatingsWatcher<Movie>> o_n : ListOfNeighbors) {
                    if (o_n.GetObject().GetRatedProducts().contains(m)) {
                        SumOfNeighborContribution = +o_n.GetScore() * o_n.GetObject().GetProductRating(m) - o_n.GetObject().GetUsersAverageScore();
                        SumOfNeighborSimilarities = +o_n.GetScore();
                    }
                }
                RecommendationScore = new ObjectScore<>(m, SumOfNeighborContribution / SumOfNeighborSimilarities + CurrentUser.GetUsersAverageScore());
                ListOfRecommendableMovies.add(RecommendationScore);
                MapOfRecommendableMovies.put(m, RecommendationScore);
            }
        }
        ListOfRecommendableMovies.sort(Comparator.comparing(ObjectScore::GetScore));
        return ListOfRecommendableMovies.get(0).GetObject();
    }


}
