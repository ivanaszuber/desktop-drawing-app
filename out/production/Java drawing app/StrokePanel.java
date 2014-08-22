

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class StrokePanel extends JPanel {
    public StrokePanel() {
        this.setPreferredSize(new Dimension(84, 84));
    }

    public void paintComponent(Graphics g) {
        g.setColor(Main.paint.drawingPanel.currentToolDetails.getColor());
        g.fillRect(this.getWidth() / 2 - Main.paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2, this.getHeight() / 2 - Main.paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2, Main.paint.drawingPanel.currentToolDetails.getStrokeWidth(), Main.paint.drawingPanel.currentToolDetails.getStrokeWidth());
        g.setColor(Color.black);
        g.drawRect(this.getWidth() / 2 - Main.paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2, this.getHeight() / 2 - Main.paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2, Main.paint.drawingPanel.currentToolDetails.getStrokeWidth(), Main.paint.drawingPanel.currentToolDetails.getStrokeWidth());
    }
}
