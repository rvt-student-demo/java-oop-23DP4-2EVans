package rvt.Draw;

import javax.swing.JFrame;

public class JavaPanelis {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java grafika");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);  
        
        GrafiskaisPanelis grafika = new GrafiskaisPanelis();

        frame.add(grafika);
        frame.setVisible(true);
    }
}
