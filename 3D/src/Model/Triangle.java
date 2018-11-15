package Model;

public class Triangle {
	private Vertex v1;
	private Vertex v2;
	private Vertex v3;
	private Vertex center;
	private Vertex normal;

	private int color =0;
	private double alpha;

	Triangle(Vertex v1, Vertex v2, Vertex v3, Light light) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
//		this.color = calculateColor();
		this.center = calculateCenterVertex();
		
		calculateNormal();
		dotProduct(light);
		setColor(light);
	}

	private int calculateColor() {
		int greyScale = (v1.getColor() + v2.getColor() + v3.getColor()) / 3;
		return greyScale;
	}

	private Vertex calculateCenterVertex() {
		Double x = (v1.getX() + v2.getX() + v3.getX()) / 3.0;
		Double y = (v1.getY() + v2.getY() + v3.getY()) / 3.0;
		Double z = (v1.getZ() + v2.getZ() + v3.getZ()) / 3.0;
		center = new Vertex(x, y, z, color);
		return center;
	}

	/**
	 * calculate the unit normal of triangles
	 */
	public void calculateNormal() {
		Vertex vector1;
		Vertex vector2;
		Vertex tempNormal;
		double normalLength;
		double normalX;
		double normalY;
		double normalZ;

		vector1 = new Vertex(v2.getX() - v1.getX(), v2.getY() - v1.getY(), v2.getZ() - v1.getZ(), color);
		vector2 = new Vertex(v3.getX() - v1.getX(), v3.getY() - v1.getY(), v3.getZ() - v1.getZ(), color);

		// To get normal, cross product of vector1 and vector 2
		tempNormal = (new Vertex(vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
				vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ(),
				vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX(), color));

		// Normalise the normal
		normalLength = Math.sqrt(tempNormal.getX() * tempNormal.getX() + tempNormal.getY() * tempNormal.getY()
				+ tempNormal.getZ() * tempNormal.getZ());
		normalX = tempNormal.getX() / normalLength;
		normalY = tempNormal.getY() / normalLength;
		normalZ = tempNormal.getZ() / normalLength;

		normal = new Vertex(normalX, normalY, normalZ, color);

	}

	public void dotProduct(Light light) {
		Vertex vector;
		double vectorLength;
		double vectorX;
		double vectorY;
		double vectorZ;
		
		// calculate the vector between the light and the center of the triangle
		vector = new Vertex(light.getX() - center.getX(), light.getY() - center.getY(), light.getZ() - center.getZ(),
				color);
		
		// Normalise the vector between light and the center of the triangle
		vectorLength = Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY()
				+ vector.getZ() * vector.getZ());
		
		vectorX = vector.getX() / vectorLength;
		vectorY = vector.getY() / vectorLength;
		vectorZ = vector.getZ() / vectorLength;
		
		//dot product
		alpha = normal.getX() * vectorX + normal.getY() * vectorY + normal.getZ() * vectorZ;
		if (normal.getZ() < 0) {
			normal.scale(-1);
		}

	}

	public void setColor(Light light) {
		color = (int) (alpha * 255 * light.getIntensity());
		if (color < 0) {
			color = 0;
		}
		if (color > 255) {
			color = 255;
		}
	}

	public Vertex getV1() {
		return v1;
	}

	public Vertex getV2() {
		return v2;
	}

	public Vertex getV3() {
		return v3;
	}

	public Vertex getCenter() {
		return center;
	}

	public int getColor() {
		return color;
	}

}
