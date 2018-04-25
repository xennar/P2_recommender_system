package RatingsWatcher;

import Framework.Basic_Characteristics;
import Framework.User;

import java.util.ArrayList;
import java.util.Map;


public class RatingsWatcher<T extends Basic_Characteristics> extends User {
    private ArrayList<T> RatedProducts;
    private Map<Integer, Double> UserRatings;
    private ArrayList<User> Neighbors;
    private ArrayList<T> IgnoreList;


    public ArrayList<T> GetRatedProducts(){
        return RatedProducts;
    }

    public double GetProductRating(Integer ProductID){
        return UserRatings.get(ProductID);
    }

    public void AddNewRatedProduct(T NewProduct, double rating){
        RatedProducts.add(NewProduct);
        UserRatings.put(NewProduct.GetID(), rating);
    }



    public ArrayList<User> GetNeighbors(){
        return Neighbors;
    }


    public void UpdateNeighbors(){

    }
}
