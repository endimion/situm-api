package gr.uagean.palaemonstiumapi.Utils;

public class PointInPolygon {


    public static boolean pointInPolygon(double[][] polygon, double[] point) {
        //A point is in a polygon if a line from the point to infinity crosses the polygon an odd number of times
        boolean odd = false;
        // int totalCrosses = 0; // this is just used for debugging
        //For each edge (In this case for each point of the polygon and the previous one)
        for (int i = 0, j = polygon.length - 1; i < polygon.length; i++) { // Starting with the edge from the last to the first node
            //If a line from the point into infinity crosses this edge
            if (((polygon[i][1] > point[1]) != (polygon[j][1] > point[1])) // One point needs to be above, one below our y coordinate
                    // ...and the edge doesn't cross our Y corrdinate before our x coordinate (but between our x coordinate and infinity)
                    && (point[0] < (polygon[j][0] - polygon[i][0]) * (point[1] - polygon[i][1]) / (polygon[j][1] - polygon[i][1]) + polygon[i][0])) {
                // Invert odd
                // System.out.println("Point crosses edge " + (j + 1));
                // totalCrosses++;
                odd = !odd;
            }
            //else {System.out.println("Point does not cross edge " + (j + 1));}
            j = i;
        }
        // System.out.println("Total number of crossings: " + totalCrosses);
        //If the number of crossings was odd, the point is in the polygon
        return odd;
    }

}
