import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Controls extends JPanel implements MouseListener{
	
	GraphicButton back = new GraphicButton(30, 30, 80, 80, "Back");
	Ventana v;
	
	
	public Controls(Ventana v) {
		super();
		this.setBackground(Color.BLACK);
		this.addMouseListener(this);
		this.v = v;
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("Atari", Font.BOLD, 30));
		Graphics2D g2 = (Graphics2D)g;
		back.paint(g2);
		g.drawString("-Te mueves con las flechas", 100, 200);
		g.drawString("-Disparas con [SPACE]", 100, 250);
		g.drawString("-Cambias de bala con [1], [2] o [3]", 100, 300);
		g.drawString("-Activa el poder con [0]", 100, 350);

		g.drawString("¡No dejes que los aliens lleguen a tu planeta!", 100, 400);

	}

	public void mouseClicked(MouseEvent e) {
		//Cuando el usuario hace clic, se cambia el panel en ventana.
		if (back.intersects(e.getX(), e.getY(), e.getX()+2, e.getY()+2)) {
			v.remove(v.getCurrentScreen());
			HomeScreen a = new HomeScreen(v);
			v.setCurrentScreen(new HomeScreen(v));
			v.add(v.getCurrentScreen());
			a.requestFocusInWindow();
		}
		v.revalidate();
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
