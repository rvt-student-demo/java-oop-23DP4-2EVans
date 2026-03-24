package rvt.Draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class GrafiskaisPanelis extends JPanel{
    public GrafiskaisPanelis() {
        super();
    }
    public GrafiskaisPanelis(LayoutManager layout) {
        super(layout);
    }
    public GrafiskaisPanelis(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }
    public GrafiskaisPanelis(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); 

        g.fillRect(10, 10, 40, 40);
        g.drawLine(50, 50, 70, 60);
        g.fillOval(65, 55, 30, 30);
    }
}
