/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs151project;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author amitranganathan
 */

//represents Text
public class DText extends DShape {

    private String text;
    private BufferedImage image;
    private String f;
    
    
    //No args constructor which calls super.  Gets a whiteboard instance so that we know the textinput and the font that is selected.  
    public DText() {
        super(new DTextModel());
        Whiteboard w = Whiteboard.getInstance();
        text = w.textInput();
        f = w.font();
        System.out.println(text);
    }

    //Draws the text on the canvas.  
    public void draw(Graphics g) {
        DShapeModel lineMod = super.returnModel();  //get the dshapemodel of the line

        image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB); //doesnt matter what width and height because we will change it later
        Graphics2D g2d = image.createGraphics();
        Font font = new Font(f, Font.PLAIN, 100);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);   //width of text
        int height = fm.getHeight();        //height of text

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  //creates the image
        //setting right information for the picture
        g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.setColor(model.getColor());
        g2d.drawString(text, 0, fm.getAscent());
        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);  //draws the image

    }

}
