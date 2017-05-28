import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CheckersGame extends JPanel implements ActionListener, MouseListener{
	public static int[][] baseGameData = new int[8][8]; 
	public static int[][] boardState = new int[8][8];
	public static final int EMPTY=0, RED=1, RKING=3, BLACK=2,WKING=4;
	public static JFrame frame;
	private JButton[][] button=new JButton[8][8];
	public static CheckersGame ch= new CheckersGame();
	private int mouseRow=9;
	private int mouseCol=9;

	public static void main(String[] args) {
		//CheckersGame ch= new CheckersGame();
		ch.drawBoard();
		ch.initialize();
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				System.out.print(boardState[i][j]+" ");
			}
			System.out.println();
		}

		//ch.revalidate();
    	ch.repaint();
    	ch.movePiece(5,2,4,3);
 		System.out.println();
	}
		public void drawBoard() {
		JFrame frame = new JFrame();
		frame.setContentPane(ch);
		//frame.getContentPane().add(new CheckersGame());
        frame.setSize(800,800);
        
        //frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        repaint();
       // revalidate();
        frame.addMouseListener(this);
  //       for(int i=0;i<8;i++) {
		// 	for(int j=0;j<8;j++) {
		// 		button[i][j]=new JButton();
		// 		button[i][j].addActionListener(this);
		// 	}
		// }
	}
	public void initialize() {
		for(int col=0;col<8;col+=2) {
			boardState[5][col]=RED;
			boardState[7][col]=RED;
		}
		for(int col=1;col<8;col+=2) {
			boardState[6][col]=RED;
		}
		for(int col=1;col<8;col+=2) {
			boardState[0][col] = BLACK;
			boardState[2][col] = BLACK;
		}
		for(int col=0; col < (8); col+=2) {
			boardState[1][col] = BLACK;
		}
	}
	public void movePiece(int row,int col,int finalRow,int finalCol) {
		boardState[finalRow][finalCol]=boardState[row][col];
    	boardState[row][col]=0;
    	//ch.repaint();
    	System.out.println();
  	 for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				System.out.print(boardState[i][j]+" ");
			}
			System.out.println();
		}
	}
	// public void addButton(JFrame frame) {
	// 	for(int i=0;i<8;i++) {
	// 		for(int j=0;j<8;j++) {
	// 			button[j][i]=new JButton((j+1)+", "+(j+1));
	// 			frame.add(button[j][i]);
	// 		}
	// 	}
	// }
	public void removePiece(Graphics g, int row,int col) {
		g.setColor(Color.BLACK);
		g.fillOval(col*75+102,row*75+102,70,70);
	}
	public void mousePressed(MouseEvent e) {
		//System.out.println("x: "+e.getX()+"  y: "+e.getY());
		int col=(e.getX()-102)/75;
		int row=(e.getY()-130)/75;
		if(row<8 && row>=0 && col>=0 && col<8 && ((col%2==0 && row%2!=0) || (col%2!=0 && row%2==0))) {
			if(mouseRow==9 && mouseCol==9) {
				mouseRow=row;
				mouseCol=col;
			} else if(mouseRow!=9 && mouseCol!=9) {
				int prev=boardState[mouseRow][mouseCol];
				System.out.println("mouse: "+mouseRow+", "+ mouseCol);
				System.out.println("aft: "+row+", "+ col);
				if(isLegal(prev,mouseRow,mouseCol,row,col)) {
					boardState[mouseRow][mouseCol]=0;
					boardState[row][col]=prev;
					mouseRow=9;
					mouseCol=9;
					ch.repaint();
				}
				// s
			}
		}
		// if(row<8 && row>=0 && col>=0 && col<8 && boardState[row][col]!=0) {
		// 	System.out.println(row+","+col);
		// 	boardState[row][col]=0;
		// 	//ch.removePiece(g,row,col);
		// 	ch.repaint();
		// 	//validate();
		// 	setVisible(true);

		// }
		// if(row<8 && row>=0 && col>=0 && col<8 && boardState[row][col]==0) {
		// 	System.out.println(row+","+col);
		// 	 boardState[row][col]=2;
		// 	 ch.repaint();
		// 	 setVisible(true);

		// }
		// e.getSource().color=Color.BLUE;
		// e.this.repaint();
	}
	public void mouseClicked(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void actionPerformed(ActionEvent e) {}

	public boolean isLegal(int piece,int mouseRow,int mouseCol,int row,int col) {
		if(piece==2) {
			if(mouseRow < row && boardState[row][col]==0) {
				return true;
			}
		} else if(piece==1  && boardState[row][col]==0) {
			if(mouseRow > row) {
				return true;
			}
		} else if(piece==3 || piece==4 && boardState[row][col]==0) {
			return true;
		}
		return false;
	}
	// public boolean isLegal(int row,int col,int finalRow,int finalCol) {
	// 	if(boardState[finalRow][finalCol]==0) {
	//		return true;	
	//	} else if((boardState[finalRow][finalCol]==1 || boardState[finalRow][finalCol]==2) && boardState[finalRow+finalRow-row][finalCol+1]==0) {
			// return true;
	//	} else if()
	//		
	// }

	// public void update(Graphics g) {
	// 	paint(g);
	// }
	public static void Piece(int col,int row,Graphics g, Color color) {
		g.setColor(color);
		g.fillOval(col*75+102,row*75+102,70,70);
		if(color.equals(Color.BLACK)) {
			g.setColor(Color.WHITE); 
			g.drawOval(col*75+102,row*75+102,70,70);
		}
	}

	public void makeMove(int row, int col, int storedRow, int storedCol){
		int x = boardState[storedCol][storedRow];
		boardState[col][row] = x;
		boardState[storedCol][storedRow] = EMPTY; 
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
        		if(boardState[i][j]==2) {
        			Piece(j,i,g,Color.BLACK);
        		}
        	}
        }
        for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(boardState[i][j]==1) {
        			Piece(j,i,g,Color.RED);
        		}
        	}
        }
		g.setColor(Color.BLACK);
		//highlight square implementation
		// if(selectedRow<=0) {
		// 	g.setColor(Color.BLUE);
		// 	y=selectedRow*20;
		// 	x=selectedCol*20;
		//	g.drawRect(x,y,70,70);
		}
	
}