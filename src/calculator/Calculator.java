/**
 * CIT-591 Assignment_11 Calculator
 * @author Christopher Ivey
 * @author Yupeng Lu
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Calculator class including all codes regarding GUI
 * also includes main method
 */
@SuppressWarnings("serial")
public class Calculator extends JFrame {
    static Calculator calc;
    static CalculatorChip chip;
    static NumberButton zero, one, two, three, four, five, six, seven, eight, nine, decimal;
    static OperationButton neg, plus, minus, mul, div, sqrt, perc, rec, equal;
    static MemoryButton mC, mR, mPlus, mMinus;
    static ClearButton clear;
    static JPanel pNum, pMem, pOpn, display;
    static JTextField textField;
    static Color green = new Color(140, 251, 140);
    static Color pink = new Color(255,182, 182);
    
    
	/**
	 * Main method generating a instance of CalculatorChip and a GUI
	 * @param args
	 */
	public static void main(String[] args) {
	    
	    calc = new Calculator();
	    chip = new CalculatorChip();
	    try {
	        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    creatGUI();
	    calc.setVisible(true);
	    setListeners();
	    
	}
	
	Calculator() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    setSize(440, 580);
	    setTitle("Calculator");
	    setLocation(100, 100);
	    
	}
	
	/**
	 * Set all the listeners for buttons on GUI
	 */
	static void setListeners() {
	    // add action listeners
        Calculator.zero.addActionListener(new NumListener());
        Calculator.one.addActionListener(new NumListener());
        Calculator.two.addActionListener(new NumListener());
        Calculator.three.addActionListener(new NumListener());
        Calculator.four.addActionListener(new NumListener());
        Calculator.five.addActionListener(new NumListener());
        Calculator.six.addActionListener(new NumListener());
        Calculator.seven.addActionListener(new NumListener());
        Calculator.eight.addActionListener(new NumListener());
        Calculator.nine.addActionListener(new NumListener());
        Calculator.decimal.addActionListener(new NumListener());
        Calculator.plus.addActionListener(new BinaryListener());
        Calculator.minus.addActionListener(new BinaryListener());
        Calculator.mul.addActionListener(new BinaryListener());
        Calculator.div.addActionListener(new BinaryListener());
        Calculator.neg.addActionListener(new OpnListener());
        Calculator.sqrt.addActionListener(new OpnListener());
        Calculator.perc.addActionListener(new OpnListener());
        Calculator.rec.addActionListener(new OpnListener());
        Calculator.equal.addActionListener(new OpnListener());
        Calculator.mC.addActionListener(new MemListener());
        Calculator.mR.addActionListener(new MemListener());
        Calculator.mPlus.addActionListener(new MemListener());
        Calculator.mMinus.addActionListener(new MemListener());
        Calculator.clear.addActionListener(new ClearListener());
	}
	
	/**
	 * Method creating GUI
	 * Calls several other methods to generate several JPanels
	 * Then combine JPanels together
	 */
	static void creatGUI() {
	    
	    JPanel pNum = createNumberButtons();
	    JPanel pMem = createMemoryButtons();
	    JPanel pOpn = createOperationButtons();
	    JPanel pClr = createClearButtons();
	    display = createTextField("0");
	    
	    calc.setLayout(new FlowLayout());
	    calc.add(textField);
	    calc.add(pClr);
	    calc.add(pMem, BorderLayout.NORTH);
	    calc.add(pNum, BorderLayout.WEST);
	    calc.add(pOpn);
	    
	}
	
	/**
	 * Creates NumberButtons in a JPanel
	 * @return JPanel
	 */
	static JPanel createNumberButtons() {
	    zero = new NumberButton("0");
        one = new NumberButton("1");
        two = new NumberButton("2");
        three = new NumberButton("3");
        four = new NumberButton("4");
        five = new NumberButton("5");
        six = new NumberButton("6");
        seven = new NumberButton("7");
        eight = new NumberButton("8");
        nine = new NumberButton("9");
        decimal = new NumberButton(".");
        neg = new OperationButton("+/-");
        
        JPanel pNum = new JPanel();
        pNum.setLayout(new GridLayout(4, 3, 5, 5));
        pNum.setPreferredSize(new Dimension(250, 335));
        pNum.setOpaque(false);

        pNum.add(seven);
        pNum.add(eight);
        pNum.add(nine);
        pNum.add(four);
        pNum.add(five);
        pNum.add(six);
        pNum.add(one);
        pNum.add(two);
        pNum.add(three);
        pNum.add(zero);
        pNum.add(decimal);
        pNum.add(neg);
        
	    return pNum;
	}
	
