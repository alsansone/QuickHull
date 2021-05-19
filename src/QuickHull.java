import java.util.ArrayList;
import java.util.List;

public class QuickHull {

    private final List<Point> convexHull;

    QuickHull() {
        this.convexHull = new ArrayList<>();
    }

    void add(Point p) {
        this.convexHull.add(p);
    }

    void print() {
        for (Point p: convexHull)
            System.out.println(p.toString());
    }

    void quickHull(List<Point> points, Point min, Point max) {
        List<Point> leftSide = new ArrayList<>();
        List<Point> rightSide = new ArrayList<>();
        for (Point p: points) {
            if (getSide(min, max, p) == 1)
                leftSide.add(p);
            else if (getSide(min, max, p) == -1)
                rightSide.add(p);
        }
        findHull(leftSide, min, max);
        findHull(rightSide, max, min);
    }

    private void findHull(List<Point> points, Point p, Point q) {
        if (points.isEmpty())
            return;

        Point farthestPoint = getFarthestPoint(points, p, q);
        convexHull.add(farthestPoint);
        points.remove(farthestPoint);

        List<Point> s1 = new ArrayList<>();
        List<Point> s2 = new ArrayList<>();

        for (Point point: points) {
            if (isInTriangle(p, q, farthestPoint, point))
                points.remove(point);
            else {
                if (getSide(p, farthestPoint, point) > 0)
                    s1.add(point);
                else if (getSide(q, farthestPoint, point) < 0)
                    s2.add(point);
            }
        }
        findHull(s1, p, farthestPoint);
        findHull(s2, farthestPoint, q);
    }

    private double getSlope(Point a, Point b) {
        return (b.getY() - a.getY()) / (b.getX() - a.getX());
    }

    private double getIntercept(Point a, Point b) {
        return a.getY() - getSlope(a, b) * a.getX();
    }

    private Point getFarthestPoint(List<Point> points, Point p, Point q) {
        Point farthest = points.get(0);
        double farthestDistance = getDistance(p, q, farthest);
        for (int i = 1; i < points.size(); i++) {
            Point testPoint = points.get(i);
            double distance = getDistance(p, q, testPoint);
            if (distance > farthestDistance) {
                farthest = testPoint;
                farthestDistance = distance;
            }
        }
        return farthest;
    }

    private double getDistance(Point a, Point b, Point p) {
        double slope = getSlope(a, b);
        double yIntercept = getIntercept(a, b);
        double distance = ((-1 * slope * p.getX()) + (p.getY() - yIntercept) / Math.sqrt(slope * slope + 1));
        if (distance < 0) distance = -distance;
        return distance;
    }

    private int getSide(Point a, Point b, Point p) {
        double result = (b.getX() - a.getX()) * (p.getY() - a.getY()) - (b.getY() - a.getY()) * (p.getX() - a.getX());
        if (result > 0)
            return 1;
        else if (result < 0)
            return -1;

        return 0;
    }

    private boolean isInTriangle(Point a, Point b, Point c, Point p) {
        double result1 = (p.getX() - a.getX()) * (b.getY() - a.getY()) - (p.getY() - a.getY()) * (b.getX() - a.getX());
        double result2 = (p.getX() - b.getX()) * (c.getY() - b.getY()) - (p.getY() - b.getY()) * (c.getX() - b.getX());
        double result3 = (p.getX() - c.getX()) * (a.getY() - c.getY()) - (p.getY() - c.getY()) * (a.getX() - c.getX());
        return result1 > 0 && result2 > 0 && result3 > 0;
    }
}
