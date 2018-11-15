package Model;
import java.util.Comparator;

public class TriangleComparator implements Comparator<Triangle>{
    public int compare(Triangle t1, Triangle t2) {
        return (int) (t2.getCenter().getZ() - t1.getCenter().getZ());
    }
    
}