	/**
	 * Creates MemoryButtons in a JPanel
	 * @return JPanel
	 */
	static JPanel createMemoryButtons() {
	    mC = new MemoryButton("MC");
	    mR = new MemoryButton("MR");
	    mPlus = new MemoryButton("M+");
	    mMinus = new MemoryButton("M-");
	    sqrt = new OperationButton("√");
	    
	    JPanel pMem = new JPanel();
	    pMem.setLayout(new GridLayout(1, 5, 5, 5));
	    pMem.setOpaque(false);
	    
	    pMem.add(mC);
	    pMem.add(mR);
	    pMem.add(mPlus);
	    pMem.add(mMinus);
	    pMem.add(sqrt);
	    
	    return pMem;
	}
	
	/**
	 * Creates OperationButtons in a JPanel
	 * @return JPanel
	 */
	static JPanel createOperationButtons() {
	    plus = new OperationButton("+");
	    minus = new OperationButton("-");
	    mul = new OperationButton("x");
	    div = new OperationButton("÷");
	    perc = new OperationButton("%");
	    rec = new OperationButton("1/x");
	    equal = new OperationButton("=");
	    equal.setMinimumSize(new Dimension(80, 160));
	    
	    JPanel pOpn1 = new JPanel();
	    pOpn1.setLayout(new GridLayout(2, 2, 5, 5));
	    pOpn1.setPreferredSize(new Dimension(165, 165));
	    pOpn1.setOpaque(false);
	    
	    JPanel pOpn2 = new JPanel();
	    pOpn2.setLayout(new GridLayout(2, 1, 5, 5));
	    pOpn2.setPreferredSize(new Dimension(80, 165));
	    pOpn2.setOpaque(false);
	    
	    JPanel pOpn3 = new JPanel();
	    pOpn3.setLayout(new GridLayout(1, 2, 5, 5));
	    pOpn3.setPreferredSize(new Dimension(165, 165));
	    pOpn3.setOpaque(false);
	    
	    JPanel pOpn4 = new JPanel();
	    pOpn4.setLayout(new GridLayout(2, 1, 5, 5));
	    pOpn4.setPreferredSize(new Dimension(165, 335));
	    pOpn4.setOpaque(false);

	    pOpn1.add(div);
	    pOpn1.add(perc);
	    pOpn1.add(mul);
	    pOpn1.add(rec);
	  
	    pOpn2.add(minus);
	    pOpn2.add(plus);
	    
	    pOpn3.add(pOpn2);
	    pOpn3.add(equal);
	    
	    pOpn4.add(pOpn1);
	    pOpn4.add(pOpn3);

	    return pOpn4;
	}
	
	/**
	 * Creates JTextField (display) in a JPanel
	 * @param text the initial text shown on the display
	 * @return JPanel containing a JTextField
	 */
	static JPanel createTextField(String text) {
	    textField = new JTextField(text);
	    textField.setPreferredSize(new Dimension(420, 80));
	    textField.setFont(new Font("Arial", Font.BOLD, 36));
	    textField.setHorizontalAlignment(JTextField.RIGHT);
	    textField.setEditable(false);
	    textField.setBackground(new Color(220, 240, 240));
	    textField.setSelectionColor(new Color(60, 60, 60));
	    JPanel display = new JPanel();
	    display.setOpaque(false);
	    display.add(textField);
	    return display;
	}
	
