import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PixelPanel extends JPanel {
        private static final int PIXEL_SIZE = 20;
        private boolean flag;
        private Color backgroundColor;
        private String str;
        private Color strColor;
        private int width, height;
        private int thisStr[];
        private int id;        
        private PixelPanel[][] mypanel;
        private int[] winner;

        public PixelPanel(int[] mylist, int newID, int width, int height,  PixelPanel[][] newpanel) 
        {
            this.backgroundColor = Color.gray;
            this.strColor = Color.gray;
            this.str = "";
            this.flag = false;
            this.width = width; 
            this.height = height;
            mylist[newID] = -1;
            this.thisStr = mylist;
            this.id = newID;
            this.mypanel = newpanel;
            this.winner = new int[5];
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
        }

        // Set color for winner
        public void resetPanel() 
        {
        	int rowNow = this.id/this.width;
    		int colNow = this.id%this.width;
        	for(int k=0; k<5; k++)
        	{
        		rowNow = this.winner[k]/this.width;
        		colNow = this.winner[k]%this.width;
        		this.mypanel[rowNow][colNow].setBackgroundColor(Color.GREEN);
            	this.mypanel[rowNow][colNow].repaint();
        	} 
        }
        
        // Get background color for pixel panel
        public Color getBackgroundColor() {
            return backgroundColor;
        }

        // Set background color for pixel panel
        public void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }
        
        // Set default string and value 
        public void setStringDefault()
        {
        	this.str = "";
        	this.thisStr[this.id] = -1;        
        }
        
        // Set which string to draw
        public void setString(boolean signal) {           
            if(signal)
            {
            	this.str = "X";
            	
            	this.thisStr[this.id] = 1;
            }
            else
            {
            	this.str = "O";
            	
            	this.thisStr[this.id] = 0;
            }            
        }
        
        // For draw string
        public String getString() {
            return str;
        }    

        // For set string color
        public void setStringColor(Color mystrColor) {
            this.strColor = mystrColor ;
        }
        
        // For get string color
        public Color getStringColor() {
            return strColor;
        }
        
        // Get flag to know when to change color
        public boolean getFlag() {
            return flag;
        }
        
        // Set flag to prevent change color if revisit
        public void setFlag() {
            this.flag = true;
        }
        
        // Reset flag for new game
        public void resetFlag() {
            this.flag = false;
        }
        

        /*public void printStr()
        {
        	for(int i=0; i < this.width*this.height;i++)
            System.out.print(" " + this.thisStr[i]);
        	System.out.println();
        }*/
    	

        // Detect winner for Row
        public boolean detectWinner()
        {
        	int rowNow = this.id/this.width;
    		int colNow = this.id%this.width;    		
    		int start = (this.id - 4)/this.width < rowNow ? (this.id - colNow) : (this.id - 4);
    		int end = (this.id + 4)/this.width > rowNow ? (this.id + this.width - colNow - 1) : (this.id + 4);
    		start = start < 0 ? 0 : start;
    		int i = 0;
    		int j = this.id-1 < start ? start : this.id-1;
    		System.out.println("Case: " + start +" "+ end  +" "+ j + " "+ this.id);
    		System.out.println("Case1: " + rowNow +" "+ colNow);
    		int temp = this.thisStr[this.id];
    		
    		this.winner[4] = this.id;
    		
            while(i<5 && j >= start)
            {           	
                if(this.thisStr[j] == temp)
            	{
                	this.winner[i] = j;
            		i++;
            		j--;            		
            	}
            	else
            	{
            		start = j;
            		j = this.id+1 > end ? end : this.id+1;
            		while(i<5 && j <= end)
            		{
            			if(this.thisStr[j] == temp)
                    	{
                        	this.winner[i] = j;
                    		i++;
                    		j++;                    		
                    	}
                    	else
                    	{
                    		break;
                    	}
            		}
            		if(i < 4)
            		{
            			return false;
            		}
            		else
            		{
            			System.out.println("true row" + temp );            			
            			return true;
            		}
            	}
            }

            System.out.println((true && i==4)+ " row " + temp );
            return true && i==4;
        }
        
        /*-------------------------------------------------------------------------*/
        // Detect winner for Column
        public boolean detectWinnerCol()
        {
        	int rowNow = this.id/this.width;

    		int start = rowNow >= 4 ? (this.id - (this.width*4)) : (this.id - (rowNow*this.width));
    		int end = rowNow+4 <= this.height-1 ? (this.id + (this.width*4)) : (this.id + (this.height-1-rowNow)*this.width);
    		int i = 0;
    		int j = this.id-this.width;
    		int temp = this.thisStr[this.id];

    		this.winner[4] = this.id;
    		while(i < 5 && j >= start)
    		{
                if(this.thisStr[j] == temp)
            	{
                	System.out.println("this i "+ i + j);
                	this.winner[i] = j;
            		i++;
            		
            		j = j - this.width;
            	}
                else
                {
                	break;
                }
    		}

    		if(i >= 4) 
    		{
    			System.out.println("true col " + temp );
    			return true;
    		}
    		
    		j = this.id+this.width;
    		while(i < 5 && j <= end)
    		{
                if(this.thisStr[j] == temp)
            	{
                	System.out.println("this i " + j);
                	this.winner[i] = j;
            		i++;
            		j += this.width;
            	}
                else
                {
                	break;
                }
    		}

    		if(i >= 4) 
    		{
    			System.out.println("true col " + temp );
    			return true;
    		}

    		return false;
        }
        /*-------------------------------------------------------------------------*/

        
        // Detect winner for Diagonal
        public boolean detectWinnerDiagonal()
        {
        	int rowNow = this.id/this.width;

    		int minus = this.width+1;
    		int start = rowNow >= 4 ? (this.id - (minus*4)) : (this.id - (rowNow*minus));
    		int end = rowNow+4 <= this.height-1 ? (this.id + (minus*4)) : (this.id + (this.height-1-rowNow)*minus);
    		start = start >= 0 ? start : start + minus;
    		end = end <= this.width*this.height-1 ? end : end-minus;
    		int i = 0;
    		int j = this.id-this.width-1;
    		int temp = this.thisStr[this.id];

    		while(i < 5 && j >= start)
    		{

                if(this.thisStr[j] == temp)
            	{
                	this.winner[i] = j;
            		i++;
            		j = j - minus;
            	}
                else
                {
                	break;
                }
    		}

    		if(i >= 4) 
    		{
    			System.out.println("true dia " + temp );
    			return true;
    		}
    		
    		j = this.id+this.width+1;
    		while(i < 5 && j <= end)
    		{
                if(this.thisStr[j] == temp)
            	{
                	this.winner[i] = j;
            		i++;
            		j += minus;
            	}
                else
                {
                	break;
                }
    		}

    		if(i >= 4) 
    		{
    			System.out.println("true dia " + temp );
    			return true;
    		}

    		return false;
        }
        
        
        // Detect winner for Anti Diagonal
        public boolean detectWinnerAntiDiagonal()
        {
        	int rowNow = this.id/this.width;

    		int minus = this.width-1;
    		int start = rowNow >= 4 ? (this.id - (minus*4)) : (this.id - (rowNow*minus));
    		int end = rowNow+4 <= this.height-1 ? (this.id + (minus*4)) : (this.id + (this.height-1-rowNow)*minus);
    		start = start >= 0 ? start : start + minus;
    		end = end <= this.width*this.height-1 ? end : end-minus;
    		int i = 0;
    		int j = this.id-minus;
    		int temp = this.thisStr[this.id];

    		while(i < 5 && j >= start)
    		{

                if(this.thisStr[j] == temp)
            	{
                	this.winner[i] = j;
            		i++;
            		j = j - minus;
            	}
                else
                {
                	break;
                }
    		}

    		if(i >= 4) 
    		{
    			System.out.println("true antidia " + temp );
    			return true;
    		}
    		
    		j = this.id+minus;
    		while(i < 5 && j <= end)
    		{
                if(this.thisStr[j] == temp)
            	{
                	this.winner[i] = j;
            		i++;
            		j += minus;
            	}
                else
                {
                	break;
                }
    		}
    		if(i >= 4) 
    		{
    			return true;
    		}
    		return false;
        }
        
        
        // Reset the whole grid
        public void resetTable()
        {
        	for(int i=0; i< this.height*this.width; i++)
        	{
        		this.thisStr[i] = -1;
        	}
        }

    	// Paint pixel panel
        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent(g);

            g.setColor(getBackgroundColor());
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setFont(new Font("Garamond", Font.PLAIN, PIXEL_SIZE/2));
            g.setColor(getStringColor());

            g.drawString(getString(), PIXEL_SIZE-13, PIXEL_SIZE-7);
        }
    }