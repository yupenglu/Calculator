/**
 * CIT-591 Assignment_11 Calculator
 * @author Christopher Ivey
 * @author Yupeng Lu
 */
package calculator;

import java.awt.Color;

/**
 * Defines number buttons
 */
@SuppressWarnings("serial")
public class NumberButton extends CalculatorButton {

    int digit;
    NumberButton(String text) {
        super(text);
        setBackground(new Color(180, 190, 238));
    }
    
//    @Override
//    protected void paintComponent(Graphics g){
//        Graphics2D g2 = (Graphics2D)g.create();
//        g2.setPaint(new GradientPaint(
//                new Point(0, 0), 
//                Color.WHITE, 
//                new Point(0, getHeight()), 
//                Color.BLUE.darker()));
//        g2.fillRect(0, 0, getWidth(), getHeight());
//        g2.dispose();
//
//        super.paintComponent(g);
//    }
}
