package Managers;

import Framework.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Product_ManagerTest {
    private Product_Manager product_manager;


    @BeforeEach
    void SetUp(){
        String moviePath = "test/moviesTest.csv";
        FileReader filereader = new FileReader();
        product_manager = new Product_Manager(filereader, moviePath);
    }

    @Test
    //Adds a new product and tests if the size is one larger indicating the movie has been written to the file
    void addNewProduct() {
        int startSize = product_manager.GetProductList().size();
        product_manager.AddNewProduct(1649800,"test the Movie", "fun|great|sad|drama");
        assertEquals(startSize + 1, product_manager.GetProductList().size());
    }

    @Test
    //Retrives the movie from a specific ID to test if the name of the movie equals the name from the given ID
    void getProductFromID() {
        assertEquals("Dangerous Minds (1995)", product_manager.getProductFromID(31).GetString());
    }
}