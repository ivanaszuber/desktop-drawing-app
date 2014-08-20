
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class PaintApplication extends JFrame {
    public DrawingPanel drawingPanel;
    protected MenuBar menuBar;
    protected ColorPalette colorPalette;
    public PaintToolPanel paintToolPanel;

    public PaintApplication() {
        super("JAVA DRAWING");
        ImageIcon ImageIcon = getIcon("/images/IMG_BOJA_48.png");
        Image Image = ImageIcon.getImage();
        this.drawingPanel = new DrawingPanel();
        this.menuBar = new MenuBar();
        this.colorPalette = new ColorPalette();
        this.paintToolPanel = new PaintToolPanel(new StrokeToolPanel(5));
        this.add(this.menuBar, "North");
        this.add(this.colorPalette, "South");
        this.add(this.paintToolPanel, "West");
        this.add(new JScrollPane(this.drawingPanel), "Center");
        this.setIconImage(Image);
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
        this.setStaringColor();
    }

    public void setStaringColor() {
        ColorPalette.selectedColorDisplay.setBackground(Color.black);
        ColorPalette.selectedColor = ColorPalette.selectedColorDisplay.getBackground();
        this.drawingPanel.currentToolDetails.setColor(ColorPalette.selectedColorDisplay.getBackground());
        this.drawingPanel.brushColor = ColorPalette.selectedColor;
    }

    public static ImageIcon getIcon(String imagePath) {
        try {
            return new ImageIcon(PaintApplication.class.getResource(imagePath));
        } catch (Exception var2) {
            JOptionPane.showMessageDialog((Component)null, "Invalid image path");
            return null;
        }
    }
}
