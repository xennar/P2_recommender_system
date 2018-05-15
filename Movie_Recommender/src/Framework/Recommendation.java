package Framework;

//This class is the most basic recommendation, and is extended by, and will be extended by, all other products that are recommended.
public class Recommendation implements Basic_Characteristics {
    private int ProductID;
    private String ProductName;

    public Recommendation(int ProductID, String ProductName) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
    }

    public int GetID() {
        return ProductID;
    }

    public String GetString() {
        return ProductName;
    }
}
