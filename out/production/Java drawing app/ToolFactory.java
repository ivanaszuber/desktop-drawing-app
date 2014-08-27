

public class ToolFactory {
    private static Tool brushTool;
    private static Tool pencillTool;
    private static Tool eraserTool;
    private static Tool ovalTool;
    private static Tool lineTool;
    private static Tool rectangleTool;
    private static Tool fillerTool;
    private static Tool roundRectangleTool;
    private static Tool filledRectangleTool;
    private static Tool filledOvalTool;
    private static Tool filledRoundRectangleTool;
    private static Tool airBrushTool;
    private static Tool currentTool;
    public static final int BRUSH_TOOL = 0;
    public static final int PENCILL_TOOL = 1;
    public static final int ERASER_TOOL = 2;
    public static final int FILLER_TOOL = 3;
    public static final int ROUND_RECTANGLE_TOOL = 4;
    public static final int OVAL_TOOL = 5;
    public static final int RECTANGLE_TOOL = 6;
    public static final int LINE_TOOL = 7;
    public static final int AIR_BRUSH_TOOL = 8;
    public static final int FILLED_RECTANGLE_TOOL = 9;
    public static final int FILLED_OVAL_TOOL = 10;
    public static final int FILLED_ROUND_RECTANGLE_TOOL = 11;

    public ToolFactory() {
    }

    public static Tool createTool(int toolTYpe) {
        switch(toolTYpe) {
            case 0:
                if(brushTool == null) {
                    brushTool = new Tool(0);
                }

                currentTool = brushTool;
                break;
            case 1:
                if(pencillTool == null) {
                    pencillTool = new Tool(1);
                }

                currentTool = pencillTool;
                break;
            case 2:
                if(eraserTool == null) {
                    eraserTool = new Tool(2);
                }

                currentTool = eraserTool;
                break;
            case 3:
                if(fillerTool == null) {
                    fillerTool = new Tool(3);
                }

                currentTool = fillerTool;
                break;
            case 4:
                if(roundRectangleTool == null) {
                    roundRectangleTool = new Tool(4);
                }

                currentTool = roundRectangleTool;
                break;
            case 5:
                if(ovalTool == null) {
                    ovalTool = new Tool(5);
                }

                currentTool = ovalTool;
                break;
            case 6:
                if(rectangleTool == null) {
                    rectangleTool = new Tool(6);
                }

                currentTool = rectangleTool;
                break;
            case 7:
                if(lineTool == null) {
                    lineTool = new Tool(7);
                }

                currentTool = lineTool;
                break;
            case 8:
                if(airBrushTool == null) {
                    airBrushTool = new Tool(8);
                }

                currentTool = airBrushTool;
                break;
            case 9:
                if(filledRectangleTool == null) {
                    filledRectangleTool = new Tool(9);
                }

                currentTool = filledRectangleTool;
                break;
            case 10:
                if(filledOvalTool == null) {
                    filledOvalTool = new Tool(10);
                }

                currentTool = filledOvalTool;
                break;
            case 11:
                if(filledRoundRectangleTool == null) {
                    filledRoundRectangleTool = new Tool(11);
                }

                currentTool = filledRoundRectangleTool;
        }

        return currentTool;
    }
}
