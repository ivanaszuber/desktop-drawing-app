
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class MenuBar extends JMenuBar {
    public JMenu file;
    public JMenu view;
    public JMenu options;
    public JMenu help;
    public JMenuItem quit;
    public JMenuItem newFile;
    public JMenuItem openFile;
    public JMenuItem saveFile;
    public JMenuItem howToPaint;
    public JMenuItem about;
    public JFileChooser fileChooser = null;

    public MenuBar() {
        MenuBar.MenuOptionsHandler itemHandler = new MenuBar.MenuOptionsHandler();
        this.file = new JMenu("File");
        this.help = new JMenu("Help");
        this.view = new JMenu("View");
        this.options = new JMenu("Tools");
        this.newFile = new JMenuItem("New File");
        this.openFile = new JMenuItem("Open File");
        this.saveFile = new JMenuItem("Save File");
        this.quit = new JMenuItem("Exit");
        this.newFile.addActionListener(itemHandler);
        this.openFile.addActionListener(itemHandler);
        this.saveFile.addActionListener(itemHandler);
        this.quit.addActionListener(itemHandler);
        this.file.add(this.newFile);
        this.file.add(this.openFile);
        this.file.add(this.saveFile);
        this.file.addSeparator();
        this.file.add(this.quit);
        this.howToPaint = new JMenuItem("Help...");
        this.howToPaint.addActionListener(itemHandler);
        this.about = new JMenuItem("About...");
        this.about.addActionListener(itemHandler);
        this.help.add(this.howToPaint);
        this.view.add(this.about);
        this.add(this.file);
        this.add(this.view);
        this.add(this.options);
        this.add(this.help);
    }

    public JFileChooser getFileChooser() {
        if(this.fileChooser == null) {
            this.fileChooser = new JFileChooser();
            this.fileChooser.setFileFilter(new MenuBar.PNGFileFilter());
        }

        return this.fileChooser;
    }

    public static BufferedImage getScreenShot(Component component) {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), 1);
        component.paint(image.getGraphics());
        return image;
    }

    private static class PNGFileFilter extends FileFilter {
        private PNGFileFilter() {
        }

        public boolean accept(File file) {
            return file.getName().toLowerCase().endsWith(".png") || file.isDirectory();
        }

        public String getDescription() {
            return "PNG image  (*.png) ";
        }
    }

    private class MenuOptionsHandler implements ActionListener {
        private MenuOptionsHandler() {
        }

        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == MenuBar.this.quit) {
                Main.paint.dispose();
                System.exit(0);
            }

            if(event.getSource() == MenuBar.this.newFile) {
                BufferedImage ch = new BufferedImage(1024, 768, 2);
                Main.paint.drawingPanel.clearImage(ch);
                Main.paint.drawingPanel.setImage(ch);
            }

            int result;
            JFileChooser ch1;
            if(event.getSource() == MenuBar.this.saveFile) {
                ch1 = MenuBar.this.getFileChooser();
                result = ch1.showSaveDialog(Main.paint.drawingPanel);
                if(result == 0) {
                    try {
                        File ex = ch1.getSelectedFile();
                        ex = new File(ex.getAbsolutePath() + ".png");
                        BufferedImage img = MenuBar.getScreenShot(Main.paint.drawingPanel);
                        ImageIO.write(img, "png", ex);
                    } catch (IOException var7) {
                        JOptionPane.showMessageDialog((Component)null, "Could not save the file");
                    }
                }
            }

            if(event.getSource() == MenuBar.this.openFile) {
                ch1 = MenuBar.this.getFileChooser();
                result = ch1.showOpenDialog(Main.paint.drawingPanel);
                if(result == 0) {
                    try {
                        Main.paint.drawingPanel.setOSImage(ImageIO.read(ch1.getSelectedFile()));
                    } catch (IOException var6) {
                        JOptionPane.showMessageDialog((Component)null, "Could not open file");
                    }
                }
            }

            if(event.getSource() == MenuBar.this.howToPaint) {
                JOptionPane.showMessageDialog((Component)null, "Use the tool buttons on the left to paint components on the screen.\nChange the color of the components by selecting a color from the palette.\nChange the stroke of the components by moving the slider on the left.");
            }

            if(event.getSource() == MenuBar.this.about) {
                JOptionPane.showMessageDialog((Component)null, "This application was made for the purpose of the Java Module WEEK 8 HIA assignment.\n\nCreated by: Ivana Zuber\nCreated date: 03 March 2013");
            }

        }
    }
}
