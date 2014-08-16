

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class ColorPalette extends JPanel {
    protected ColorButton[] colorButtons;
    public static JPanel selectedColorDisplay;
    protected Color[] colors;
    public static Color selectedColor;

    public ColorPalette() {
        this.setBackground(Color.darkGray);
        this.setLayout(new BorderLayout());
        this.colors = new Color[92];

        for(int colorButtonsGrid = 0; colorButtonsGrid < 92; ++colorButtonsGrid) {
            this.colors[colorButtonsGrid] = Color.getHSBColor((float)colorButtonsGrid / 92.0F, 0.85F, 1.0F);
        }

        selectedColor = Color.black;
        selectedColorDisplay = new JPanel();
        selectedColorDisplay.setPreferredSize(new Dimension(180, 92));
        selectedColorDisplay.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                ColorPalette.selectedColorDisplay.setBackground(JColorChooser.showDialog(Main.paint, "Change Color", Main.paint.drawingPanel.brushColor));
                ColorPalette.selectedColor = ColorPalette.selectedColorDisplay.getBackground();
                Main.paint.drawingPanel.currentToolDetails.setColor(ColorPalette.selectedColorDisplay.getBackground());
                Main.paint.drawingPanel.setCurrentColor(ColorPalette.selectedColor);
            }
        });
        JPanel var4 = new JPanel();
        var4.setBackground(Color.darkGray);
        var4.setLayout(new GridLayout(4, 16, 6, 6));
        this.colorButtons = new ColorButton[this.colors.length];

        for(int colorButtonsPanel = 0; colorButtonsPanel < this.colorButtons.length; ++colorButtonsPanel) {
            this.colorButtons[colorButtonsPanel] = new ColorButton(this.colors[colorButtonsPanel]);
            var4.add(this.colorButtons[colorButtonsPanel]);
        }

        ColorPanel var5 = new ColorPanel(Color.darkGray);
        var5.setLayout(new BorderLayout(6, 6));
        var5.add(selectedColorDisplay, "West");
        var5.add(var4, "Center");
        JPanel colorButtonRows = new JPanel();
        colorButtonRows.setLayout(new BorderLayout());
        colorButtonRows.add(new ColorPanel(Color.darkGray), "West");
        colorButtonRows.add(new ColorPanel(Color.darkGray), "East");
        colorButtonRows.add(new ColorPanel(Color.darkGray), "South");
        colorButtonRows.add(new ColorPanel(Color.darkGray), "North");
        colorButtonRows.add(var5, "Center");
        this.add(colorButtonRows, "Center");
    }

    public void deselectAll() {
        ColorButton[] arr$ = this.colorButtons;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            ColorButton colorButton = arr$[i$];
            colorButton.isSelected = false;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
