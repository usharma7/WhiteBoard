package cs151project;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.TableColumn;


/* This class has all the controls on the left and a canvas instance on the right side in white

*/
public class Whiteboard extends JFrame implements ActionListener{
    //instance variables
    private JPanel contentPane; //will hold controls
    private JPanel controls;
    private JPanel shapesPanel;
    private JPanel colorPanel;
    private JPanel textPanel;
    private JPanel utilityPanel;
    private JPanel tablePanel;
    
    private JButton rect;
    private JButton oval;
    private JButton line;
    private JButton text;
    private JButton setColor;
    private JComboBox font; // placed near the textField
    private JButton moveToFront;
    private JButton moveToBack;
    private JButton remove;
    private JButton save; 
    private JButton load; 
    private JButton saveImage;
    private JButton server;
    private JButton client;
    
    private static Whiteboard board;
    private Canvas canvas;

    private JTable table;
    
    private JTextField textField;
    
   // private Canvas can;
    
    /**
     * @param args the command line arguments
     */
    
    //main method
    public static void main(String[] args) {
        // TODO code application logic here
        Whiteboard myGUI = new Whiteboard();
       // myGUI.buildGUI();
    }
    
    //singleton, get instance
    public static Whiteboard getInstance(){
		
		if(board == null)
			board = new Whiteboard();
		return board;
		
	}
    
    //constructor
    public Whiteboard(){
        setTitle("Whiteboard");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(1000, 450));
        
        setLocationRelativeTo(null);
         
        BorderLayout bm= new BorderLayout(); 
        
        contentPane = new JPanel(); //contentpane is a jpanel over the entire whiteboard.  The controls will be on another jpanel on the left, and canvas on the right
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(bm);
        
        //contentPane.setBounds(0, 0, 800, 400);
        contentPane.setSize(1000,450);
		canvas = Canvas.getInstance();

