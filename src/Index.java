import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Index {
	public static void main(String[] args) {
		JFrame f = new JFrame("Constructive Geometry");
		f.getContentPane().setPreferredSize(new Dimension(400, 400));
		f.add(new YinYang());
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}

class YinYang extends JPanel {
	static Area leftWisp;
	static Area rightWisp;

	static {
		Area topCircle = new Area(new Ellipse2D.Float(100, 0, 200, 200));
		Area bottomCircle = new Area(new Ellipse2D.Float(100, 200, 200, 200));
		Area leftRect = new Area(new Rectangle2D.Float(0, 0, 200, 400));
		Area rightRect = new Area(new Rectangle2D.Float(200, 0, 200, 400));
		Area silhouette = new Area(new Ellipse2D.Float(0, 0, 400, 400));

		leftWisp = new Area(leftRect);
		leftWisp.intersect(silhouette);
		leftWisp.add(topCircle);
		leftWisp.subtract(bottomCircle);

		rightWisp = new Area(rightRect);
		rightWisp.intersect(silhouette);
		rightWisp.add(bottomCircle);
		rightWisp.subtract(topCircle);
	}

	@Override
	public void paint(Graphics gg) {
		super.paint(gg);
		Graphics2D g = (Graphics2D)(gg);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.fill(leftWisp);
		g.setColor(Color.BLACK);
		g.fill(rightWisp);
		g.setColor(Color.WHITE);
		g.fill(new Ellipse2D.Float(180, 280, 40, 40));
		g.setColor(Color.BLACK);
		g.fill(new Ellipse2D.Float(180, 80, 40, 40));
	}

}