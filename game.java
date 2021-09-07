package graph;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public class game extends Canvas implements KeyListener
{
	private boolean check, H1, H2, H3, G, T, U, I;
	private int S1, S2, Score, P1, P2, G1, G2;
	private String Guess;
	
		//this is the constructor
	public game()
	{
		check = false;
		H1 = false;
		H2 = false;
		H3 = false;
		G = false;
		T = false;
		U = false;
		I = false;
		S1 = (int)(Math.random() * 6) + 1;
		S2 = (int)(Math.random() * 6) + 1;
		G1 = 0;
		G2 = 0;
		P1 = 1;
		P2 = 1;
		Guess = "";
		Score = 50;
		setBackground(Color.BLACK);
		addKeyListener( this );
		setFocusable( true );
		
	}

	public void paint( Graphics wi )
	{
		
		
		
		Rectangle rect2 = new Rectangle(175, 175, 150, 150);
		Rectangle rect = new Rectangle( 375, 175, 150, 150);
		
		Graphics2D window = (Graphics2D) wi;
		window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		
		
		drawSq(rect2, rect, window, P1, P2);
		hint (window, S1, S2);
		Score(window);
		if(G) {
			window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			window.drawString("Make A Guess", 200, 550);
			window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			G = false;
		}
		textBox(window, Guess);
		if(check) {
			
			int r = 0;
			int l = (int)(Math.random() * 20) + 2;
			
			while(l % 2 == 1) {
				l = (int)(Math.random() * 20) + 2;
			}
			
			while( r < l) { 
			
				window.setColor( Color.WHITE );
				
				if(r%2 == 0) {
					drawSq(rect2, rect, window);
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				
				else {
					drawDi(rect2, rect, window);
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				window.clearRect(0, 0, 800, 600);
				r++;
			}	
			
			
			drawSq(rect2, rect, window,S1, S2);


			H1 = false;
			H2 = false;
			H3 = false;
			I = false;
			T = false;
			U = false;
			
			if(!Guess.equals("")) {
				int g = Integer.parseInt(Guess);
				if(g == (S1+S2)) {
					window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					window.drawString("Correct", 600, 500);
					Score += 5;
				}
				int l1 =  (S1+S2)-1;
				int l2 =  (S1+S2)+1;
				if(g == l1 || g == l2) {
					window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
					window.drawString("You were 1 off, but I will give it to you", 200, 500);
					Score += 3;
				}
				l1 =  (S1+S2)-2;
				l2 =  (S1+S2)+2;
				if(g == l1 || g == l2) {
					window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
					window.drawString("You were 2 off, but I will allow it", 200, 500);
					Score += 1;
				}
				if(g > l2 || g < l1) {
					window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					window.drawString("Incorrect", 600, 500);
				}
				
				 
			}
			
			P1 = S1;
			P2 = S2;
			
			S1 = (int)(Math.random() * 6) + 1;
			S2 = (int)(Math.random() * 6) + 1;
			Guess = "";
			
			Score(window);
			hint (window, S1, S2);
			
			check = false;
		}
	}
	
	public void textBox(Graphics2D window, String t) {
		window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		window.drawString(t, 200, 500);
		window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	}
	
	public void keyPressed(KeyEvent e) {
 
		String p = "";
		if (e.getKeyCode()  == KeyEvent.VK_1) {
			p+="1";
			}
		if (e.getKeyCode()  == KeyEvent.VK_2) {
			p+="2";
			}
		if (e.getKeyCode()  == KeyEvent.VK_3) {
			p+="3";
			}
		if (e.getKeyCode()  == KeyEvent.VK_4) {
			p+="4";
			}
		if (e.getKeyCode()  == KeyEvent.VK_5) {
			p+="5";
			}
		if (e.getKeyCode()  == KeyEvent.VK_6) {
			p+="6";
			}
		if (e.getKeyCode()  == KeyEvent.VK_7) {
			p+="7";
			}
		if (e.getKeyCode()  == KeyEvent.VK_8) {
			p+="8";
			}
		if (e.getKeyCode()  == KeyEvent.VK_9) {
			p+="9";
			}
		if (e.getKeyCode()  == KeyEvent.VK_0) {
			p+="0";
			}
		 if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && Guess.length()>0)
		    {  
		       Guess=Guess.substring(0,Guess.length()-1);
		    }
		
		if (e.getKeyCode()  == KeyEvent.VK_H) {
			if(Score >= 10) {
				H1 = true; 
				repaint();
			}
		}
		if (e.getKeyCode()  == KeyEvent.VK_J) {
			if(Score >= 30) {
				H2 = true; 
				repaint();
			}
		}
		if (e.getKeyCode()  == KeyEvent.VK_K) {
			if(Score >= 50) {
				H3 = true; 
				repaint();
			}
		}
		Guess += p;
		repaint();
		if (e.getKeyCode()  == KeyEvent.VK_SPACE) {
			if(Guess.equals("")) {
				G = true;
				//System.out.println("Make a Guess");
				repaint();
			}
			else {
				check = true;
				repaint();
			}
		}
 
		else {check = false;}
    }
	
	
	public void Score(Graphics2D window) {
		window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		window.drawString("Score: "+Score, 30, 50);
	}
	public void hint( Graphics2D window, int f1, int f2) {
		window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		String p1 = "";
		window.drawString( "HINT 1", 600, 150 );
			
			int i = f1+f2;
			int f = i-3, p = i+3;
			if(f<2) {
				f = 2;
			}
			if(p>12) {
				p = 12;
			}
			if((H1 == true && Score >= 10)|| T == true) {
				p1 = "It is between " + f +" and "+ p +".";
				window.drawString( p1, 600, 175 );
				
				if(T== false) {Score-=10;}
				T = true;
			}
			else if(Score < 10 && H1 == true && T==false) {
				window.drawString( "Not High Engouh Score", 600, 175 );
			}
			else {
				window.drawString( "This hint costs: 10 points", 600, 175 );
				
			}
		window.drawString( "HINT 2", 600, 200 );
		
			if((H2 == true && Score >= 30) || U == true) {
				if(U == false) {
					G1 = (int)(Math.random() * 12) + 1;
					G2 = (int)(Math.random() * 12) + 1;
					while( G1 == i) {
						G1 = (int)(Math.random() * 12) + 1;
					}
					while (G2 == i && G2 == G1) {
						G2 = (int)(Math.random() * 12) + 1;
					}
					
				}
				
				p1 = "It is " + G1 +" or "+ i +" or "+ G2 +".";
				window.drawString( p1, 600, 225 );
				if(U==false) {Score-=30;}
				 U = true;
				
			}
			else if(Score < 30 && H2 == true && U == false) {
				window.drawString( "Not High Engouh Score", 600, 225 );
			}
			else {
				window.drawString( "This hint costs: 30 points", 600, 225 );
			}
		window.drawString( "HINT 3", 600, 250 );
		
		if((H3 == true && Score >= 50)|| I == true ) {
			p1 = "It is "+ i+".";
			window.drawString( p1, 600, 275 );
			if(I==false) {Score-=50;} 
			I = true;
			}
		else if(Score < 50 && H3 == true && I == false) {
			window.drawString( "Not High Engouh Score", 600, 275 );
		}
		else {
			window.drawString( "This hint costs: 50 points", 600, 275 );
			
		}
	}
	
	public void drawSq(Rectangle rect2, Rectangle rect, Graphics2D window) {
		window.setColor( Color.WHITE );
		window.fill(rect2);
		int r = (int)(Math.random() * 6) + 1;
		dots(r, rect2.x, rect2.y , window);
		
		
		window.setColor( Color.WHITE );
		window.fill( rect );
		r = (int)(Math.random() * 6) + 1;
		dots(r, rect.x, rect.y, window);
	}
	
	
	public void drawSq(Rectangle rect2, Rectangle rect, Graphics2D window, int f1, int f2) {
		window.setColor( Color.WHITE );
		
		window.setColor( Color.WHITE );
		window.fill(rect2);
		dots(f1, rect2.x, rect2.y , window);
		
		
		window.setColor( Color.WHITE );
		window.fill( rect );
		dots(f2, rect.x, rect.y, window);
	}
	public void drawDi(Rectangle rect2, Rectangle rect, Graphics2D window) {
		
		Polygon pol = rota(rect2, window);
		window.setColor( Color.WHITE );
		window.fillPolygon( pol );
		int r = (int)(Math.random() * 6) + 1;
		Rdots(r, pol.xpoints[0], pol.ypoints[0] , window);
		
		
		Polygon pol2 = rota(rect, window);
		window.setColor( Color.WHITE );
		window.fillPolygon( pol2 );
		r = (int)(Math.random() * 6) + 1;
		Rdots(r, pol2.xpoints[0], pol2.ypoints[0] , window);
	}
	
	public void dots(int numb, int x, int y, Graphics window) {

		window.setColor(Color.RED);
		if(numb == 1) {
			window.fillOval(x+55,y+55,40,40);
		}
		
		if(numb == 2) {
			window.fillOval(x+10,y+10,40,40);
			window.fillOval(x+100,y+100,40,40);
		}
		
		if(numb == 3) {
			window.fillOval(x+10,y+10,40,40);
			window.fillOval(x+55,y+55,40,40);
			window.fillOval(x+100,y+100,40,40);
		}
		
		if(numb == 4) {
			window.fillOval(x+10,y+10,40,40);
			window.fillOval(x+10,y+100,40,40);
			window.fillOval(x+100,y+100,40,40);
			window.fillOval(x+100,y+10,40,40);
		}
		
		if(numb == 5) {
			window.fillOval(x+10,y+10,40,40);
			window.fillOval(x+10,y+100,40,40);
			window.fillOval(x+100,y+100,40,40);
			window.fillOval(x+100,y+10,40,40);
			window.fillOval(x+55,y+55,40,40);
		}
		
		if(numb == 6) {
			window.fillOval(x+10,y+10,40,40);
			window.fillOval(x+10,y+100,40,40);
			window.fillOval(x+100,y+100,40,40);
			window.fillOval(x+100,y+10,40,40);
			window.fillOval(x+10,y+55,40,40);
			window.fillOval(x+100,y+55,40,40);
		}
	}
	
	public void Rdots(int numb, int x, int y, Graphics window) {

		window.setColor(Color.RED);
		if(numb == 1) {
			window.fillOval(x+80, y-20,40,40);
		}
		
		if(numb == 2) {
			window.fillOval(x+20,y-20,40,40);
			window.fillOval(x+140,y-20,40,40);
		}
		
		if(numb == 3) {
			window.fillOval(x+20,y-20,40,40);
			window.fillOval(x+80, y-20,40,40);
			window.fillOval(x+140,y-20,40,40);
		}
		
		if(numb == 4) {
			window.fillOval(x+20,y-20,40,40);
			window.fillOval(x+140,y-20,40,40);
			window.fillOval(x+80,y-80,40,40);
			window.fillOval(x+80,y+40,40,40);
		}
		
		if(numb == 5) {
			window.fillOval(x+20,y-20,40,40);
			window.fillOval(x+140,y-20,40,40);
			window.fillOval(x+80,y-80,40,40);
			window.fillOval(x+80,y+40,40,40);
			window.fillOval(x+80, y-20,40,40);
		}
		
		if(numb == 6) {
			window.fillOval(x+20,y-20,40,40);
			window.fillOval(x+140,y-20,40,40);
			window.fillOval(x+80,y-80,40,40);
			window.fillOval(x+80,y+40,40,40);
			window.fillOval(x+50, y+10,40,40);
			window.fillOval(x+110, y-50,40,40);
		}
	}
	
	public static Polygon rota (Rectangle a, Graphics2D window) {
		int[] xPoints = {a.x-25, a.x+75, a.x+175, a.x+75};
		int[] yPoints = {a.y+75, a.y-25, a.y+75, a.y+175};
		
		Polygon pol = new Polygon( xPoints, yPoints, 4);
		window.setColor(Color.white);
		window.fillPolygon( pol );
		
		return pol;
	}
	
	public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new game();
        canvas.setSize(800, 600);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}