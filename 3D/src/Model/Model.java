package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Model {
	private ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	private ArrayList<Double> x = new ArrayList<Double>();
	private ArrayList<Double> y = new ArrayList<Double>();
	private ArrayList<Double> z = new ArrayList<Double>();
	private ArrayList<Integer> greyScale = new ArrayList<Integer>();

	private double minX = 0;
	private double minY = 0;
	private double maxX = 0;
	private double maxY = 0;
	private double minZ = 0;
	private double maxZ = 0;
	private double lightX = 900;
	private double lightY = 800;
	private double lightZ = -700;
	private double intensity = 1;
	private Light light;

	
	public Model(double lightX, double lightY, double lightZ, double intensity) {
		this.lightX = lightX;
		this.lightY = lightY;
		this.lightZ = lightZ;
		this.intensity = intensity;
				
		light = new Light(lightX, lightY, -lightZ, intensity); //要改
		loadFaceShapeData();
		loadFaceTextureData();
		dataScaling();
		TraiangleList();
		SortZ();
	}

	public void loadFaceShapeData() {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("face-shape.txt"));
			String line;
			String[] line2;
			while ((line = br.readLine()) != null) {
				try {
					line2 = line.split(" ");
					x.add(Double.valueOf(line2[0]));
					y.add(Double.valueOf(line2[1]));
					z.add(Double.valueOf(line2[2]));
				} catch (Exception e) {
				}
			}
		} catch (FileNotFoundException exc) {
			exc.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFaceTextureData() {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("face-texture.txt"));
			String line = "";
			String[] line2 = new String[3];
			while ((line = br.readLine()) != null) {
				line2 = line.split(" ");
				if (line2.length == 3) {
					for (int i = 0; i < line2.length; i++) {
						double g = Double.valueOf(line2[i]);
						greyScale.add((int) g);
					}
				}
			}
		} catch (FileNotFoundException exc) {
			exc.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dataScaling() {

		int l = 600;
		int w = 600;

		minMax();

		for (int i = 0; i < x.size(); i++) {
			x.set(i, (((x.get(i) - minX) / (maxX - minX)) * w) + w / 2.5);
			y.set(i, l - (((y.get(i) - minY) / (maxY - minY)) * l) + l / 2.5);
	        z.set(i, l - (((z.get(i) - minZ) / (maxZ - minZ)) * l) + l / 2.5);
		}
	}
	

	private void minMax() {
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i) > (maxX))// 此时由于a>=b，则返回值小于0；
			{
				maxX = x.get(i);
			}

			if (x.get(i) < minX) {
				minX = x.get(i);
			}

			if (y.get(i) > maxY)// 此时由于a>=b，则返回值小于0；
			{
				maxY = x.get(i);
			}

			if (y.get(i) < minY) {
				minY = y.get(i);
			}
			
			if (z.get(i) < minZ) {
				minZ = z.get(i);
			}
			
			if (z.get(i) < minZ) {
				minZ = z.get(i);
			}
		}
	}

	public void TraiangleList() {
		minMax();
		int l = 600;
		int w = 600;
		for (int i = 0; i < x.size(); i++) {
//			x.set(i, (((x.get(i) - minX) / (maxX - minX)) * w) + w / 2.5);
//			y.set(i, l - (((y.get(i) - minY) / (maxY - minY)) * l) + l / 2.5);
//			z.set(i, l - (((z.get(i) - minZ) / (maxZ - minZ)) * l) + l / 2.5);
			Vertex v1 = new Vertex(x.get(i), y.get(i), z.get(i), (int) greyScale.get(i));

			i++;
//			x.set(i, (((x.get(i) - minX) / (maxX - minX)) * w) + w / 2.5);
//			y.set(i, l - (((y.get(i) - minY) / (maxY - minY)) * l) + l / 2.5);
//			z.set(i, l - (((z.get(i) - minZ) / (maxZ - minZ)) * l) + l / 2.5);
			Vertex v2 = new Vertex(x.get(i), y.get(i), z.get(i), greyScale.get(i));

			i++;
//			x.set(i, (((x.get(i) - minX) / (maxX - minX)) * w) + w / 2.5);
//			y.set(i, l - (((y.get(i) - minY) / (maxY - minY)) * l) + l / 2.5);
//			z.set(i, l - (((z.get(i) - minZ) / (maxZ - minZ)) * l) + l / 2.5);
			Vertex v3 = new Vertex(x.get(i), y.get(i), z.get(i), (int) greyScale.get(i));

			triangles.add(new Triangle(v1, v2, v3,light));
		}
	}

	public void SortZ() {
		Collections.sort(triangles, new TriangleComparator());
	}
	

//	/**
//	 * calculate the unit normal of triangles
//	 */
//	public void calculateNormal() {
//		Vertex vector1;
//		Vertex vector2;
//		Vertex tempNormal;
//		double normalLength;
//		double normalX;
//		double normalY;
//		double normalZ;
//		int color;
//		
//		for (int i = 0; i < triangles.size(); i++) {
//			color = triangles.get(i).getColor();
//			vector1 = new Vertex(triangles.get(i).getV2().getX() - triangles.get(i).getV1().getX(),
//					triangles.get(i).getV2().getY() - triangles.get(i).getV1().getY(),
//					triangles.get(i).getV2().getZ() - triangles.get(i).getV1().getZ(), color);
//			vector2 = new Vertex(triangles.get(i).getV3().getX() - triangles.get(i).getV1().getX(),
//					triangles.get(i).getV3().getY() - triangles.get(i).getV1().getY(),
//					triangles.get(i).getV3().getZ() - triangles.get(i).getV1().getZ(), color);
//
//			// To get normal, cross product of vector1 and vector 2
//			tempNormal = (new Vertex(vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
//					vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ(),
//					vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX(), color));
//
//			// Normalise the normal
//			normalLength = Math.sqrt(
//					tempNormal.getX() * tempNormal.getX() + tempNormal.getY() * tempNormal.getY() + tempNormal.getZ() * tempNormal.getZ());
//			normalX = tempNormal.getX()/normalLength;
//			normalY = tempNormal.getY()/normalLength;
//			normalZ = tempNormal.getZ()/normalLength;
//
//			normal.add(new Vertex(normalX,normalY,normalZ,color));
//		}
//	}
//	
//	public void dotProduct(Light light) {
//		Vertex vector;
//		
//		// calculate the vector between the light and the center of the triangle
//		for (int i = 0; i < triangles.size(); i++) {
//			vector = new Vertex(light.getX() - triangles.get(i).getCenter().getX(),
//					light.getY() - triangles.get(i).getCenter().getY(),
//					light.getZ() - triangles.get(i).getCenter().getZ(), triangles.get(i).getColor());	
//			alpha.add(normal.get(i).getX()*vector.getX() + normal.get(i).getY()*vector.getY()+ normal.get(i).getZ()*vector.getZ());
//		}
//	}

	public ArrayList<Triangle> getTriangleList() {
		return triangles;
	}

	public void setLight(int lightX, int lightY, int lightZ, int intensity) {
		light = new Light(lightX,lightY,lightZ,intensity);
		System.out.println(lightX);
		System.out.println(lightY);
		System.out.println(lightZ);
		System.out.println(intensity);
	}

}
