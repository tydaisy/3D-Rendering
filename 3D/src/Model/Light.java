package Model;

public class Light {
    private double x;
    private double y;
    private double z;
    private double intensity;
    
    public Light(double x, double y, double z, double intensity) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.intensity = intensity;
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


	public double getIntensity() {
		return intensity;
	}

}