        buildGUI(); //builds all the componenets
        setVisible(true);
        setResizable(false);
        
    }
    
    public void buildGUI(){
        
        controls = new JPanel();    //will contain all other jpanels which contains their respective buttons
        //controls.setBounds(0, 0, 400, getHeight());
        controls.setSize(500, getHeight());
        controls.setLayout(new BoxLayout(controls,BoxLayout.PAGE_AXIS));
        controls.setBackground(Color.GRAY);
        
        
        
        //add all the buttons using the helper methods
        createShapeButtons();
        createColorButton();
        createUtilityButtons();
        createTable();
        createTextField();
        
        
        //add the controsl and canvas to the contentpane 
        contentPane.add(controls, BorderLayout.LINE_START);
        contentPane.add(canvas, BorderLayout.CENTER);
        add(contentPane);
        
        
    }
    
    // Helper method which creates the first line of buttons on the contentPane panel
    //Creates a sub panel, called "shapes", which holds the 4 buttons
    private void createShapeButtons(){
        shapesPanel = new JPanel();
        shapesPanel.setBackground(controls.getBackground());
        shapesPanel.setAlignmentX(0);    // puts in the top corner
        shapesPanel.setAlignmentY(0);
        rect = new JButton("Rect");
        oval = new JButton("Oval");
        line = new JButton("Line");
        text = new JButton("Text");
        
        
        shapesPanel.setLayout(new BoxLayout(shapesPanel, BoxLayout.LINE_AXIS));
        rect.addActionListener(this);
        oval.addActionListener(this);
        line.addActionListener(this);
        text.addActionListener(this);
       
       
        shapesPanel.add(rect);
        shapesPanel.add(Box.createRigidArea(new Dimension(10,0)));
        shapesPanel.add(oval);
        shapesPanel.add(Box.createRigidArea(new Dimension(10,0)));
        shapesPanel.add(line);
        shapesPanel.add(Box.createRigidArea(new Dimension(10,0)));
        shapesPanel.add(text);
        shapesPanel.add(Box.createRigidArea(new Dimension(10,0)));
        
        
        
        controls.add(shapesPanel);
        controls.add(Box.createRigidArea(new Dimension(0,25))); //creates a gap between this shape panel and the next panel added
    }
    
    //Helper method which creates the second row panel which contains one button, "set color"
    private void createColorButton(){
        colorPanel = new JPanel();
        colorPanel.setBackground(controls.getBackground());
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.LINE_AXIS));
        colorPanel.setAlignmentX(0);
     
        setColor = new JButton("Set Color");
        setColor.addActionListener(this);
        colorPanel.add(setColor);
        
        controls.add(colorPanel);
        controls.add(Box.createRigidArea(new Dimension(0,25))); //creates a gap between this color panel and the next panel added  
        
        
        save= new JButton("Save"); 
        load= new JButton("Load");
        saveImage = new JButton("Save Image");
        client = new JButton("Client");
        server = new JButton("Server");


        
        save.addActionListener(this);
        load.addActionListener(this);
        saveImage.addActionListener(this);
        client.addActionListener(this);
        server.addActionListener(this);


        
        colorPanel.add(save);
        colorPanel.add(Box.createRigidArea(new Dimension(10,0)));
        colorPanel.add(load); 
        colorPanel.add(Box.createRigidArea(new Dimension(10,0)));
        colorPanel.add(saveImage);
        colorPanel.add(client);
        colorPanel.add(server);
    }
    
    //Helper method which creates the 3 utility buttons on the third row panel
    private void createUtilityButtons(){
        utilityPanel = new JPanel();
        utilityPanel.setBackground(controls.getBackground());
        utilityPanel.setLayout(new BoxLayout(utilityPanel, BoxLayout.LINE_AXIS));
        utilityPanel.setAlignmentX(0);
        
        
        moveToFront = new JButton("Move To Front");
        moveToFront.addActionListener(this);
        moveToBack = new JButton("Move To Back");
        moveToBack.addActionListener(this);
        remove = new JButton("Remove Shape");
        remove.addActionListener(this);
        
        utilityPanel.add(moveToFront);
        utilityPanel.add(Box.createRigidArea(new Dimension(10,0)));
        utilityPanel.add(moveToBack);
        utilityPanel.add(Box.createRigidArea(new Dimension(10,0)));       
        utilityPanel.add(remove);
        utilityPanel.add(Box.createRigidArea(new Dimension(10,0)));
        
        controls.add(utilityPanel);
        controls.add(Box.createRigidArea(new Dimension(0,25))); //creates a gap between this utility panel and the next panel added
        
    
    }
    
    private void createTextField(){
        textPanel = new JPanel();
        textPanel.setBackground(controls.getBackground());
        textPanel.setAlignmentX(0);
        
       
        textField = new JTextField("Enter text", 24);   //hard coded to look good on the GUI
        
        font = new JComboBox<String>();
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] everyFont = environment.getAvailableFontFamilyNames();
        for (String f : everyFont) {
            font.addItem(f);
        }
		//panel.add(font);
        textPanel.add(textField);
        textPanel.add(font);
        
        controls.add(textPanel);
        //controls.add(Box.createRigidArea(new Dimension(0,25))); //creates a gap between this text panel and the next panel added 
    }
    
    private void createTable(){
        
    }
    
    //returns the font.  Used in canvas
    public String font(){
        return String.valueOf(font.getSelectedItem());
    }
    
    //ocntains the actions performed for all the buttons with action listeners
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
        
        if(ob == rect){
            DShapeModel dr= new DRectModel(); 
            
            canvas.addShape(dr);
            //can.revalidate();
        }else if(ob == oval){
        	DShapeModel dr= new DOvalModel(); 
        	canvas.addShape(dr);
        	canvas.revalidate();
        	
        }else if(ob == line){
//        	DShapeModel dr= new DLineModel(); 
//        	canvas.addShape(dr);
//        	canvas.revalidate();
        }else if(ob == text){
            if(textField.getText().isEmpty() == false){
                
                DShapeModel dr = new DTextModel();
                canvas.addShape(dr);
                canvas.revalidate();
            }
        
        }else if(ob == setColor){
            Color c = JColorChooser.showDialog(null, "Pick a Color", null);
            canvas.shapes.get(canvas.selectedIndex).model.setColor(c);
            canvas.repaint();
            System.out.println();
        }  
        else if(ob== save){
        	canvas.save();
        }else if(ob == saveImage){
//            String input = JOptionPane.showInputDialog("PNG File Name: ");
//            File file = new File(input + ".png");
            canvas.saveImage();
        }
        else if(ob== load){
        	canvas.open(); 
        }else if(ob == remove){
            canvas.removeShape();
        }else if(ob == moveToFront){
           canvas.moveFront();
           //canvas.revalidate();  
        }else if(ob == moveToBack){
            canvas.moveBack();
        }
        else if(ob == client)
        {
        	canvas.callClient();
        }
        else if(ob == server)
        {
        	canvas.callServer();
        }
    
    }
    
    //returns the text inputted into the jtextfield
    public String textInput(){
        return textField.getText();
    }
    
    
    
}
