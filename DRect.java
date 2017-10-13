package cs151project;

import java.awt.Graphics;

import javax.swing.JFrame;

//class represents a DRect or a rectangle
public class DRect extends DShape {
    
    //calls super constructor and passes a drectmodel
    public DRect() {

        super(new DRectModel());

    }

//	public DRect(DShapeModel dm){
//		
//	
//		model = new DRectModel(dm.getX(), dm.getY(), dm.getColor(), dm.getWidth(), dm.getHeight());
////		
//		
//		
//		 
//		
//	}
    
    
    //every DRect knows how to draw itself.  Here is draw method
    public void draw(Graphics g) {
        DShapeModel m = super.returnModel();
        g.setColor(m.getColor());

        g.drawRect(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        g.fillRect(model.getX(), model.getY(), model.getWidth(), model.getHeight());

    }

//	public void paintComponent(Graphics g)
//	{
//	
//		super.paintComponent(g);
//		this.setBackground(Color.WHITE);
//		g.setColor(Color.GRAY);
//
//		g.fillRect(rectangleModel.getX(), rectangleModel.getY(), rectangleModel.getWidth(), rectangleModel.getHeight());
//		
//		
//		DRectModel[] list = this.getKnobs(rectangleModel.getX(),rectangleModel.getY(),rectangleModel.getWidth(),rectangleModel.getHeight());
//
//	    for(int i = 0; i < list.length;i++)
//	    {
//			g.setColor(Color.BLACK);
//	    	g.fillRect(list[i].getX()  , list[i].getY()  , list[i].getWidth()   , list[i].getHeight());
//	    	
//	    }
//	    
//	
//
//	    
//	}
    
    //main method for testing
//    public static void main(String[] args) {
//
//        JFrame f = new JFrame("Title");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        DRect d = new DRect();
//        f.add(d);
//        //f.add(o);
//        f.setSize(400, 250);
//        f.setVisible(true);
//
//    }

}
