package Framework;

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
