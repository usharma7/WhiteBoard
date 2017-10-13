package cs151project;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class DOval extends DShape {
	

//	public DOval(DShapeModel dm){
//		model= new DOvalModel(dm.getX(), dm.getY(), dm.getColor(), dm.getWidth(), dm.getHeight());
//	}
//        
//	public DOval(int x, int y, Color color, int width, int height ) 
//	{
//		super();
//		model = new DOvalModel(x,y,color,width,height);
//
//	}
	
	public DOval(){
		super(new DOvalModel()); 
		
		
		
	}
	
	
        public void draw(Graphics g){
            DShapeModel m = super.returnModel();
            g.setColor(m.getColor());
            
            g.drawOval(model.getX(), model.getY(), model.getWidth(), model.getHeight());
            g.fillOval(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        
        }
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
 
		g.setColor(model.getColor());
		g.fillOval(model.getX(), model.getY(), model.getWidth(), model.getHeight());
		
		
	}
	
//	public static void main(String[] args)
//	{
//		JFrame f  = new JFrame("Title");
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		DOval d = new DOval(25,25,Color.GREEN, 100,30);
//		f.add(d);
//		f.setSize(400,250);
//		f.setVisible(true);
//		
//	}

}
