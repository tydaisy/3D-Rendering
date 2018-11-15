package Model;
import java.awt.Color;

public class Vertex {
    private double x;
    private double y;
    private double z;
    private int color;
    Vertex(double x, double y, double z, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public int getColor() {
		return color;
	}

	public void scale(double mul) {

		x = x * mul;
		y = y * mul;
		z = z * mul;

	}

}
