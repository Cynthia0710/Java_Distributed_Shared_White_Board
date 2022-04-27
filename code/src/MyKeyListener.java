import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MyKeyListener implements KeyListener{
	public int key;
	ShapeList selectedShape;
	DrawMethod dm;
	WhiteBoardGUI window;
	public MyKeyListener(ShapeList selectedShape, DrawMethod dm,WhiteBoardGUI window) {
		this.selectedShape=selectedShape;
		this.dm=dm;
		this.window=window;
	}
	public void keyPressed(KeyEvent e) {
		
		int action=e.getKeyChar();
		//System.out.print("enter"+action);
		if(dm.shape.equals("text")) {
			switch(action) {
			case 10:
				System.out.print("enter");
		        if(dm.shape.equals("text")) {
		        	
					ShapeList shaplist1 = new ShapeList(dm.initialX, dm.initialY, dm.finalX, dm.finalY, dm.shape, dm.color, dm.width,window.textArea.getText() ,dm.frameWidth,dm.fontWidth,dm.style);
					//cleanText.add(window.textArea);
					dm.listShape.add(shaplist1);
					dm.sendDraw(shaplist1);
					window.getWhiteBoardPanel().remove(window.textArea);
			        window.clearBoard();
			        dm.drawAllItems(dm.graphics, window);
					
		        }
				break;
			default:
				break;
			}
		}else {
		if(window.getIsServer()) {
			switch(action) {
			case 119:
				
				this.selectedShape.changeLocation(0, -1);
				break;
			case 97:
				this.selectedShape.changeLocation(-1, -0);
				break;
			case 115:
				this.selectedShape.changeLocation(0, 1);
				break;
			case 100:
				this.selectedShape.changeLocation(1, 0);
				break;
			case 8:
				dm.listShape.remove(this.selectedShape);
				break;
			default:
				break;
			}
			window.getServer().sendNewFile();
		}
		else {
			System.out.println(selectedShape);
			switch(action) {
			case 119:
				window.getClient().sendUpdateDraw(selectedShape, 0, -1);
				this.selectedShape.changeLocation(0, -1);
				break;
			case 97:
				window.getClient().sendUpdateDraw(selectedShape, -1, 0);
				this.selectedShape.changeLocation(-1, 0);
				break;
			case 115:
				window.getClient().sendUpdateDraw(selectedShape, 0, 1);
				this.selectedShape.changeLocation(0, 1);
				break;
			case 100:
				window.getClient().sendUpdateDraw(selectedShape, 1, 0);
				this.selectedShape.changeLocation(1, 0);
				break;
			case 8:
				window.getClient().sendRemoveShape(selectedShape);
				dm.listShape.remove(this.selectedShape);
				break;
			default:
				break;
			}
			
		}
		
		dm.clearBoard();
		dm.drawAllItems();
		
		
	}}
 
	public void keyReleased(KeyEvent e) {
		//System.out.println("閿洏" + KeyEvent.getKeyText(e.getKeyCode()) + "閿澗寮�\n");
	}
 
	public void keyTyped(KeyEvent e) {
		//System.out.println("杈撳叆鐨勫唴瀹规槸" + e.getKeyChar() + "\n");
	}
	
	public void setSelectedShape(ShapeList selectedShape) {
		this.selectedShape=selectedShape;
		this.dm=dm;
	}
}