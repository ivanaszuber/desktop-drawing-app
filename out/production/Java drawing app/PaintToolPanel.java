

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PaintToolPanel extends JPanel {
    protected ToolButton[] toolButtons;
    public StrokeToolPanel strokeToolPanel;
    private JComboBox fillerType;
    private Icon pencil = new ImageIcon(this.getClass().getResource("images/IMG_OLOVKA_48.png"));
    private Icon roundRectangle = new ImageIcon(this.getClass().getResource("images/polygon.png"));
    private Icon filledRoundRectangle = new ImageIcon(this.getClass().getResource("images/filled-polygon.png"));
    private Icon oval = new ImageIcon(this.getClass().getResource("images/oval.png"));
    private Icon filledOval = new ImageIcon(this.getClass().getResource("images/filled-oval.png"));
    private Icon rectangle = new ImageIcon(this.getClass().getResource("images/rectangle.png"));
    private Icon filledRectangle = new ImageIcon(this.getClass().getResource("images/filled-rectangle.png"));
    private Icon lineTool = new ImageIcon(this.getClass().getResource("images/line-tool.png"));
    private Icon paintBrush = new ImageIcon(this.getClass().getResource("images/paint-brush.png"));
    private Icon eraser = new ImageIcon(this.getClass().getResource("images/eraser.png"));
    private JPanel toolPanel = new JPanel();
    private ToolButton colorPickerButton;

    public PaintToolPanel(StrokeToolPanel strokeToolPanel) {
        this.setBackground(Color.darkGray);
        this.setPreferredSize(new Dimension(200, 0));
        this.setLayout(new BorderLayout(8, 8));
        this.toolPanel.setLayout(new GridLayout(4, 2));
        this.toolPanel.setBackground(Color.darkGray);
        this.toolPanel.setPreferredSize(new Dimension(200, 300));
        this.strokeToolPanel = strokeToolPanel;
        this.toolButtons = new ToolButton[8];
        String[] fillerTypes = new String[]{"EMPTY", "FILLED"};
        this.fillerType = new JComboBox(fillerTypes);
        PaintToolPanel.ComboBoxHandler handler = new PaintToolPanel.ComboBoxHandler(null);
        this.fillerType.addActionListener(handler);
        this.fillerType.setFont(new Font("Cambria", 1, 16));
        ImageIcon colorPicker = new ImageIcon(this.getClass().getResource("images/IMG_BOJA_48.png"));
        this.colorPickerButton = new ToolButton(colorPicker, ToolFactory.createTool(1));
        this.colorPickerButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                ColorPalette.selectedColorDisplay.setBackground(JColorChooser.showDialog(Main.paint, "Change Color", Main.paint.drawingPanel.brushColor));
                ColorPalette.selectedColor = ColorPalette.selectedColorDisplay.getBackground();
                Main.paint.drawingPanel.currentToolDetails.setColor(ColorPalette.selectedColorDisplay.getBackground());
                Main.paint.drawingPanel.setCurrentColor(ColorPalette.selectedColor);
            }
        });
        this.addBasicToolButtons();
        this.addEmptyShapeToolButtons();
        ToolButton[] arr$ = this.toolButtons;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            ToolButton toolButton = arr$[i$];
            this.toolPanel.add(toolButton);
        }

        this.add(this.toolPanel, "North");
        this.add(this.fillerType, "Center");
        this.add(strokeToolPanel, "South");
    }

    private void addBasicToolButtons() {
        this.toolButtons[0] = new ToolButton(this.pencil, ToolFactory.createTool(1));
        this.toolButtons[1] = new ToolButton(this.paintBrush, ToolFactory.createTool(8));
        this.toolButtons[2] = new ToolButton(this.eraser, ToolFactory.createTool(2));
        this.toolButtons[3] = this.colorPickerButton;
        this.toolButtons[4] = new ToolButton(this.lineTool, ToolFactory.createTool(7));
    }

    private void addEmptyShapeToolButtons() {
        this.toolButtons[5] = new ToolButton(this.oval, ToolFactory.createTool(5));
        this.toolButtons[6] = new ToolButton(this.roundRectangle, ToolFactory.createTool(4));
        this.toolButtons[7] = new ToolButton(this.rectangle, ToolFactory.createTool(6));
    }

    private void addFilledShapeToolButtons() {
        this.toolButtons[5] = new ToolButton(this.filledOval, ToolFactory.createTool(10));
        this.toolButtons[6] = new ToolButton(this.filledRoundRectangle, ToolFactory.createTool(11));
        this.toolButtons[7] = new ToolButton(this.filledRectangle, ToolFactory.createTool(9));
    }

    private class ComboBoxHandler implements ActionListener {
        private ComboBoxHandler() {
        }

        public void actionPerformed(ActionEvent e) {
            PaintToolPanel.this.fillerType = (JComboBox)e.getSource();
            int selectedValue = PaintToolPanel.this.fillerType.getSelectedIndex();
            ToolButton[] arr$;
            int len$;
            int i$;
            ToolButton toolButton;
            if(selectedValue == 0) {
                arr$ = PaintToolPanel.this.toolButtons;
                len$ = arr$.length;

                for(i$ = 0; i$ < len$; ++i$) {
                    toolButton = arr$[i$];
                    PaintToolPanel.this.toolPanel.remove(toolButton);
                }

                PaintToolPanel.this.revalidate();
                PaintToolPanel.this.repaint();
                PaintToolPanel.this.addBasicToolButtons();
                PaintToolPanel.this.addEmptyShapeToolButtons();
                arr$ = PaintToolPanel.this.toolButtons;
                len$ = arr$.length;

                for(i$ = 0; i$ < len$; ++i$) {
                    toolButton = arr$[i$];
                    PaintToolPanel.this.toolPanel.add(toolButton);
                }
            } else if(selectedValue == 1) {
                arr$ = PaintToolPanel.this.toolButtons;
                len$ = arr$.length;

                for(i$ = 0; i$ < len$; ++i$) {
                    toolButton = arr$[i$];
                    PaintToolPanel.this.toolPanel.remove(toolButton);
                }

                PaintToolPanel.this.revalidate();
                PaintToolPanel.this.repaint();
                PaintToolPanel.this.addBasicToolButtons();
                PaintToolPanel.this.addFilledShapeToolButtons();
                arr$ = PaintToolPanel.this.toolButtons;
                len$ = arr$.length;

                for(i$ = 0; i$ < len$; ++i$) {
                    toolButton = arr$[i$];
                    PaintToolPanel.this.toolPanel.add(toolButton);
                }
            }

        }
    }
}
