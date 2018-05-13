package Framework;

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
