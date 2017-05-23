import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class CheckersGame extends JPanel implements ActionListener, MouseListener{
	public static int[][] baseGameData = new int[8][8]; 
	public static int[][] gameData = new int[8][8];
	public static final int EMPTY=0, RED=1, RKING=3, BLACK=2,WKING=4;
	public static JFrame frame;

	public static void main(String[] args) {
		CheckersGame ch= new CheckersGame();
		ch.drawBoard();
		ch.initialize();
		ch.revalidate();
    	ch.repaint();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				System.out.print(gameData[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {
		// if(e.getSource().equals(button)){
		// 	System.out.println("The JButton was clicked...");
		// }
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void actionPerformed(ActionEvent e) {}

	public void drawBoard() {
		JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.getContentPane().add(new CheckersGame());
        //frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addMouseListener(this);
	}
	public void initialize() {
		for(int col=0;col<8;col+=2) {
			gameData[5][col]=RED;
			gameData[7][col]=RED;
		}
		for(int col=1;col<8;col+=2) {
			gameData[6][col]=RED;
		}
		for(int col=1;col<8;col+=2) {
			gameData[0][col] = BLACK;
			gameData[2][col] = BLACK;
		}
		for(int col=0; col < (8); col+=2) {
			gameData[1][col] = BLACK;
		}
	}
	public static void Piece(int col,int row,Graphics g, Color color) {
		g.setColor(color);
		g.fillOval(col*75+102,row*75+102,70,70);
		if(color.equals(BLACK)) {
			System.out.println("work");
			g.setColor(Color.WHITE); 
			g.drawOval(col*75+102,row*75+102,67,67);
		}
	}
	// public static void movePiece(Piece piece,String direction) {

	// }
	public void makeMove(int col, int row, int storedCol, int storedRow){
		int x = gameData[storedCol][storedRow];
		gameData[col][row] = x;
		gameData[storedCol][storedRow] = EMPTY; 
	}
	// public void mousePressed(java.awt.event.MouseEvent e) {
	// 	int col=e.getx()/75;
	// 	int row=e.gety()/75;
	// }
	public void paint(Graphics g) {
		//super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
        g.fillRect(100, 100, 600, 600);
        for(int i = 100; i <= 600; i+=150){
            for(int j = 100; j <= 600; j+=150){
                g.clearRect(i, j, 75, 75);
            }
        }
        
        for(int i = 175; i <= 675; i+=150) {
            for(int j = 175; j <= 675; j+=150){
                g.clearRect(i, j, 75, 75);
            }
        }
        for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(gameData[j][i]==2) {
        			Piece(i,j,g,Color.BLACK);
        		}
        	}
        }
        for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(gameData[j][i]==1) {
        			Piece(i,j,g,Color.RED);
        		}
        	}
        }
  //       for(int j=0;j<3;j+=2) {
		// 	for(int i=1;i<=8;i+=2) {
		// 		Piece(i,j,g,Color.BLACK);
		// 	}
		// }
		// for(int i=0;i<8;i+=2) {
		// 	Piece(i,1,g,Color.BLACK);
		// }
		// for(int j=5;j<8;j+=2) {
		// 	for(int i=0;i<8;i+=2) {
		// 		Piece(i,j,g,Color.RED);
		// 	}
		// }
		// for(int i=1;i<8;i+=2) {
		// 	Piece(i,6,g,Color.RED);
		// }
		g.setColor(Color.BLACK);
	}
}