package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Light;
import Model.Model;
import Model.Triangle;

@SuppressWarnings("serial")
public class View extends JPanel {
	private static JFrame f;
	private ArrayList<Triangle> triangles;
	private Model m;
	private int length = 1000;
	private int width = 1000;
	static String option1;
	static String option2;
	private double lightX = 100;
	private double lightY = 900;
	private double lightZ = -700;
	private double intensity = 1;
	/*
	 * show the 3D face
	 */
	public View() {
		m = new Model(lightX, lightY, lightZ, intensity);
		triangles = m.getTriangleList();
        JButton b1 = new JButton("confirm");
        JLabel l1 = new JLabel("Select Light Position:");
        JLabel l2 = new JLabel("  Select Intensity:");
        option1 = "";
        option2 = "";


        String[] items1 = new String[] { "front", "up", "down", "left", "right"}; 
        JComboBox comBox1 = new JComboBox(items1);
        
        String[] items2 = new String[] { "0.2", "0.4", "0.6", "0.8", "1.0"}; 
        JComboBox comBox2 = new JComboBox(items2);
        comBox1.setSelectedIndex(4);
        comBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option1 = (String)(((JComboBox<String>) e.getSource()).getSelectedItem());   
            }
        });
        comBox2.setSelectedIndex(4);
        comBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option2 = (String)(((JComboBox<String>) e.getSource()).getSelectedItem());                
            }
        });
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(option1 == "front") {
            		lightX = 500;
            		lightY = 850;
            		lightZ = -700;
            	}
            	if(option1 == "down") {
            		lightX = 500;
            		lightY = 0;
            		lightZ = -700;
            	}
            	if(option1 == "up") {
            		lightX = 500;
            		lightY = 1700;
            		lightZ = -700;
            	}
            	if(option1 == "right") {
            		lightX = 100;
            		lightY = 900;
            		lightZ = -700;
            	}
            	if(option1 == "left") {
            		lightX = 900;
            		lightY = 800;
            		lightZ = -700;
            	}
            	if(option2 == "0.2") {
            		intensity = 0.2;
            	}
            	if(option2 == "0.4") {
            		intensity = 0.4;
            	}
            	if(option2 == "0.6") {
            		intensity = 0.6;
            	}
            	if(option2 == "0.8") {
            		intensity = 0.8;
            	}
            	
            	if(option2 == "1.0") {
            		intensity = 1;
            	}
                m = new Model(lightX, lightY, lightZ, intensity);     
                triangles = m.getTriangleList();
        		repaint();
            }
        });
        
        this.add(l1);
        this.add(comBox1);
        this.add(l2);
        this.add(comBox2);
        this.add(b1);
		f = new JFrame();
		f.setTitle("3D Rendering");
		f.setSize(width, length);
		f.add(this, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);

		repaint();
	}

	public static void main(String[] args) {
		new View();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int[] x = new int[3];
		int[] y = new int[3];

		for (int i = 0; i < triangles.size(); i++) {
			x[0] = (int) triangles.get(i).getV1().getX();
			y[0] = (int) triangles.get(i).getV1().getY();

			x[1] = (int) triangles.get(i).getV2().getX();
			y[1] = (int) triangles.get(i).getV2().getY();

			x[2] = (int) triangles.get(i).getV3().getX();
			y[2] = (int) triangles.get(i).getV3().getY();

			g.setColor(
					new Color(triangles.get(i).getColor(), triangles.get(i).getColor(), triangles.get(i).getColor()));
			g.fillPolygon(x, y, 3);
		}
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}
}
