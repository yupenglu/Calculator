/**
 * CIT-591 Assignment_11 Calculator
 * @author Christopher Ivey
 * @author Yupeng Lu
 */
package calculator;

import java.awt.Color;

/**
 * Defines operation buttons
 */
@SuppressWarnings("serial")
public class OperationButton extends CalculatorButton {

    OperationButton(String text) {
        super(text);
        this.setBackground(new Color(140, 251, 140));
    }

//    @Override
//    protected void paintComponent(Graphics g){
//        Graphics2D g2 = (Graphics2D)g.create();
//        g2.setPaint(new GradientPaint(
//                new Point(0, 0), 
//                Color.WHITE, 
//                new Point(0, getHeight()), 
//                Color.GREEN.darker()));
//        g2.fillRect(0, 0, getWidth(), getHeight());
//        g2.dispose();
//
//        super.paintComponent(g);
//    }
}
