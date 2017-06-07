import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CheckersGame extends JPanel implements MouseListener{
	public static int[][] highlightBoard = new int[8][8]; 
	public static int[][] boardState = new int[8][8];
	public static final int EMPTY=0, BLACK=1, RED=2, BKING=3, RKING=4;
	public static CheckersGame ch= new CheckersGame();
	private int mouseRow=9;
	private int mouseCol=9;
	private int turn=2;
	JLabel turnIndicator;
	private JLabel jlabel;
	static BufferedImage king=null;

	public static void main(String[] args) throws IOException {
		king = ImageIO.read(new File("king.png"));
		ch.drawBoard();
		ch.initialize();
	}
	public CheckersGame() {
        setSize(1000,800);	
        addMouseListener(this);
        repaint();
       	//revalidate();
       	setVisible(true);
	}
	// Creates Jframe and adds mouselistener
	public void drawBoard() {
		JFrame frame = new JFrame("Checkers");
		frame.setContentPane(ch);
		frame.setLayout(null);
        frame.setSize(1000,800);	
       	frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ch.repaint();
        frame.addMouseListener(this);
        ch.revalidate();
	}
	// Creates checker pieces in their starting positions
	public void initialize() {
		for(int col=0;col<8;col+=2) {
			boardState[5][col]=BLACK;
			boardState[7][col]=BLACK;
		}
		for(int col=1;col<8;col+=2) {
			boardState[6][col]=BLACK;
		}
		for(int col=1;col<8;col+=2) {
			boardState[0][col] = RED;
			boardState[2][col] = RED;
		}
		for(int col=0; col < (8); col+=2) {
			boardState[1][col] = RED;
		}
	}

	// Mouselistener that records the coordinates of mousepress and executes moves 
	public void mousePressed(MouseEvent e) {
		//System.out.println("x: "+e.getX()+"  y: "+e.getY());
		int col=(e.getX()-102)/75;
		int row=(e.getY()-100)/75;
		//System.out.println("row: "+row+" col"+col);
		//System.out.println();
		if(row<8 && row>=0 && col>=0 && col<8 && ((col%2==0 && row%2!=0) || (col%2!=0 && row%2==0))) {
			if(mouseRow==9 && mouseCol==9) {
				if(turn==2 && (boardState[row][col]==2 || boardState[row][col]==4)) {
					mouseRow=row;
					mouseCol=col;
					int piece=boardState[row][col];
					showMoves(piece,row,col);
				} else if(turn==1 && (boardState[row][col]==1 || boardState[row][col]==3)) {
					mouseRow=row;
					mouseCol=col;
					int piece=boardState[row][col];
					showMoves(piece,row,col);
				}
				
			} else if(mouseRow!=9 && mouseCol!=9) {
				int prev=boardState[mouseRow][mouseCol];
				// System.out.println("mouse: "+mouseRow+", "+ mouseCol);
				// System.out.println("aft: "+row+", "+ col);
				if((mouseRow==row && mouseCol==col) || highlightBoard[row][col]==0) {
					mouseRow=9;
					mouseCol=9;
				}
				if((mouseRow!=row || mouseCol!=col) && highlightBoard[row][col]==1) { 
					if(Math.abs(col-mouseCol)>1) {
						boardState[mouseRow+diff(mouseRow,row)][mouseCol+diff(mouseCol,col)]=0;
					} 
					if(boardState[mouseRow][mouseCol]==2 && row==7) {
						boardState[row][col]=4;
						repaint();
					}else if(boardState[mouseRow][mouseCol]==1 && row==0) {
						boardState[row][col]=3;
						repaint();
					} else {
						boardState[row][col]=prev;
					}
					boardState[mouseRow][mouseCol]=0;
					mouseRow=9;
					mouseCol=9;
					switchTurn();
				} 
				for(int i=0;i<8;i++) {
			       	for(int j=0;j<8;j++) {
			       		highlightBoard[i][j]=0;
			       	}
		        }
			    repaint();
			}
		}
	}
	// Creates destination square for jumping a piece
	public static int diff(int before,int after) {
		if(before-after<0) {
			return 1;
		} else if(before-after>0) {
			return -1;
		}
		return 0;
	}
	public void switchTurn() {
		if(turn==1) {
			turn=2;
		} else if(turn==2) {
			turn=1;
		}
	}
	public void mouseClicked(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void actionPerformed(ActionEvent e) {}

	// Highlights available moves for selected piece
	public void showMoves(int piece,int row,int col) {
		if(piece==1 && row>0) {
			if(col<7) {
				if(boardState[row-1][col+1]==0) {
					highlightBoard[row-1][col+1]=1;
				}
			}
			if(col>0) {
				if(boardState[row-1][col-1]==0) {
					highlightBoard[row-1][col-1]=1;
				}
			}
			if(row>1 && col>1) {
				if((boardState[row-1][col-1]==2 || boardState[row-1][col-1]==4) && boardState[row-2][col-2]==0) {
					highlightBoard[row-2][col-2]=1;
				}
			}
			if(row>1 && col<6) {
				if((boardState[row-1][col+1]==2 || boardState[row-1][col+1]==4) && boardState[row-2][col+2]==0) {
					highlightBoard[row-2][col+2]=1;
				}
			}
		} else if(piece==2 && row<7) {
			if(col<7) {
				if(boardState[row+1][col+1]==0) {
					highlightBoard[row+1][col+1]=1;
				}
			}
			if(col>0) {
				if(boardState[row+1][col-1]==0) {
					highlightBoard[row+1][col-1]=1;
				}
			}
			if(row<6 &&col>1) {
				if((boardState[row+1][col-1]==1 || boardState[row+1][col-1]==3) && boardState[row+2][col-2]==0) {
					highlightBoard[row+2][col-2]=1;
				}
			}
			if(row<6 && col<6) {
				if((boardState[row+1][col+1]==1 || boardState[row+1][col+1]==3) && boardState[row+2][col+2]==0) {
					highlightBoard[row+2][col+2]=1;
				}
			}
		} else if(piece==3 || piece==4) {
			if(row<7 && col<7 && boardState[row+1][col+1]==0) {
				highlightBoard[row+1][col+1]=1;
			}
			if(row>0 && col<7 && boardState[row-1][col+1]==0) {
				highlightBoard[row-1][col+1]=1;
			}
			if(row<7 && col>0 && boardState[row+1][col-1]==0) {
				highlightBoard[row+1][col-1]=1;
			}
			if(row>0 && col>0 && boardState[row-1][col-1]==0) {
				highlightBoard[row-1][col-1]=1;
			}
			if(piece==3) {
				if(row>1 && col>1 && (boardState[row-1][col-1]==2 || boardState[row-1][col-1]==4) && boardState[row-2][col-2]==0) {
					highlightBoard[row-2][col-2]=1;
				}
				if(row>1 && col<6 && (boardState[row-1][col+1]==2 || boardState[row-1][col+1]==4) && boardState[row-2][col+2]==0) {
					highlightBoard[row-2][col+2]=1;
				}
				if(row<6 && col>1 && (boardState[row+1][col-1]==2 || boardState[row+1][col-1]==4) && boardState[row+2][col-2]==0) {
					highlightBoard[row+2][col-2]=1;
				}
				if(row<6 && col<6 && (boardState[row+1][col+1]==2 || boardState[row+1][col+1]==4) && boardState[row+2][col+2]==0) {
					highlightBoard[row+2][col+2]=1;
				}
			} else if(piece==4) {
				if(row>1 && col>1 && (boardState[row-1][col-1]==1 || boardState[row-1][col-1]==3) && boardState[row-2][col-2]==0) {
					highlightBoard[row-2][col-2]=1;
				}
				if(row>1 && col<6 && (boardState[row-1][col+1]==1 || boardState[row-1][col+1]==3) && boardState[row-2][col+2]==0) {
					highlightBoard[row-2][col+2]=1;
				}
				if(row<6 && col>1 && (boardState[row+1][col-1]==1 || boardState[row+1][col-1]==3) && boardState[row+2][col-2]==0) {
					highlightBoard[row+2][col-2]=1;
				}
				if(row<6 && col<6 && (boardState[row+1][col+1]==1 || boardState[row+1][col+1]==3) && boardState[row+2][col+2]==0) {
					highlightBoard[row+2][col+2]=1;
				}
			}
		}
		repaint();
	}
	public void gameOver(Graphics g) {
		String text = " GAME OVER ";
	}
	// Draws Checker Pieces
	public static void Piece(int piece,int col,int row,Graphics g, Color color) {
		g.setColor(color);
		g.fillOval(col*75+102,row*75+102,70,70);
		if(color.equals(Color.BLACK)) {
			g.setColor(Color.WHITE); 
			g.drawOval(col*75+102,row*75+102,70,70);
		}
		if(piece==3 || piece==4) {
			g.drawImage(king,col*75+107,row*75+105,60,60,null);
		}
	}

	public void paint(Graphics g) {
		// paints squares
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
        // paints checker pieces
        for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(boardState[i][j]==2) {
        			Piece(2,j,i,g,Color.BLACK);
        		} else if(boardState[i][j]==4) {
        			Piece(4,j,i,g,Color.BLACK);
        		}
        	}
        }
        for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(boardState[i][j]==1) {
        			Piece(1,j,i,g,Color.RED);
        		} else if(boardState[i][j]==3) {
        			Piece(3,j,i,g,Color.RED);
        		}
        	}
        }
        // highlight moves for piece selected
		g.setColor(Color.CYAN);
		for(int i=0;i<8;i++) {
        	for(int j=0;j<8;j++) {
        		if(highlightBoard[i][j]==1) {
        			g.fillRect(j*75+100,i*75+100,76,76);
        		}
        	}
        }
	}
	public boolean isLegal(int piece,int mouseRow,int mouseCol,int row,int col) {
		if(piece==2) {
			if(mouseRow < row && boardState[row][col]==0) {
				if(col>1 && col<6 && boardState[mouseRow+row-mouseRow][mouseCol+col-mouseCol]==1 && boardState[mouseRow+row-mouseRow][mouseCol+col-mouseCol]==3) {
					boardState[row-mouseRow][col-mouseCol]=1;
				}
				return true;
			} 
		} else if(piece==1  && boardState[row][col]==0) {
			if(mouseRow > row) {
				if(col>1 && col<6 && boardState[mouseRow+row-mouseRow][mouseCol+col-mouseCol]==2 && boardState[mouseRow+row-mouseRow][mouseCol+col-mouseCol]==4) {
					boardState[row-mouseRow][col-mouseCol]=1;
				}
				return true;
			}
		} else if(piece==3 || piece==4 && boardState[row][col]==0) {
			return true;
		}
		return false;
	}
}