/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfeapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author PC-ASUS
 */
public class Drawline extends JPanel {
    	public Dimension getPreferredSize() {
		return new Dimension(240, 50);
	}
	
	protected void paintComponent(Graphics g) {
 
		g.setColor( Color.red );
		// X Start, Y Start, X End, Y End
		// X = <---------->
		g.drawLine ( 0, 0, 240, 50 );
 
	}
}
    

