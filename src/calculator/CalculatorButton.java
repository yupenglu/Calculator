/**
 * CIT-591 Assignment_11 Calculator
 * @author Christopher Ivey
 * @author Yupeng Lu
 */
package calculator;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

/**
 * Defines all buttons shown on GUI
 */
@SuppressWarnings("serial")
public abstract class CalculatorButton extends JButton {
	
    CalculatorButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(80, 80));
        this.setFont(new Font("Arial", Font.BOLD, 24));
    }
}