

import java.awt.Color;

public class ToolDetails extends Tool {
    protected int strokeWidth;
    protected Color color;

    public ToolDetails(Color brushColor, int stroke, int type) {
        super(type);
        this.color = brushColor;
        this.strokeWidth = stroke;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color clr) {
        this.color = clr;
    }

    public void setStrokeWidth(int dim) {
        this.strokeWidth = dim;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }
}
