package Managers;

import Framework.DatabaseReader;
import Framework.ObjectScore;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.*;

//This class controls the ratings and recommendations that the program uses and makes.
public class Ratings_Manager {

    private ArrayList<Movie> ListOfMovies;
    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;


    public Ratings_Manager(DatabaseReader reader, ArrayList<RatingsWatcher<Movie>> ListOfUsers, ArrayList<Movie> ListOfMovies, String PathToRatings) {
        reader.ReadRatings(ListOfUsers, ListOfMovies, PathToRatings);

        this.ListOfMovies = ListOfMovies;
        this.ListOfUsers = ListOfUsers;
    }

    //The following two methods are basic getters.
    public ArrayList<Movie> GetListOfMovies() {
        return ListOfMovies;
    }

    public ArrayList<RatingsWatcher<Movie>> GetListOfUsers() {
        return ListOfUsers;
    }

    //The following method is used to make a recommendation to the current user.
    public Movie GetRecommendation(RatingsWatcher<Movie> CurrentUser, Neighbor_Manager neighbor_manager, int NumberOfNeighbors) {



        //Lists and variables are initialized and prepared for use.
        ObjectScore<Movie> RecommendationScore;
        ArrayList<ObjectScore<Movie>> ListOfRecommendableMovies = new ArrayList<>();
        HashMap<Movie, ObjectScore<Movie>> MapOfRecommendableMovies = new HashMap<>();
        HashSet<Movie> MoviesInCommon = new HashSet<>();
        double SumOfNeighborContribution;
        double SumOfNeighborSimilarities;

        //If the user has not seen any movies, then the movie with the highest average rating is recommended.
        if(CurrentUser.GetRatedProducts().size() == 0){
            for(Movie m : ListOfMovies){
                if(m.GetAverage_Rating() > 1){
                RecommendationScore = new ObjectScore<Movie>(m, m.GetAverage_Rating());
                ListOfRecommendableMovies.add(RecommendationScore);}
            }
            ListOfRecommendableMovies.sort(new RecommendationComparator());
            return ListOfRecommendableMovies.get(ListOfRecommendableMovies.size()-1).GetObject();
        }

        //To ensure that the correct neighbors are picked, the method gets the newest set by calling the method for getting neighbors.
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> ListOfNeighbors = neighbor_manager.GetNewNeighbors(CurrentUser, NumberOfNeighbors);
        //A list of all movies that the neighbors have seen are made by adding their individual lists together.
        for (ObjectScore<RatingsWatcher<Movie>> n : ListOfNeighbors)
            MoviesInCommon.addAll(n.GetObject().GetRatedProducts());

        //Each movie in that list is then iterated over, to find out its predicted score.
        for (Movie m : MoviesInCommon) {
            //If the user has seen the movie, then it will not be recommended.
            if (!CurrentUser.GetRatedProducts().contains(m)) {
                SumOfNeighborContribution = 0;
                SumOfNeighborSimilarities = 0;
                //As described in the rapport, the values needed to calculate the predicted score are added together.
                for (ObjectScore<RatingsWatcher<Movie>> o_n : ListOfNeighbors) {
                    //If the neighbor has not seen the movie, then they are skipped
                    if (o_n.GetObject().GetRatedProducts().contains(m)) {
                        SumOfNeighborContribution += o_n.GetScore() * o_n.GetObject().GetProductRating(m) - o_n.GetObject().GetUsersAverageScore();
                        SumOfNeighborSimilarities += +o_n.GetScore();
                    }
                }
                //An Object with the score is initialized and added to the list and Map.
                RecommendationScore = new ObjectScore<>(m, SumOfNeighborContribution / SumOfNeighborSimilarities + CurrentUser.GetUsersAverageScore());
                ListOfRecommendableMovies.add(RecommendationScore);
                MapOfRecommendableMovies.put(m, RecommendationScore);
            }
        }
        //The list is sorted and the movie with the highest predicted score is returned.
        ListOfRecommendableMovies.sort(Comparator.comparing(ObjectScore::GetScore));

        //If, however, the user has said that they don't want a specific movie, then it will not be recommended.
        Movie RecommendedProduct = ListOfRecommendableMovies.get(0).GetObject();
        int counter = 1;
        if (CurrentUser.GetIgnoreIDs().size() != 0)
            if (CurrentUser.GetIgnoreIDs().size() == 1)
                throw new RuntimeException("Can't recommend any movies due to ignores");
        while (CurrentUser.GetIgnoreIDs().contains(RecommendedProduct.GetID())) {
            RecommendedProduct = ListOfRecommendableMovies.get(counter).GetObject();
            counter++;
        }
        if (CurrentUser.GetIgnoreIDs().contains(RecommendedProduct.GetID()))
            throw new RuntimeException("Can't recommend any movies due to ignores");

        return RecommendedProduct;
    }

    public void AddIgnoreToUser(RatingsWatcher<Movie> Current_User, Movie ToBeIgnored) {
        Current_User.GetIgnoreIDs().add(ToBeIgnored.GetID());
        Session_Manager.addNewSessionUserChanges(Current_User);
    }

    public void RemoveIgnoreFromUser(RatingsWatcher<Movie> Current_User, Movie ToBeIgnored) {
        Current_User.GetIgnoreIDs().remove(Current_User.GetIgnoreIDs().indexOf(ToBeIgnored.GetID()));
        Session_Manager.addNewSessionUserChanges(Current_User);
    }
}
