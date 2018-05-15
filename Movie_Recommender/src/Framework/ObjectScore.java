package Framework;

//This class has the purpose of holding an object and a score associated with that object. This makes it possible to
//make calculations over the object, save the result and then access that as needed, here by sorting and getting the highest scorer.
public class ObjectScore<T> {
    private T object;
    private double Score;

    public ObjectScore(T object, double Score) {
        this.object = object;
        this.Score = Score;
    }

    public T GetObject() {
        return object;
    }

    public double GetScore() {
        return Score;
    }
}
