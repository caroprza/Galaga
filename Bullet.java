import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet {
		int xPos, yPos, width, height, kind, speed, damage;
		Image sprite;
		Rectangle boxCollider;
		
		
		public Bullet(int kind, int xPos, int yPos) {
			this.kind = kind;
			this.xPos = xPos;
			this.yPos = yPos;
			this.sprite = new ImageIcon("bullet-" + kind + ".png").getImage();
			if(kind==1) {
				this.width = 10;
				this.height = 20;
				this.speed = 15;
				this.damage = 5;
			}
			if(kind==2) {
				this.width = 20;
				this.height = 20;
				this.speed = 5;
				this.damage = 10;
			}
			if(kind==3) {
				this.width = 20;
				this.height = 100;
				this.speed = 20;
				this.damage = 30;
			}
			
			this.boxCollider = new Rectangle(this.width, this.height);
		}
		
		
	}