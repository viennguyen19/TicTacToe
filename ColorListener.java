import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorListener extends MouseAdapter {
        private PixelPanel panel;
        private static boolean signal; 
        public ColorListener(PixelPanel panel) {
            this.panel = panel;
            this.signal = true;
        }

        //@Override
        public void mousePressed(MouseEvent event) {
        	if(panel.getFlag() != true)
        	{        		
                if (event.getButton() == MouseEvent.BUTTON1) {
                	if(signal)
                	{
                		panel.setBackgroundColor(Color.CYAN);
                	}
                	else
                	{
                		panel.setBackgroundColor(Color.ORANGE);
                	}
                	panel.setString(signal);
                	panel.setStringColor(Color.black);
                	panel.setFlag();                	
                	this.signal = !this.signal;
                	boolean check = panel.detectWinner() || panel.detectWinnerCol()
                					||panel.detectWinnerAntiDiagonal() || panel.detectWinnerDiagonal();                	
                	if(check)
                	{panel.resetPanel();}
                    panel.repaint();
                }
        	}
        }
}

