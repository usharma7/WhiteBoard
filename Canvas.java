/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs151project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Canvas extends JPanel {

    public ArrayList<DShape> shapes = new ArrayList<DShape>();
    private static Canvas canvas;//single instance of canvas
    private int selectedShape = 1;
    boolean[] list = new boolean[100];
    public int selectedIndex = -1; //will give the selected shape's index
    public int knobPressed = 0;
    public boolean movable = false;
    boolean delete = false; // this is for the delete method
    boolean dirty;
    File f;

    //public boolean movable;
    //singleTon
    public static Canvas getInstance() {

        if (canvas == null) {
            canvas = new Canvas();
        }

        return canvas;

    }

    /*Constructor which also holds the action listeners
    
     */
    public Canvas() {
        setBounds(315, 0, 400, 400);
        setBackground(Color.WHITE);
        setVisible(true);

        addMouseListener(new MouseAdapter() {

            @Override
            /*
             * 
             * This will get selected shape
             */
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();//gets the point on canvas
                boolean selecter = false;

                for (DShape shape : shapes) {
                    if (shape.returnModel().getRectangle().contains(p)) { // checks if point is in a shape
                        list[shapes.indexOf(shape)] = true;
                        selecter = true;
                        selectedIndex = shapes.indexOf(shape);
                    }
                }
                if (!selecter) {
                    selectedIndex = -1;
                }

                Canvas.this.repaint();

            }

            /*
             * 
             * 
             * 
             * This is for knobSelection
             * the numbers represent knobs the 4 points of the 4 knobs
             */

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    DShapeModel knobSelected = shapes.get(selectedIndex).model;
                    Point p = e.getPoint();
                    if (knobSelected.knobOne().contains(p)) {

                        knobPressed = 1;
                    } else if (knobSelected.knobTwo().contains(p)) {

                        knobPressed = 2;
                    } else if (knobSelected.knobThree().contains(p)) {
                        knobPressed = 3;
                    } else if (knobSelected.knobFour().contains(p)) {
                        knobPressed = 4;
                    } else if (knobSelected.getRectangle().contains(p)) {
                        movable = true;
                    }
                } catch (ArrayIndexOutOfBoundsException es) {
                    ;
                }

            }

            //ensures that release knobs are not selected
            public void mouseReleased(MouseEvent e) {
                //movable = false;
                knobPressed = 0;

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            //This is for moving shapes
            public void mouseDragged(MouseEvent e) {

                DShapeModel selected = shapes.get(selectedIndex).model;
//                System.out.println(selectedIndex);
//                System.out.println(movable);
                if (selectedIndex == -1 || knobPressed == 0) { // if not selected
                    // if(movable == true){
                    try {

                        selected.setX(e.getX());
                        selected.setY(e.getY());

                        Canvas.this.repaint();
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        ;
                    }
                    // }

                    //if selected
                } else if (knobPressed != 0) {

                    try {
                        selected = shapes.get(selectedIndex).model;
                        int newX = e.getX();
                        int newY = e.getY();
                        int selectedWidth = selected.getWidth();
                        int selectedHeight = selected.getHeight();
                        //resize(selectedWidth, selectedHeight, newX, newY, selected);
//                        System.out.println(selectedWidth);
//                        System.out.println(selectedHeight);
//                        System.out.println(newX);
//                        System.out.println(newY);

                        //360 rotation does not work!!!!
                        //This is for the shapes resizing
                        if (knobPressed == 1) {
                            System.out.println(knobPressed);

                            selected.setWidth(selected.getWidth() - (newX - selected.getX()));
                            selected.setHeight(selected.getHeight() - (newY - selected.getY()));
                            selected.setX(newX);
                            selected.setY(newY);
                            repaint();
                        } else if (knobPressed == 2) {
                            selected.setWidth(selected.getWidth() + (newX - (selected.getX() + selected.getWidth())));
                            selected.setHeight(selected.getHeight() + (selected.getY() - newY));
                            selected.setY(newY);
                            repaint();
                        } else if (knobPressed == 3) {
                            selected.setWidth(selected.getWidth() - (newX - selected.getX()));
                            selected.setHeight(selected.getHeight() + (newY - (selected.getY() + selected.getHeight())));
                            selected.setX(newX);
                            selected.setY(newY - selected.getHeight());
                            repaint();
                        } else if (knobPressed == 4) {
                            System.out.println(knobPressed);
                            selected.setWidth(selected.getWidth() + (newX - (selected.getX() + selected.getWidth())));
                            selected.setHeight(selected.getHeight() + (newY - (selected.getY() + selected.getHeight())));
//                            selected.setX(selected.getX());
//                            selected.setY(selected.getY());
                            repaint();
                        }

                    } catch (Exception ex) {
                        ;
                    }

                }
            }
        });

    }

    private void resize(int width, int height, int newX, int newY, DShapeModel mod) {

    }

    //Takes the shapes list and draws the graphics
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (DShape ds : shapes) {
            //System.out.println(ds);
            ds.draw(g);
            //revalidate();
        }
        if (selectedShape != -1 && delete != true) {
            paintKnobs(g);
        }
        delete = false;

    }

    // Adds the shapes to the shapes list
    public void addShape(DShapeModel dm) {
        Graphics g = this.getGraphics();
        if (dm instanceof DRectModel) {//gets the instanceOf shapes
            DRect dr = new DRect(); //creates a shape
            shapes.add(dr);
        } else if (dm instanceof DOvalModel) {
            DOval ov = new DOval();
            shapes.add(ov);
        } else if (dm instanceof DLineModel) {
            DLine li = new DLine();
            shapes.add(li);

        } else {
            DText dt = new DText();
            shapes.add(dt);
        }
//        for(DShape s: shapes){
//            System.out.println(s);
//        }
//        System.out.println();

        repaint();

    }

    //create the knobs here
    public void paintKnobs(Graphics g) {

        if (selectedIndex != -1) {
            Rectangle k1 = shapes.get(selectedIndex).returnModel().knobOne();
            Rectangle k2 = shapes.get(selectedIndex).returnModel().knobTwo();
            Rectangle k3 = shapes.get(selectedIndex).returnModel().knobThree();
            Rectangle k4 = shapes.get(selectedIndex).returnModel().knobFour();

            g.setColor(Color.BLACK);
            g.fillRect(k1.x, k1.y, k1.width, k1.height);
            g.fillRect(k2.x, k2.y, k2.width, k2.height);
            g.fillRect(k3.x, k3.y, k3.width, k3.height);
            g.fillRect(k4.x, k4.y, k4.width, k4.height);

        }

    }

    //save image as a png
    public void saveImage() {

        JFileChooser chose = new JFileChooser(".png"); // window that selects location
        chose.setCurrentDirectory(null);
        int retrival = chose.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            BufferedImage bi = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB); //makes BufferedImage 
            this.paint(bi.getGraphics());
            f = chose.getSelectedFile();
            File file = new File(f.toString() + ".png");    // this line is so that the saved file will contain the .png extension

            try {
                javax.imageio.ImageIO.write(bi, "PNG", file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    //save file and possible to reopen
    public void save() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(null);

            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {

                f = chooser.getSelectedFile();
                XMLEncoder xmlOut = new XMLEncoder(
                        new BufferedOutputStream(new FileOutputStream(f)));
                DShape[] shapeArray = shapes.toArray(new DShape[0]);
                xmlOut.writeObject(shapeArray);
                xmlOut.writeObject("This is a test");
                xmlOut.close();

                setDirty(false);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    ///remove the shape
    public void removeShape() {

        if (selectedIndex != -1) {
            shapes.remove(selectedIndex);
            delete = true;
        }
        repaint();

    }

    //opens the files
    public void open() {
        DShape[] shapeArray = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(null);
        int ret = chooser.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                f = chooser.getSelectedFile();
                XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
                shapeArray = (DShape[]) xmlIn.readObject(); // opens the shape array
                xmlIn.close();
                clear();
                for (DShape dm : shapeArray) {
                    doAdd(dm); //as this to shapess
                }
                setDirty(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //clears used in open 
    public void clear() {
        shapes.clear();
        dirty = false;
        repaint();
    }

    //add objects from open method
    public void doAdd(DShape tempShape) {
        shapes.add(tempShape);
        repaint();
        setDirty(true);
    }

    /*Moves the selected shape to the end of the arraylist.  The last element in the arraylist is
     displayed as the top most on the canvas
    */
    public void moveFront() {
        //System.out.println("test");
        if (selectedIndex != -1) {

            //simple swap within arraylist will occur
            DShape shape = shapes.get(selectedIndex);   //temp storage for the selected shape
            shapes.set(selectedIndex, shapes.get(shapes.size() - 1));
            shapes.set(shapes.size() - 1, shape);
            selectedIndex = shapes.size() - 1;    //must increment index or else wrong image will be selected on the canvas

            repaint();

        }
    }
    
    /*Moves the selected shape to the beginning of the arraylist.  The first element in the arraylist is
    displayed as the bottom most on the canvas
    
    */
    public void moveBack() {
        if (selectedIndex != -1) {

            //simple swap within arraylist will occur
            DShape shape = shapes.get(selectedIndex);   //temp storage for the selected shape
            shapes.set(selectedIndex, shapes.get(0));
            shapes.set(0, shape);
            selectedIndex = 0;    //must increment index or else wrong image will be selected on the canvas

            repaint();

        }

    }

    //Simple Client server Interaction
    public void callClient() {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.print("Received string: '");
            while (!in.ready()) {
            }
            System.out.println(in.readLine()); // reads the information 
            System.out.print("'\n");
            in.close();
        } catch (Exception e) {
            System.out.print("Error\n");
        }
    }

    public void callServer() {
        String data = "Hello Client"; // deliever this to client
        try {
            ServerSocket theServer = new ServerSocket(1234);
            Socket socket = theServer.accept();
            System.out.print("You are connected\n");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.print("Sending string: '" + data + "'\n");
            out.print(data);
            out.close();
            socket.close();
            theServer.close();
        } catch (Exception e) {
            System.out.print("Error\n");
        }
    }

}
