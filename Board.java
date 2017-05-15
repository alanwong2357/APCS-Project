import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Board extends JPanel {

    public static void main(String[] args){
        JFrame board = new JFrame();
        board.setSize(800,800);
        board.getContentPane().add(new Board());
        board.setLocationRelativeTo(null);
        board.setBackground(Color.LIGHT_GRAY);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
	}
	public void paint(Graphics g) {
        g.fillRect(100, 100, 400, 400);
        for(int i = 100; i <= 400; i+=100){
            for(int j = 100; j <= 400; j+=100){
                g.clearRect(i, j, 50, 50);
            }
        }
        
        for(int i = 150; i <= 450; i+=100){
            for(int j = 150; j <= 450; j+=100){
                g.clearRect(i, j, 50, 50);
            }
        }
    }
}