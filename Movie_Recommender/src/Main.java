//import package p2;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("active");
        InfoGatherer ratingsMake = new InfoGatherer();
        ratingsMake.MakeRatings();
        Reader NewReader = new Reader();
        NewReader.ReadToMovies();
    }
}
