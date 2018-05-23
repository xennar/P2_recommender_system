//Made by Emil Palmelund Voldby, evoldb17@student.aau.dk

package Managers;

import java.util.Comparator;

import Framework.ObjectScore;
import Movie.*;

public class RecommendationComparator implements Comparator<ObjectScore<Movie>> {
    @Override
    public int compare(ObjectScore<Movie> o1, ObjectScore<Movie> o2) {
        double  result = (o2.GetScore() - o1.GetScore());
        System.out.println("Result is : " + result + " and int is " + (int) result);
        if(result == 0)
            return o2.GetObject().GetRatings().size() - o1.GetObject().GetRatings().size();
        else{
            if(result > 0 && result < 1)
                result = 1;
            if(result < 0 && result > -1)
                result = -1;
            System.out.println(result);
            return (int) result;
        }
    }
}
