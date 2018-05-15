package Managers;

import Framework.FileReader;
import Movie.Movie;

import java.io.IOException;
import java.util.ArrayList;

public class Product_Manager {
    private ArrayList<Movie> ProductList;

    public Product_Manager(FileReader reader, String path) {
            ProductList = reader.ReadProducts(path);
    }


    public ArrayList<Movie> GetProductList() {
        return ProductList;
    }

    public void AddNewProduct(int ID, String title, String tags) {
        Movie newProduct = new Movie(ID, title, tags);
        ProductList.add(newProduct);
        Session_Manager.addNewSessionProducts(newProduct);
    }
    public Movie getProductFromID(int productID){
        return ProductList.get(productID - 1);
    }
}

