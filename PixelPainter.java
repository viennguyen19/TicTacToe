import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PixelPainter implements Runnable {
	private JFrame frame;
	private PixelPanel grid[][];
    public static void main(String[] args) {
        EventQueue.invokeLater(new PixelPainter());
    }

    public PixelPanel[][] getGrid()
    {
    	return this.grid;
    }
    
    @Override
    public void run() {
        initGUI();
    }

    public void initGUI() {    	
        frame = new JFrame("Pixel Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
		JPanel panel0 = new JPanel();
		JButton reset = new JButton("Reset");
		panel0.add(reset);
		frame.add(panel0,BorderLayout.PAGE_END);	
		
        JPanel panel1 = createPixels();  

        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	 System.out.println("This is");
            }
         });

        frame.add(panel1);        

		reset.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        for(PixelPanel[] buttons: getGrid())
		        {
		            for(PixelPanel b:buttons)
		            {
		            	b.setBackgroundColor(Color.GRAY);
		            	b.setStringDefault();
		            	b.resetFlag();

		            	b.repaint();
		            }
		        }
		    }
		});

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createPixels() 
    {
        int width = 30;
        int height = 20;
        grid = new PixelPanel[height][width];
        JPanel panel = new JPanel();

        int myListStr[] = new int[width*height];

        panel.setLayout(new GridLayout(height, width, 0, 0));

        for (int row = 0; row < height; row++) 
        {
            for (int column = 0; column < width; column++) 
            {            	
                PixelPanel pixelPanel = new PixelPanel(myListStr, width*row+column, width, height, grid);
                pixelPanel.addMouseListener(new ColorListener(pixelPanel));
                panel.add(pixelPanel);                
                grid[row][column] = pixelPanel;                
            }
        }     
        return panel;
    }
}
