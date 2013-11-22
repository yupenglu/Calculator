package calculator;

import java.awt.Color;

/**
 * Defines memory buttons
 */
@SuppressWarnings("serial")
public class MemoryButton extends CalculatorButton {

    MemoryButton(String text) {
        super(text);
        setBackground(new Color(255, 222, 173));
    }

//    @Override
//    protected void paintComponent(Graphics g){
//        Graphics2D g2 = (Graphics2D)g.create();
//        g2.setPaint(new GradientPaint(
//                new Point(0, 0), 
//                Color.WHITE, 
//                new Point(0, getHeight()), 
//                Color.YELLOW.darker()));
//        g2.fillRect(0, 0, getWidth(), getHeight());
//        g2.dispose();
//
//        super.paintComponent(g);
//    }
}
