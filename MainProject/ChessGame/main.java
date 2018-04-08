import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class main{

	private JFrame frame;
	private DrawPanel drawPanel;
	private chessBoard board;
	private boolean isRunning = true;
	private int spaceSize = 50;

	public static void main(String[] args){
		new main();
	}

	public main(){
		//Make the jframe
		makeJFrame();
		//
		board = new chessBoard(spaceSize);
		//Call start game function
		startGame();
	}

	public void startGame(){
		//Create infinite loop
		while(isRunning){
			try{
				Thread.sleep(5);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public void makeJFrame(){
		//set frame to new JFrame object setting title in process
		frame = new JFrame("Test BG");
		//set program to exit on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set drawpanel to new drawPanel object
		drawPanel = new DrawPanel();
		//set the prefered size to be height and width of level loaded
		drawPanel.setPreferredSize(new Dimension((8 * spaceSize) , (8 * spaceSize)));

		//add draw panel to jframe
		frame.getContentPane().add(drawPanel);

		//pack everything so JFrame fits jpanel
		frame.pack();

		//set visible
		frame.setVisible(true);
		//set resizable to false
		frame.setResizable(false);
		//set location of JFrame
		frame.setLocation(375, 55);
		//call repaint on frame
		frame.repaint();

		drawPanel.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				board.highlightBox(x, y);
			}
			public void mouseClicked(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
		});
	}

	class DrawPanel extends JPanel{
		public void paintComponent(Graphics g){
			//Super the paintComponent method
			super.paintComponent(g);
			//Draw the squares
			board.drawBoard(g);
		}
	}

}