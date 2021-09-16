package graph;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public class Game extends JPanel implements KeyListener
{
	private boolean check, H1, H2, H3, G, T, U, I, bet, Start;
	private int S1, S2, Score, P1, P2, G1, G2, Co, g1, g2, falliply;
	private String Guess, PGuess;
	private ArrayList<Integer> j;
	private BufferedImage img = null;
	private BufferedImage i = null;
	private BufferedImage mmm = null;
	private Falling f;
	private Mousey m;


	public Game()
	{

		Co = 0;
		check = false;
		bet = false;
		H1 = false;
		H2 = false;
		H3 = false;
		G = false;
		T = false;
		U = false;
		I = false;
		Start = false;
		S1 = (int)(Math.random() * 6) + 1;
		S2 = (int)(Math.random() * 6) + 1;
		G1 = 0;
		G2 = 0;
		g1 = 0;
		g2 = 0;
		P1 = 1;
		P2 = 1;
		falliply = 20;
		Guess = "";
		PGuess = "";
		m = new Mousey();
		this.addMouseListener(m);
		Score = 20;
		j = new ArrayList<>();
		setBackground(Color.BLACK);
		addKeyListener( this );
		setFocusable( true );
		try
		{
			img = ImageIO.read( new File("hand.png" ));
			i = ImageIO.read( new File("dice.png" ));
			mmm = ImageIO.read( new File("money.jpg"));
		}
		catch ( IOException exc )
		{
			//TODO: Handle exception.
		}

	}

	private boolean falls = false;
	private int dtime = 0;
	public void paintComponent( Graphics wi )
	{

		super.paintComponent(wi);
		Rectangle rect2 = new Rectangle(175, 175, 150, 150);
		Rectangle rect = new Rectangle( 375, 175, 150, 150);

		Graphics2D window = (Graphics2D) wi;
		window.setFont(new Font("TimesRoman", Font.PLAIN, 20));

		if(!Start) {
			setBackground(Color.black);
			window.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			window.setColor(Color.white);
			window.drawString("Dice Game", 165, 220);
			window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			window.drawString("Press enter to Start", 200, 280);
			window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			window.drawImage( img, 225, 300, 300, 250, null );
			window.drawImage( i, 275, 325, 200, 150, null );
		}

		else {
			setBackground(Color.black);


			drawSq(rect2, rect, window, P1, P2);
			hint (window, S1, S2);
			Score(window);
			if(G) {

				window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
				window.drawString("Make A Guess", 200, 550);
				window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				G = false;
			}
			if(!check) {textBox(window, Guess);}
			if(check) {

				textBox(window, PGuess);
				if(G) {

					window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					window.drawString("Make A Guess", 200, 550);
					window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
					G = false;
				}
				else {
					window.drawString("How Much Do You Bet?", 200, 450);
				}
				textBox(window, Guess, 400, 500);


				if(!Guess.equals("")){ Co = Integer.parseInt(Guess);}


				if(bet) {

					if (dtime == 0) {
						dtime = (int)(Math.random() * 80) + 20;

						while(dtime % 20 == 10) {
							dtime = (int)(Math.random() * 80) + 20;
						}
					}

					if (dtime > 0) { 

						window.setColor( Color.WHITE );
						if(dtime % 20 == 0) {
							drawSq(rect2, rect, window);		
						}

						else {
							drawDi(rect2, rect, window);
						}

						dtime--;
					}	

					if (dtime == 0) {
						drawSq(rect2, rect, window,S1, S2);


						H1 = false;
						H2 = false;
						H3 = false;
						I = false;
						T = false;
						U = false;

						if(!Guess.equals("")) {
							int g = Integer.parseInt(PGuess);
							if(g == (S1+S2)) {
								window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
								window.drawString("Correct", 600, 500);
								if(S1 == S2) {
									window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
									window.drawString("You got a double! Double Points", 10, 550);
									window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
									Score += Co*2;
								}
								else {
									Score += Co;
								}
							}
							int l1 =  (S1+S2)-1;
							int l2 =  (S1+S2)+1;
							if(g == l1 || g == l2) {
								window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
								window.drawString("You were 1 off, but I will give it to you", 200, 500);
								Score += Co/2;
							}
							if( g != (S1+S2) && g != (S1+S2-1) && g !=(S1+S2+1) ) {
								window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
								window.drawString("Incorect", 600, 500);
								Score -= Co;
							}


						}



						P1 = S1;
						P2 = S2;

						S1 = (int)(Math.random() * 6) + 1;
						S2 = (int)(Math.random() * 6) + 1;
						Guess = "";
						PGuess = "";

						j = new ArrayList<>();

						Score(window);
						hint (window, S1, S2);

						check = false;
						bet = false;
					}
				}
			}
			
			if (falls || (int)(Math.random()*100*falliply) == 0) {
				if (!falls || f == null) {
					f = new Falling(Score);
					Score = 0;
					falls = true;
					falliply--;
					if (falliply < 0) falliply = 0;
				}
				doMuns(window);
				while (!m.orders.isEmpty()) {
					int[] hey = m.orders.poll();
					Score += f.killThem(hey[0], hey[1]);
				}
				
				if (!f.hasAny()) falls = false;
			}
			
		}
	}
	
	public void doMuns(Graphics2D window) {
		window.setColor(Color.GREEN);
		for (int x = f.moneys.size()-1; x >= 0; x--) {
			window.drawImage( mmm, f.moneys.get(x).x, f.moneys.get(x).y, f.moneys.get(x).width, f.moneys.get(x).height, null );
			
			if (f.moneys.get(x).fall())
				f.moneys.remove(x);
		}
	}

	public void textBox(Graphics2D window, String t) {
		window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		window.drawString(t, 200, 500);
		window.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	}

	public void textBox(Graphics2D window, String t, int q, int q1) {
		window.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		window.drawString(t, q, q1);
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
			if(Score >= 1) {
				H1 = true; 
				//repaint();
			}
		}
		if (e.getKeyCode()  == KeyEvent.VK_J) {
			if(Score >= 3) {
				H2 = true; 
				//repaint();
			}
		}
		if (e.getKeyCode()  == KeyEvent.VK_K) {
			if(Score >= 5) {
				H3 = true; 
				//repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && Start == false) {
			Start = true;
			//repaint();
		}
		Guess += p;
		//repaint();
		if (e.getKeyCode()  == KeyEvent.VK_SPACE) {
			if(Guess.equals("") && PGuess.equals("")) {
				G = true;
				//System.out.println("Make a Guess");
				//repaint();
			}

			else{
				if(Guess.equals("")) {
					G = true;
					//repaint();
				}
				if(!check) {
					PGuess = Guess;
					Guess = "";
				}
				check = true;
				if(!Guess.equals("") && !PGuess.equals("")) {
					bet = true;
					//repaint();
				}
				//repaint();
			}
		}

		//else {check = false;}
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

		if((H1 == true && Score >= 1)|| T == true) {
			if(T == false) {
				g1 = i-(int)(Math.random()*5); 
				g2 = i+(int)(Math.random()*5);
				if(g1<2) {
					g1 = 2;
				}
				if(g2>12) {
					g2 = 12;
				}
			}
			p1 = "It is between " + g1 +" and "+ g2 +".";
			window.drawString( p1, 600, 175 );

			if(T== false) {Score-=1;}
			T = true;
		}

		else {
			window.drawString( "This hint costs: 1 point", 600, 175 );

		}
		window.drawString( "HINT 2", 600, 200 );

		if((H2 == true && Score >= 3) || U == true) {
			if(U == false) {
				G1 = (int)(Math.random() * 12) + 1;
				G2 = (int)(Math.random() * 12) + 1;
				while( G1 == i) {
					G1 = (int)(Math.random() * 12) + 1;
				}
				while (G2 == i && G2 == G1) {
					G2 = (int)(Math.random() * 12) + 1;
				}

				int o = (int)(Math.random()*3)+1;

				if(o == 3) {
					j.add(G2);
					j.add(G1);
					j.add(i);
				}

				if(o==2) {
					j.add(G1);
					j.add(i);
					j.add(G2);
				}
				if(o==1) {
					j.add(i);
					j.add(G2);
					j.add(G1);
				}

			}

			p1 = "It is " + j.get(0) +" or "+ j.get(1) +" or "+ j.get(2) +".";
			window.drawString( p1, 600, 225 );
			if(U==false) {
				Score-=3;


			}
			U = true;

		}
		else {
			window.drawString( "This hint costs: 3 points", 600, 225 );
		}
		window.drawString( "HINT 3", 600, 250 );

		if((H3 == true && Score >= 5)|| I == true ) {
			p1 = "It is "+ i+".";
			window.drawString( p1, 600, 275 );
			if(I==false) {Score-=5;} 
			I = true;
		}
		else {
			window.drawString( "This hint costs: 5 points", 600, 275 );

		}
	}

	public void drawSq(Rectangle rect2, Rectangle rect, Graphics2D window) {
		window.setColor( Color.RED );
		window.fill(rect2);
		int r = (int)(Math.random() * 6) + 1;
		dots(r, rect2.x, rect2.y , window);


		window.setColor( Color.RED );
		window.fill( rect );
		r = (int)(Math.random() * 6) + 1;
		dots(r, rect.x, rect.y, window);
	}

	public void drawSq(Rectangle rect2, Rectangle rect, Graphics2D window, int f1, int f2) {
		window.setColor( Color.RED );

		window.setColor( Color.RED );
		window.fill(rect2);
		dots(f1, rect2.x, rect2.y , window);


		window.setColor( Color.RED );
		window.fill( rect );
		dots(f2, rect.x, rect.y, window);
	}

	public void drawDi(Rectangle rect2, Rectangle rect, Graphics2D window) {

		Polygon pol = rota(rect2, window);
		window.setColor( Color.RED );
		window.fillPolygon( pol );
		int r = (int)(Math.random() * 6) + 1;
		Rdots(r, pol.xpoints[0], pol.ypoints[0] , window);


		Polygon pol2 = rota(rect, window);
		window.setColor( Color.RED );
		window.fillPolygon( pol2 );
		r = (int)(Math.random() * 6) + 1;
		Rdots(r, pol2.xpoints[0], pol2.ypoints[0] , window);
	}

	public void dots(int numb, int x, int y, Graphics window) {

		window.setColor(Color.WHITE);
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

		window.setColor(Color.WHITE);
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

	public void run()
	{


		try
		{
			while( true )
			{	
				if (System.currentTimeMillis() % 60 == 0) repaint();
			}
		}
		catch( Exception e )
		{

		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("My Drawing");
		Game canvas = new Game();
		frame.setSize(850, 650);
		frame.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		canvas.run();
	}

	@Override
	public void keyTyped(KeyEvent e) {


	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
