package cs151project;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.lang.reflect.Field;
import java.util.Random;

//DShapeModel is the model of every shape.  DShape will contain an instance of dshapemodel
public class DShapeModel {

    //instance variables
    private int x;
    private int y;
    //private String color;
    private int width;
    private int height;
    private Color theColor;
    protected boolean isSelected = false;
    private int index;

    //No args constructor which sets the x,y to random integers.  Sets width and height of the shape as well.  Color is black
    public DShapeModel() {
        Random r = new Random();
        int Low = 0;
        int High = 350;
        int x = r.nextInt(High - Low);
        int y = r.nextInt(High - Low);

        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 100;
        this.theColor = Color.BLACK;

    }
    
    //
    public Shape getBounds() {
        return null;
    }
    
    //returns a rectangle related to instance variables
    public Rectangle getRectangle() {

        return new Rectangle(x, y, width, height);

    }
    
    //bunch of getters and setters bellow 
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return theColor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color c) {
        this.theColor = c;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int x) {
        this.height = x;
    }

    public void setWidth(int y) {
        this.width = y;
    }

    //every dshape contains a dshapemodel which knows its 4 knobs.  Here are the 4 knobs being created
    public Rectangle knobOne() {
        Rectangle rect = new Rectangle(getX() - 5, getY() - 5, 10, 10);
        return rect;
    }

    public Rectangle knobTwo() {
        Rectangle rect = new Rectangle(getWidth() + getX() - 5, getY() - 5, 10, 10);
        return rect;
    }

    public Rectangle knobThree() {
        Rectangle rect = new Rectangle(getX() - 5, getHeight() + getY() - 5, 10, 10);
        return rect;
    }

    public Rectangle knobFour() {
        Rectangle rect = new Rectangle(getWidth() + getX() - 5, getHeight() + getY() - 5, 10, 10);
        return rect;
    }

    public void setIndex(int index) {

        this.index = index;

    }

}