	/**
	 * Creates ClearButtons in a JPanel
	 * @return JPanel
	 */
	static JPanel createClearButtons() {
	    JPanel pClr = new JPanel();
	    pClr.setOpaque(false);
	    pClr.setPreferredSize(new Dimension(420, 30));
	    pClr.setLayout(new BorderLayout());
	    clear = new ClearButton("AC");
	    clear.setPreferredSize(new Dimension(80, 30));
//	    clear.setAlignmentX(80);
	    pClr.add(clear, BorderLayout.EAST);
	    return pClr;
	}
	
	/**
	 * Contains actions need to perform after NumberButtons clicked
	 */
	static class NumListener implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent e) {
            resetBinaryButtonColor();
            if (e.getSource() == zero)
                textField.setText(chip.digit(0));
            if (e.getSource() == one)
                textField.setText(chip.digit(1));
            if (e.getSource() == two)
                textField.setText(chip.digit(2));
            if (e.getSource() == three)
                textField.setText(chip.digit(3));
            if (e.getSource() == four)
                textField.setText(chip.digit(4));
            if (e.getSource() == five)
                textField.setText(chip.digit(5));
            if (e.getSource() == six)
                textField.setText(chip.digit(6));
            if (e.getSource() == seven)
                textField.setText(chip.digit(7));
            if (e.getSource() == eight)
                textField.setText(chip.digit(8));
            if (e.getSource() == nine)
                textField.setText(chip.digit(9));
            if (e.getSource() == decimal)
                textField.setText(chip.decimalPoint());
            clear.setLabel("C");
        }
	}
	
	/**
	 * Contains actions need to perform after BinaryOperationButtons clicked
	 */
	static class BinaryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            resetBinaryButtonColor();
            ((OperationButton) e.getSource()).setBackground(pink);
            
            if (e.getSource() == plus)
                textField.setText(chip.add());
            if (e.getSource() == minus)
                textField.setText(chip.subtract());
            if (e.getSource() == mul)
                textField.setText(chip.multiply());
            if (e.getSource() == div)
                textField.setText(chip.divide());
        }
	}
	
	/**
	 * Contains actions need to perform after other OperationButtons clicked
	 */
	static class OpnListener implements ActionListener {

	    @SuppressWarnings("deprecation")
        @Override
	    public void actionPerformed(ActionEvent e) {
	        
	        resetBinaryButtonColor();
	        
	        if (e.getSource() == equal)
	            textField.setText(chip.doEquals());
            if (e.getSource() == sqrt)
                textField.setText(chip.sqrt());
            if (e.getSource() == perc)
                textField.setText(chip.percent());
            if (e.getSource() == neg)
                textField.setText(chip.changeSign());
            if (e.getSource() == rec)
                textField.setText(chip.invert());
            clear.setLabel("C");
        }
	}
        
	/**
	 * Contains actions need to perform after MemoryButtons clicked
	 */
	static class MemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (e.getSource() == mC) {
                mR.setBackground(new Color(255, 222, 173));
                textField.setText(chip.memClear());
            }
            if (e.getSource() == mR) {
                textField.setText(chip.memRead());
            }
            if (e.getSource() == mPlus) {
                mR.setBackground(new Color(255, 182, 182));
                textField.setText(chip.memPlus());
            }
            if (e.getSource() == mMinus) {
                mR.setBackground(new Color(255, 182, 182));
                textField.setText(chip.memMinus());
            }
        }
	}
	
	/**
	 * Contains actions need to perform after ClearButtons clicked
	 */
	static class ClearListener implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {
           
            resetBinaryButtonColor();
            
            String temp = chip.getPrevOpn();
            if (! chip.getEqualCalled()) {
                switch(temp) {
                case "add":
                    plus.setBackground(pink);
                    break;
                case "subtract":
                    minus.setBackground(pink);
                    break;
                case "multiply":
                    mul.setBackground(pink);
                    break;
                case "divide":
                    div.setBackground(pink);
                    break;
                }
            }
            textField.setText(chip.clear());
            clear.setLabel("AC");
        }
	}
	    
	/**
	 * Sets the four BinaryOperationButtons color back to green
	 */
	static void resetBinaryButtonColor() {
	    plus.setBackground(green);
	    minus.setBackground(green);
	    mul.setBackground(green);
	    div.setBackground(green);
	}
}
