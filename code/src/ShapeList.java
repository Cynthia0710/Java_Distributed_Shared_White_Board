import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.awt.BasicStroke;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;








public class ShapeList implements Serializable{
    public String shapeName;
    public String shapeText="";
    public Color shapeColor;
    public int shapeInitialX,shapeInitialY, shapeFinalX, shapeFinalY,shapeWidth;
    //private WhiteBoardGUI window;
    public boolean isFill=false;
    public int shapeFrameWidth = 1;
    public int shapeFontWidth =10;
    public String shapeStyle = "TimesRoman";
    
    public ShapeList(int x1, int y1, int x2, int y2, String name, Color color,int width, String text,int FrameWidth,int FontWidth,String Style) {
    	shapeInitialX = x1;
    	shapeInitialY = y1;
    	shapeFinalX = x2;
    	shapeFinalY = y2;
    	shapeName = name;
    	shapeColor = color;
    	shapeWidth = width;
    	shapeText = text;
    	shapeFrameWidth = FrameWidth;
    	shapeFontWidth = FontWidth;
    	shapeStyle = Style;
    	
    }
    public boolean containLocation(int x,int y) {
    	switch(shapeName) {
    	case "line":
    		try {
    			if((y-this.shapeInitialY)/(this.shapeFinalY-this.shapeInitialY)==(x-this.shapeInitialX)/(this.shapeFinalX-this.shapeInitialX))
        			return true;
        		else
        			return false;
    		}
    		catch(Exception e){
    			
    		}
    		
    		
    	case "circle":
    		if((y<=this.shapeFinalY && y>=this.shapeInitialY)&&(x<=this.shapeFinalX && x>=this.shapeInitialX))
    			return true;
    		else
    			return false;
    	case "oval":
    		if((y<=this.shapeFinalY && y>=this.shapeInitialY)&&(x<=this.shapeFinalX && x>=this.shapeInitialX))
    			return true;
    		else
    			return false;
    	case "rectangle":
    		//if((y==this.shapeInitialY ||y==this.shapeFinalY)&&(x<=this.shapeFinalX && x>=this.shapeInitialX)||
    		//(x==this.shapeInitialX ||x==this.shapeFinalX)&&(y<=this.shapeFinalY && y>=this.shapeInitialY))
    		if((y<=this.shapeFinalY && y>=this.shapeInitialY)&&(x<=this.shapeFinalX && x>=this.shapeInitialX))
    			return true;
    		else
    			return false;
    		
    	default:
    		return false;
    	}
    }
    public void drawShape(Graphics2D g, WhiteBoardGUI win) {
    	//window = win;
    	
    	switch (shapeName) {
        case "line":
        	g.setStroke(new BasicStroke(shapeWidth));
            g.setColor(shapeColor);
            g.drawLine(shapeInitialX,shapeInitialY, shapeFinalX, shapeFinalY);
            break;

        case "rectangle":
        	g.setStroke(new BasicStroke(shapeWidth));
            g.setColor(shapeColor);
            
        	if(isFill) {
        		g.fillRect(shapeInitialX, shapeInitialY, Math.abs(shapeFinalX - shapeInitialX), Math.abs(shapeFinalY - shapeInitialY));
        	}
        	else {
        		g.drawRect(shapeInitialX, shapeInitialY, Math.abs(shapeFinalX - shapeInitialX), Math.abs(shapeFinalY - shapeInitialY));
        	}
        	
            break;
            
        case "oval":
        	g.setStroke(new BasicStroke(shapeWidth));
            g.setColor(shapeColor);
            
        	if(isFill) {
        		g.fillOval(shapeInitialX, shapeInitialY, Math.abs(shapeFinalX - shapeInitialX), Math.abs(shapeFinalY - shapeInitialY));
        	}
        	else {
        		g.drawOval(shapeInitialX, shapeInitialY, Math.abs(shapeFinalX - shapeInitialX), Math.abs(shapeFinalY - shapeInitialY));
        	}
        	
            break;
            
        case "circle":
        	g.setStroke(new BasicStroke(shapeWidth));
            g.setColor(shapeColor);
            
        	if(isFill) {
        		g.fillOval(shapeInitialX, shapeInitialY, Math.abs(shapeFinalX - shapeInitialX),Math.abs(shapeFinalX - shapeInitialX));
        	}
        	else {
        		g.drawOval(shapeInitialX, shapeInitialY, Math.abs(shapeFinalX - shapeInitialX),Math.abs(shapeFinalX - shapeInitialX));
        	}
        	
            
            break;
            
        case "freehandDrawing":
        	g.setStroke(new BasicStroke(shapeWidth));
            g.setColor(shapeColor);
            synchronized(g) {
            	g.drawLine(shapeInitialX,shapeInitialY, shapeFinalX, shapeFinalY);
        	}
            
            break;
            
        case "erasing":
        	g.setStroke(new BasicStroke(shapeWidth));
            g.setColor(Color.WHITE);
            synchronized(g) {
            	g.drawLine(shapeInitialX,shapeInitialY, shapeFinalX, shapeFinalY);
        	}
            shapeInitialX = shapeFinalX;
            shapeInitialY = shapeFinalY;
            break;
         
        case "text":
        	/*
        	window.getWhiteBoardPanel().setLayout(null);
        	window.textArea = new JTextArea();
        	
        	window.textArea.setBounds(shapeInitialX, shapeInitialY, Math.abs(shapeFinalX - shapeInitialX),
					Math.abs(shapeFinalY - shapeInitialY));
        	window.textArea.setBorder(BorderFactory.createLineBorder(Color.gray, shapeFrameWidth));
        	window.textArea.setFont(new Font(shapeStyle,Font.PLAIN,shapeFontWidth));
        	window.textArea.setVisible(true);
        	window.textArea.setText(shapeText);
        	
        	
        	window.textArea.setEditable(true);
        	window.textArea.setLineWrap(true); 
        	window.textArea.setWrapStyleWord(true); 
        	//window.textArea.setText("test");
			window.getCleanText().add(window.textArea);
			window.getWhiteBoardPanel().add(window.textArea);
			window.getWhiteBoardPanel().updateUI();
			window.textArea.getDocument().addDocumentListener(new Swing_OnValueChanged());
            break;
            */
        	g.setColor(shapeColor);
        	g.setFont(new Font(shapeStyle,Font.PLAIN,shapeFontWidth));
        	g.drawString(shapeText, shapeInitialX, shapeInitialY);
        	break;
            
        
        case "dashedLine":
        	    g.setColor(shapeColor);
            	//System.out.println("this is drawing line");   
            	float[] dash = { 2f, 0f, 2f };
            	BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
            	g.setStroke(bs);
                g.drawLine(shapeInitialX, shapeInitialY, shapeFinalX, shapeFinalY);
                BasicStroke bs1 = new BasicStroke();
                g.setStroke(bs1);
//                ShapeList shaplist1 = new ShapeList(initialX, initialY, finalX, finalY,shape,color,width,"noEdit");
//                listShape.add(shaplist1);
//                sendDraw(shaplist1);
                break;
    			

        case "directionalConnector":
            	//System.out.println("this is drawing line"); 
        	    g.setStroke(new BasicStroke(shapeWidth));
        	    g.setColor(shapeColor);
        	    DrawMethod m = new DrawMethod();
            	m.drawAL(shapeInitialX, shapeInitialY, shapeFinalX, shapeFinalY, g);     
//              ShapeList shaplist1 = new ShapeList(initialX, initialY, finalX, finalY,shape,color,width,"noEdit");
//              listShape.add(shaplist1);
//              sendDraw(shaplist1);
            	break;
        case "link" :
    	        g.setStroke(new BasicStroke(shapeWidth));
    	        g.setColor(shapeColor);
            	//System.out.println("this is drawing line"); 
            	g.drawLine(shapeInitialX-4, shapeInitialY-4, shapeFinalX-4, shapeFinalY-4);  
            	g.drawLine(shapeInitialX+4, shapeInitialY+4, shapeFinalX+4, shapeFinalY+4); 
//              ShapeList shaplist1 = new ShapeList(initialX, initialY, finalX, finalY,shape,color,width,"noEdit");
//              listShape.add(shaplist1);
//              sendDraw(shaplist1);
                break;
        }

    }
    
    public String toString() {
    	return this.shapeName+"*"+this.shapeColor+"*"+shapeInitialX+"*"+shapeInitialY+"*"+shapeFinalX+"*"+shapeFinalY;
    }
    public void setColor(Color color) {
    	this.shapeColor=color;
    }
    public Color getColor() {
    	return this.shapeColor;
    }
    
    public void changeLocation(int x,int y) {
    	this.shapeInitialX+=x;
    	this.shapeFinalX+=x;
    	this.shapeFinalY+=y;
    	this.shapeInitialY+=y;
    }
    
    public void changeSize(int x,int y) {
    	this.shapeFinalX=x;
    	this.shapeFinalY=y;
    }
    
    
}


