
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ColorButton extends JPanel {
    Color color;
    boolean isSelected;

    public ColorButton(Color clr) {
        this.color = clr;
        this.isSelected = false;
        this.setBackground(this.color);
        this.addMouseListener(new ColorButton.MouseHandler());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
        if(this.isSelected) {
            g.setColor(Color.white);
            g.drawRect(1, 1, this.getWidth(), this.getHeight());
            g.drawRect(-1, -1, this.getWidth(), this.getHeight());
        }

    }

    private class MouseHandler extends MouseAdapter {
        private MouseHandler() {
        }

        public void mousePressed(MouseEvent event) {
            Main.paint.colorPalette.deselectAll();
            ColorButton.this.isSelected = true;
            Main.paint.drawingPanel.setCurrentColor(ColorButton.this.color);
            ColorPalette.selectedColorDisplay.setBackground(ColorButton.this.color);
            Main.paint.repaint();
        }

        public void mouseReleased(MouseEvent mouseevent) {
        }

        public void mouseClicked(MouseEvent mouseevent) {
        }

        public void mouseEntered(MouseEvent mouseevent) {
        }
    }
}
