
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ToolButton extends JButton implements ActionListener {
    public JLabel label;
    public Tool tool;

    public ToolButton(Icon icon, Tool tool) {
        this.label = new JLabel(icon);
        this.setLayout(new BorderLayout());
        this.add(this.label);
        this.tool = tool;
        this.addActionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void actionPerformed(ActionEvent event) {
        Main.paint.drawingPanel.currentTool = this.tool;
        Main.paint.repaint();
    }
}
