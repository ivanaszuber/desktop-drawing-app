

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    public Image OSI;
    int OSIWidth;
    int OSIHeight;
    private int mouseX;
    private int mouseY;
    private int prevX;
    private int prevY;
    private int startX;
    private int startY;
    private boolean isDrawing;
    private Graphics2D dragGraphics;
    public Color brushColor;
    int[][] brushPoints;
    protected Boolean mousePressed;
    public Tool currentTool;
    public ToolDetails currentToolDetails;
    public Color backgroundColor;

    public DrawingPanel() {
        this.backgroundColor = Color.white;
        this.setBackground(this.backgroundColor);
        this.setPreferredSize(new Dimension(1024, 768));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.mousePressed = Boolean.valueOf(false);
        this.brushColor = Color.black;
        this.currentTool = ToolFactory.createTool(1);
        this.currentToolDetails = new ToolDetails(this.brushColor, 5, 1);
    }

    private void drawGraphics(Graphics2D graphics2D, Tool currentTool, int pointX1, int pointY1, int pointX2, int pointY2) {
        if(currentTool.toolType == 7) {
            graphics2D.setStroke(new BasicStroke((float)this.currentToolDetails.strokeWidth, 1, 1));
            graphics2D.drawLine(pointX1, pointY1, pointX2, pointY2);
            this.repaint();
        } else {
            int positionY;
            if(currentTool.toolType == 8) {
                Random positionX = new Random();
                this.brushPoints = new int[this.currentToolDetails.strokeWidth * this.currentToolDetails.strokeWidth / 10][2];

                for(positionY = 0; positionY < this.currentToolDetails.strokeWidth * this.currentToolDetails.strokeWidth / 10; ++positionY) {
                    int[] weight = new int[]{positionX.nextInt(this.currentToolDetails.strokeWidth), positionX.nextInt(this.currentToolDetails.strokeWidth)};
                    graphics2D.drawRect(pointX1 + weight[0], pointY1 + weight[1], 1, 1);
                    this.brushPoints[positionY] = weight;
                }

                this.repaint();
            }

            int var11;
            int var12;
            if(pointX1 >= pointX2) {
                var11 = pointX2;
                var12 = pointX1 - pointX2;
            } else {
                var11 = pointX1;
                var12 = pointX2 - pointX1;
            }

            int height;
            if(pointY1 >= pointY2) {
                positionY = pointY2;
                height = pointY1 - pointY2;
            } else {
                positionY = pointY1;
                height = pointY2 - pointY1;
            }

            if(currentTool.toolType == 6) {
                graphics2D.setStroke(new BasicStroke((float)this.currentToolDetails.strokeWidth, 1, 1));
                graphics2D.drawRect(var11, positionY, var12, height);
                this.repaint();
            } else if(currentTool.toolType == 5) {
                graphics2D.setStroke(new BasicStroke((float)this.currentToolDetails.strokeWidth, 1, 1));
                graphics2D.drawOval(var11, positionY, var12, height);
                this.repaint();
            } else if(currentTool.toolType == 4) {
                graphics2D.setStroke(new BasicStroke((float)this.currentToolDetails.strokeWidth, 1, 1));
                graphics2D.drawRoundRect(var11, positionY, var12, height, 20, 20);
                this.repaint();
            } else if(currentTool.toolType == 10) {
                graphics2D.fillOval(var11, positionY, var12, height);
            } else if(currentTool.toolType == 9) {
                graphics2D.fillRect(var11, positionY, var12, height);
            } else {
                if(currentTool.toolType == 11) {
                    graphics2D.fillRoundRect(var11, positionY, var12, height, 20, 20);
                }

            }
        }
    }

    private void repaintRectangle(int pointX1, int pointY1, int pointX2, int pointY2) {
        int x;
        int w;
        if(pointX2 >= pointX1) {
            x = pointX1;
            w = pointX2 - pointX1;
        } else {
            x = pointX2;
            w = pointX1 - pointX2;
        }

        int y;
        int h;
        if(pointY2 >= pointY1) {
            y = pointY1;
            h = pointY2 - pointY1;
        } else {
            y = pointY2;
            h = pointY1 - pointY2;
        }

        this.repaint(x, y, w + 1, h + 1);
    }

    private void createOffScreenImage() {
        if(this.OSI == null || this.OSIWidth != this.getSize().width || this.OSIHeight != this.getSize().height) {
            this.OSI = null;
            this.OSI = this.createImage(this.getSize().width, this.getSize().height);
            this.OSIWidth = this.getSize().width;
            this.OSIHeight = this.getSize().height;
            Graphics graphics = this.OSI.getGraphics();
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.OSIWidth, this.OSIHeight);
            graphics.dispose();
        }

    }

    public void paintComponent(Graphics graphics) {
        this.createOffScreenImage();
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics.drawImage(this.OSI, 0, 0, this);
        if(this.isDrawing && this.currentTool.toolType != 1 && this.currentTool.toolType != 8 && this.currentTool.toolType != 2) {
            graphics.setColor(this.brushColor);
            this.drawGraphics(graphics2D, this.currentTool, this.startX, this.startY, this.mouseX, this.mouseY);
        }

    }

    public void setOSImage(BufferedImage image) {
        this.OSI = image;
        this.repaint();
    }

    public void setImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        this.OSI = new BufferedImage(w, h, 2);
        this.OSI = this.createImage(w, h);
        this.OSIWidth = this.getSize().width;
        this.OSIHeight = this.getSize().height;
        this.repaint();
        Graphics graphics = this.OSI.getGraphics();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.OSIWidth, this.OSIHeight);
        graphics.dispose();
    }

    public void clearImage(BufferedImage image) {
        Graphics2D g = image.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
        this.repaint();
    }

    private Color getCurrentColor() {
        return this.currentTool.toolType != 2?this.currentToolDetails.getColor():this.getBackground();
    }

    public void setCurrentColor(Color clr) {
        this.brushColor = clr;
        this.currentToolDetails.setColor(clr);
    }

    public void mousePressed(MouseEvent evt) {
        if(!this.isDrawing) {
            this.prevX = this.startX = evt.getX();
            this.prevY = this.startY = evt.getY();
            this.brushColor = this.getCurrentColor();
            this.dragGraphics = (Graphics2D)this.OSI.getGraphics();
            this.dragGraphics.setColor(this.brushColor);
            this.dragGraphics.setBackground(this.getBackground());
            this.isDrawing = true;
        }
    }

    public void mouseReleased(MouseEvent evt) {
        if(this.isDrawing) {
            this.isDrawing = false;
            this.mouseX = evt.getX();
            this.mouseY = evt.getY();
            if(this.currentTool.toolType != 1 && this.currentTool.toolType != 8 && this.currentTool.toolType != 2) {
                this.repaintRectangle(this.startX, this.startY, this.prevX, this.prevY);
                if(this.mouseX != this.startX && this.mouseY != this.startY) {
                    this.drawGraphics(this.dragGraphics, this.currentTool, this.startX, this.startY, this.mouseX, this.mouseY);
                    this.repaintRectangle(this.startX, this.startY, this.mouseX, this.mouseY);
                }
            }

            this.dragGraphics.dispose();
            this.dragGraphics = null;
        }
    }

    public void mouseDragged(MouseEvent evt) {
        if(this.isDrawing) {
            this.mouseX = evt.getX();
            this.mouseY = evt.getY();
            if(this.currentTool.toolType == 1) {
                this.drawGraphics(this.dragGraphics, ToolFactory.createTool(7), this.prevX, this.prevY, this.mouseX, this.mouseY);
                this.repaintRectangle(this.prevX, this.prevY, this.mouseX, this.mouseY);
            } else if(this.currentTool.toolType == 2) {
                this.drawGraphics(this.dragGraphics, ToolFactory.createTool(7), this.prevX, this.prevY, this.mouseX, this.mouseY);
                this.repaintRectangle(this.prevX, this.prevY, this.mouseX, this.mouseY);
            } else if(this.currentTool.toolType == 8) {
                this.drawGraphics(this.dragGraphics, ToolFactory.createTool(8), this.prevX, this.prevY, this.mouseX, this.mouseY);
                this.repaintRectangle(this.prevX, this.prevY, this.mouseX, this.mouseY);
            } else {
                this.repaintRectangle(this.startX, this.startY, this.prevX, this.prevY);
                this.repaintRectangle(this.startX, this.startY, this.mouseX, this.mouseY);
            }

            this.prevX = this.mouseX;
            this.prevY = this.mouseY;
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
}
