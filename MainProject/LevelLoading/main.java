import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.PointerInfo;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class main{

	private JFrame frame;
	private levelFrame paintLevel;
	private DrawPanel drawPanel;
	private player mainPlayer;
	private platforms allPlatforms;
	private int blockSize = 10;

	public static void main(String[] args){
		new main();
	}

	public main(){
		paintLevel = new levelFrame(blockSize);
		makeJFrame();
		mainPlayer = new player(frame);
		allPlatforms = new platforms(paintLevel.getMap(), blockSize);
		startGame();
	}

	public void startGame(){
		while(true){
			mainPlayer.moverPlayer();
			frame.repaint();
			mainPlayer.playerOnPlatform(allPlatforms.getPlatforms(), 190);
			try{
				Thread.sleep(5);
			}catch(Exception E){System.out.println(E);}
		}
	}

	public void makeJFrame(){
		frame = new JFrame("Test BG");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();
		drawPanel.setPreferredSize(new Dimension(paintLevel.getWindowWidth() , paintLevel.getWindowHeight()));

		frame.getContentPane().add(drawPanel);

		frame.pack();

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocation(375, 55);
	}

	class DrawPanel extends JPanel{
		public void paintComponent(Graphics g){
			paintLevel.paintLevel(g);
			mainPlayer.drawPlayer(g, blockSize);
		}
	}

}