/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs151project;

import cs151project.DShapeModel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

//DShape class.  Every shape extends DShape
public class DShape extends JPanel {

//	private int x;
//	private int y;
//	private Color color;
    protected DShapeModel model;    //every subclass has access to this dshapemodel
    //protected boolean isSelected;   

    public DShape(DShapeModel mod) {
        // TODO Auto-generated constructor stub
        model = mod;
    }

    /*
        /   SubClasses will override this method
     */
    public void draw(Graphics g) {

    }
    
    //returns the model
    public DShapeModel returnModel() {
        return model;
    }

    public void makeModel(DShapeModel mod) {
        model = mod;

    }
//        
//	public DRectModel[] getKnobs(int x, int y, int width, int height)
//	{
//		DRectModel[] list = new DRectModel[4];
//		
//		for(int i = 0; i< list.length; i++)
//		{
//			if(i == 0)
//			{
//
//				DRectModel temp = new DRectModel(x-5,y-5,Color.black,5,5);
//				list[i] = temp;
//			}
//			else if(i == 1)
//			{
//
//				DRectModel temp = new DRectModel(x + width ,y +height , Color.RED,5,5);
//				list[i] = temp;
//			}
//			else if(i == 2)
//			{
//
//				DRectModel temp = new DRectModel(x-5 ,y + height, Color.RED,5,5);
//				list[i] = temp;
//			}
//			else
//			{
//
//				DRectModel temp = new DRectModel(x+width,y-5, Color.RED,5,5);
//				list[i] = temp;
//			}
//			
//			
//		}
//		return list;
//		
//	}
//        

}
