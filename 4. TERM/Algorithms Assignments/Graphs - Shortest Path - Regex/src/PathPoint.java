
public class PathPoint extends Point{
    public double cost;

    public PathPoint(int x, int y) {
        super(x, y);
        this.cost = Double.POSITIVE_INFINITY;
    }
}
