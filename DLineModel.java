package cs151project;

import java.awt.Color;
import java.awt.Point;

public class DLineModel extends DShapeModel {
	
	private Point p1;
        private Point p2;

	
	public DLineModel( ) 
	{
            p1 = new Point();
            p2 = new Point();
	}
        
        
	public void setPoint1(Point point1){
            p1 = point1;
        
        }
        
        public void setPoint2(Point point2){
            p2 = point2;
        }
	
	public Point getPoint1(){
            return p1;
        
        }
        
        public Point getPoint2(){
            return p2;
        }
        
        




}
