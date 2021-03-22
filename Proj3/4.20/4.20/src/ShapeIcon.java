import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author Cay Horstmann
 * 
   An icon that contains a moveable shape.
 */
public class ShapeIcon implements Icon
{
	private int width;
	private int height;
	private MoveableShape shape;
	ArrayList<MoveableShape> list = new ArrayList<MoveableShape>();


	public ShapeIcon(ArrayList<MoveableShape> list,
			int width, int height)
	{
		this.list = list;
		this.width = width;
		this.height = height;
	}

	public int getIconWidth()
	{
		return width;
	}

	public int getIconHeight()
	{
		return height;
	}

	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.DARK_GRAY);
		for(int i = 0; i<list.size(); i++) {
			shape = list.get(i);
			shape.draw(g2);
		}
	}


}


