import java.io.*;
import java.util.ArrayList;

public class Main {

    static Point min = null, max = null;

    public static void main(String[] args) {

        ArrayList<Point> points = getPoints();

        QuickHull convex = new QuickHull();
        convex.add(min);
        convex.add(max);
        points.remove(min);
        points.remove(max);
        convex.quickHull(points, min, max);
        convex.print();
    }

    /* Reads in the points from a file and finds the min & max points */
    public static ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        try (FileInputStream stream = new FileInputStream("input.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String strLine;
            String[] first = reader.readLine().split(" ");
            min = new Point(Integer.parseInt(first[0]), Integer.parseInt(first[1]));
            max = min;
            points.add(min);
            while ((strLine = reader.readLine()) != null) {
                String[] coordinates = strLine.split(" ");
                Point p = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                points.add(p);
                if (isMin(p, min))
                    min = p;
                if (isMax(p, max))
                    max = p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    public static boolean isMin(Point p1, Point p2) {
        return p1.getX() < p2.getX();
    }

    public static boolean isMax(Point p1, Point p2) {
        return p1.getX() > p2.getX();
    }
}
