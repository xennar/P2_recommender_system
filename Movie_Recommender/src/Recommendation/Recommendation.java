package Recommendation;

import User.*;

public class Recommendation {
    private int ProductID;
    private String ProductName;

    public Recommendation(int ProductID, String ProductName){
        this.ProductID = ProductID;
        this.ProductName = ProductName;
    }

    public int GetProductID(){
        return ProductID;
    }

    public String GetProductName(){
        return ProductName;
    }

    public void GetRecommendation(MovieWatcher ActiveUser){

    }
}
