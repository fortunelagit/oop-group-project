package id.ac.its.nouvelli0011.pro2;

import java.awt.FlowLayout; // specifies how components are arranged
import javax.swing.JFrame; // provides basic window features
import javax.swing.JLabel; // displays text and images
import javax.swing.SwingConstants; // common constants used with Swing
import javax.swing.ImageIcon; // loads imagea

@SuppressWarnings("serial")
public class LabelFrame extends JFrame
{              
   private final JLabel label; // JLabel constructed with text and icon 
   


   public LabelFrame()
   {
	      super("Data Diri");
	      setLayout(new FlowLayout()); // set frame layout
	      
	      ImageIcon photo = new ImageIcon(new ImageIcon("src/image/foto.jpeg").getImage().
	    		  getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)); 
	      
	      label = new JLabel(); // JLabel constructor no arguments
	      label.setText("<html>Nama: Nouvelli Cornelia <br>NRP: 05111940000011<html>");   
	      label.setIcon(photo); // add icon to JLabel              
	      label.setHorizontalTextPosition(SwingConstants.CENTER);
	      label.setVerticalTextPosition(SwingConstants.BOTTOM);  
	      label.setToolTipText("Foto");                  
	      
	      add(label);
	   } 
	}