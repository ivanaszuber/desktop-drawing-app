
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StrokeToolPanel extends JPanel {
    protected JSlider strokeSlider;
    protected JPanel strokePanel;
    protected StrokePanel stroke;

    public StrokeToolPanel(int stroke) {
        this.setPreferredSize(new Dimension(50, 256));
        this.setBackground(Color.darkGray);
        this.setLayout(new FlowLayout());
        this.strokeSlider = new JSlider(0, 0, 16, 5);
        this.strokeSlider.setPreferredSize(new Dimension(200, 20));
        this.strokeSlider.setPaintTicks(false);
        this.strokeSlider.setMajorTickSpacing(1);
        this.strokeSlider.setValue(stroke);
        this.strokeSlider.revalidate();
        StrokeToolPanel.SlideChangeListener listener = new StrokeToolPanel.SlideChangeListener();
        this.strokeSlider.addChangeListener(listener);
        this.stroke = new StrokePanel();
        this.strokePanel = new JPanel();
        this.strokePanel.setBackground(new Color(228, 237, 245));
        this.strokePanel.setPreferredSize(new Dimension(200, 100));
        this.strokePanel.setLayout(new FlowLayout());
        this.strokePanel.add(this.stroke, "South");
        this.add(this.strokePanel);
        this.add(this.strokeSlider);
    }

    private class SlideChangeListener implements ChangeListener {
        private SlideChangeListener() {
        }

        public void stateChanged(ChangeEvent event) {
            Main.paint.drawingPanel.currentToolDetails.setStrokeWidth(StrokeToolPanel.this.strokeSlider.getValue());
            StrokeToolPanel.this.repaint();
        }
    }
}
