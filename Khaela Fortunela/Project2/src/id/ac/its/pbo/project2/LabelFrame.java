package id.ac.its.pbo.project2;

import java.awt.FlowLayout; // specifies how components are arranged
import javax.swing.JFrame; // provides basic window features
import javax.swing.JLabel; // displays text and images
import javax.swing.SwingConstants; // common constants used with Swing
import javax.swing.ImageIcon; // loads images

@SuppressWarnings("serial")
public class LabelFrame extends JFrame
{              
   private final JLabel label; // JLabel constructed with text and icon 
   
   // LabelFrame constructor adds JLabels to JFrame
   public LabelFrame()
   {
	      super("Data Diri");
	      setLayout(new FlowLayout()); // set frame layout
	      
	      ImageIcon photo = new ImageIcon(new ImageIcon("src/image/foto.jpeg").getImage().
	    		  getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)); 
	      
	      label = new JLabel(); // JLabel constructor no arguments
	      label.setText("<html>Nama: Khaela Fortunela <br>NRP: 05111940000057<html>");   
	      label.setIcon(photo); // add icon to JLabel              
	      label.setHorizontalTextPosition(SwingConstants.CENTER);
	      label.setVerticalTextPosition(SwingConstants.BOTTOM);  
	      label.setToolTipText("Foto");                  
	      
	      add(label);
	   } 
	}