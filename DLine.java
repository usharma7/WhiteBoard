package cs151project;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;

//Represents a line
public class DLine extends DShape {
    
    //NO args constructor which calls super class.
    public DLine() {
        super(new DShapeModel());
    }
    
    //every line knows how to draw itself.  
    public void draw(Graphics g) {
        DLineModel lineMod = (DLineModel) super.returnModel();
        Point p1 = lineMod.getPoint1();
        Point p2 = lineMod.getPoint2();

        Graphics2D line = (Graphics2D) g;
        line.setColor(lineMod.getColor());
        line.drawLine(p1.x, p1.y, p2.x, p2.y);

    }

//    public static void main(String[] args) {
//        JFrame f = new JFrame("Title");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //DLine d = new DLine(25,25,Color.GREEN, 100,30);
//        //f.add(d);
//        f.setSize(400, 250);
//        f.setVisible(true);
//    }

}
