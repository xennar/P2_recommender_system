package Managers;

import Framework.FileReader;
import Movie.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//This class controls the products on an overall level.
public class Product_Manager {
    private ArrayList<Movie> ProductList;
    private HashMap<Integer, Movie> ProductMap;

    //The constructor makes a Map of IDs and Movies.
    public Product_Manager(FileReader reader, String path) {
        ProductList = reader.ReadProducts(path);
            ProductMap = new HashMap<>();
            for(Movie m : ProductList)
                ProductMap.put(m.GetID(), m);
    }


    //basic Getter
    public ArrayList<Movie> GetProductList() {
        return ProductList;
    }


    //Makes new product through ID, title, and a single string of tags, separated by |
    public void AddNewProduct(int ID, String title, String tags) throws RuntimeException {
        if(ProductMap.keySet().contains(ID))
            throw new RuntimeException("Movie ID already exists");
        Movie newProduct = new Movie(ID, title, tags);
        ProductList.add(newProduct);
        Session_Manager.addNewSessionProducts(newProduct);
    }

    //returns the movie associated with the ID.
    public Movie getProductFromID(int productID){
        return ProductMap.get(productID);
    }
}

