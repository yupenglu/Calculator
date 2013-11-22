/**
 * CIT-591 Assignment_11 Calculator
 * @author Christopher Ivey
 * @author Yupeng Lu
 */
package calculator;

import java.awt.Color;
import java.awt.Font;

/**
 * Defines clear button
 */
@SuppressWarnings("serial")
public class ClearButton extends CalculatorButton {

    ClearButton(String text) {
        super(text);
        setBackground(new Color(255, 170, 200));
        setFont(new Font("Arial", Font.BOLD, 16));
    }

}
