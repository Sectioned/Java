import java.util.*;
import java.awt.*; 
import javax.swing.*;

public class Stream{

	int totalSymbols = (int) Math.round(3 + (Math.random() * 10));
	int speed = 1;
	Symbol[] symbolArray = new Symbol[totalSymbols];
	JLabel[] labels = new JLabel[totalSymbols];

	public void generateSymbols(int column, int FONT_SIZE){
		int positioning = ((int)Math.round(Math.random() * 25));
		for(int i = 0; i < totalSymbols; i++){
			Symbol singleSymbol = new Symbol();
			singleSymbol.setPosition(column, i, FONT_SIZE, positioning);//((int)Math.round(Math.random() * 10))
			symbolArray[i] = singleSymbol;
		}
	}

	public void render(JPanel panel, int FONT_SIZE){
		for(int i = 0; i < totalSymbols; i++){
			labels[i] = symbolArray[i].labelSetUp(panel);
			symbolArray[i].rain(labels[i], FONT_SIZE);
		}
	}

}