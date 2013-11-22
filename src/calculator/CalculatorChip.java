/**
 * CIT-591 Assignment_11 Calculator
 * @author Christopher Ivey
 * @author Yupeng Lu
 */
package calculator;

/**
 * Defines all the logic functions of the calculator
 */
public class CalculatorChip {
    
    private double x, y, m, result;
    private String prevOpn, endZero;
    private boolean doAC, enterX, canCalculate, cleanX, cleanY, equalCalled, isError, canInvert;
    private boolean[] hasDecimalPoint;
    private int minusOne;  // use to indicate whether the display field is a negative number of positive number

    CalculatorChip() {
        initialize();
    }
    
    /**
     * Initialize all the variables to initial status
     */
    void initialize() {
        x = 0;
        y = 0;
        result = 0;
        prevOpn = "";
        enterX = true;
        doAC = true;
        canCalculate = false;
        cleanX = false;
        cleanY = false;
        equalCalled = false;
        hasDecimalPoint = new boolean[2];
        hasDecimalPoint[0] = false;
        hasDecimalPoint[1] = false;
        endZero = "";
        minusOne = 1;
        isError = false;
        canInvert = true;
    }
    
    
	/**
	 * Clear screen (doAC is false) or make the calculator to initial status (doAC is true)
	 * @return String for outputs of display panel
	 */
	String clear() {
	    if (doAC) {
	        initialize();
	    } else {
	        isError = false;
	                
// Remove comment for the following part to change the behavior of clear button
//	        if (enterX) {
//	            hasDecimalPoint[0] = false;
//	            x = 0;
//	        } else {
//	            hasDecimalPoint[1] = false;
//	            y = 0;
//	        }
	        
	        doAC = true;
	    }
	    return "0";
	}
	
	/**
	 * Clears number stored in the memory
	 * @return  String for outputs of display panel
	 */
	String memClear() {
	    m = 0;
	    return format(result);
	}
	
	/**
	 * Gets number stored in the memory
	 * @return
	 */
	String memRead() {
	    result = m;
	    if (enterX) x = m;
	    else y = m;
	    enterX = false;
	    return format(result);
	}
	
	/**
	 * Adds current result to the number stored in memory
	 * @return String for outputs of display panel
	 */
	String memPlus() {
	    m = m + result;
		return format(result);
	}
	
	/**
	 * Subtracts current result from the number stored in memory
	 * @return String for outputs of display panel
	 */
	String memMinus() {
	    m = m - result;
		return format(result);
	}
	
	/**
	 * Changes number stored in temporary storage (x or y)
	 * @param digit The number pressed from GUI
	 * @return String for outputs of display panel
	 */
	String digit(int digit) {
	    isError = false;
	    if (digit == 0) {
	        if (enterX && hasDecimalPoint[0])
	            endZero += "0";
	        if (!enterX && hasDecimalPoint[1])
	            endZero += "0";
	    }
	    
	    if (enterX) {
	        // set x = 0 first if cleanX is true
            if (cleanX) x = 0;
            
            if (y != Math.floor(y))
                hasDecimalPoint[0] = true;

            if (! hasDecimalPoint[0]) {
                x = x * 10 + digit * minusOne;
            } else {
                String temp = String.valueOf(x);
                if (String.valueOf(x).endsWith(".0")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                if (!endZero.equals("")){
                    x = Double.parseDouble(temp + endZero + digit);
                } else {
                    x = Double.parseDouble(temp + digit);
                }
            }
	        prevOpn = "";
	        cleanX = false;
	        doAC = false;
	        
	        if (digit != 0)
	            endZero = "";
	        return format(x);
	    }
	    else {
	        // set y = 0 first if cleanY is true
	        if (cleanY) y = 0;
	        
            if (y != Math.floor(y))
                hasDecimalPoint[1] = true;


            if (! hasDecimalPoint[1]) {
                y = y * 10 + digit * minusOne;
            } else {
                String temp = String.valueOf(y);
                if (String.valueOf(y).endsWith(".0")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                if (!endZero.equals("")){
                    y = Double.parseDouble(temp + endZero + digit);
                } else {
                    y = Double.parseDouble(temp + digit);
                }
            }
	        cleanY = false;
	        canCalculate = true;
	        
	        if (digit != 0)
	            endZero = "";
	        return format(y);
	    }

	}
	
	/**
	 * Add decimal point to current number
	 * @return String for outputs of display panel
	 */
	String decimalPoint() {
	    if (cleanX && enterX) x = 0;
	    if (cleanY && !enterX) y = 0;
	    
	    if (enterX) {
	        if (!hasDecimalPoint[0]) hasDecimalPoint[0] = true;
	        String temp = String.valueOf(x) + endZero;
	        if (temp.endsWith(".0"))
	            return temp.substring(0, temp.length() - 1);
	        else
	            return temp.substring(0, temp.length());
	    }
	    else if (!enterX){
	        if (! hasDecimalPoint[1]) hasDecimalPoint[1] = true;
	        String temp = String.valueOf(y) + endZero;
	        if (temp.endsWith(".0"))
	            return temp.substring(0, temp.length() - 1);   // case 2.03.
	        else
	            return temp.substring(0, temp.length());       // case 2.33.
	    }
	    return format(result);
	}
	
	/**
	 * Performs addition operation
	 * @return String for outputs of display panel
	 */
	String add() {
	    cleanY = true;
	    minusOne = 1;
	    if (enterX) {
	        enterX = false;
	        minusOne = 1;
	        minusOne = 1;
	        y = x;
	        if (equalCalled) prevOpn = "";
	        prevOpn = "add";
	        equalCalled = false;
	        return format(x);
	    } else {
	        if (canCalculate) {
	            String output = equals();
	            y = result;
	            prevOpn = "add";
	            equalCalled = false;
	            return output;
	        } else {
	            prevOpn = "add";
	            equalCalled = false;
	            return format(x);
	        }
	    }
	}
	
	/**
	 * Performs subtraction operation
	 * @return String for outputs of display panel
	 */
	String subtract() {
	    cleanY = true;
	    minusOne = 1;
        if (enterX) {
            enterX = false;
            y = x;
            if (equalCalled) prevOpn = "";
            prevOpn = "subtract";
            equalCalled = false;
            return format(x);
        } else {
            if (canCalculate) {
                String output = equals();
                y = result;
                prevOpn = "subtract";
                equalCalled = false;
                return output;
            } else {
                prevOpn = "subtract";
                equalCalled = false;
                return format(x);
            }
        }
	}
	
	/**
	 * Performs multiplication operation
	 * @return String for outputs of display panel
	 */
	String multiply() {
	    cleanY = true;
	    minusOne = 1;
        if (enterX) {
            enterX = false;
            minusOne = 1;
            y = x;
            if (equalCalled) prevOpn = "";
            prevOpn = "multiply";
            equalCalled = false;
            return format(x);
        } else {
            if (canCalculate) {
                String output = equals();
                y = result;
                prevOpn = "multiply";
                equalCalled = false;
                return output;
            } else {
                prevOpn = "multiply";
                equalCalled = false;
                return format(x);
            }
        }
	}
	
	/**
	 * Performs division operation
	 * @return String for outputs of display panel
	 */
	String divide() {
	    cleanY = true;
	    minusOne = 1;
	    if (enterX) {
	        enterX = false;
	        minusOne = 1;
	        y = x;
	        System.out.print(equalCalled);
	        if (equalCalled) prevOpn = "";
	        prevOpn = "divide";
	        equalCalled = false;
	        return format(x);
	    } else {
	        if (canCalculate) {
                String output = equals();
                y = result;
                prevOpn = "divide";
                equalCalled = false;
                return output;
            } else {
                prevOpn = "divide";
                equalCalled = false;
                return format(x);
            }
	    }
	}
	
	/**
	 * Calculates the number using numbers stored in temporary variable(x or y) and the 
	 * operation should perform(stored in variable prevOpn)
	 * @return String for outputs of display panel
	 */
	String equals() {
	    switch(prevOpn) {
	    case "add":
	        result = x + y;
	        break;
	    case "subtract":
	        result = x - y;
	        break;
	    case "multiply":
	        result = x * y;
	        break;
	    case "divide":
	        result = x / y;
	        break;
	    }
	    hasDecimalPoint[0] = false;
	    hasDecimalPoint[1] = false;
	    x = result;
	    cleanX = true;
	    cleanY = true;
	    endZero = "";
	    equalCalled = true;
	    canCalculate = false;
		return format(result);
	}
	
	/**
	 * When equal button clicked in GUI, change doAC to true and then performs equals()
	 * @return String for outputs of display panel
	 */
	String doEquals() {
	    enterX = true;
	    doAC = true;
	    return equals();
	}
	
	/**
	 * Calculate square root of current number
	 * @return String for outputs of display panel
	 */
	String sqrt() {
	    cleanX = true;
	    cleanY = true;
	    endZero = "";
	    if (enterX) {
	        x = Math.sqrt(x);
	        return format(x);
	    } else if (! canCalculate) {
	        x = Math.sqrt(x);
	        return format(x);
	    } else {
	        y = Math.sqrt(y);
	        return format(y);
	    }
	}
	
	/**
	 * Calculate percentage of current number
	 * @return String for outputs of display panel
	 */
	String percent() {
	    cleanX = true;
	    cleanY = true;
	    endZero = "";
	    if (enterX) {
	        x = 0.01 * result;
	        return format(x);
	    } else if (! canCalculate) {
	        if (equalCalled) {
	            x = 0.01 * result;
	            y = 0;
	            return format(x);
	        }
	        else {
	            y = 0.01 * x * y;
	            return format(y);
	        }
	    } else {
	        y = 0.01 * x * y;
	        return format(y);
	    }
	}
	
	/**
	 * Calculate inversion of current number
	 * @return String for outputs of display panel
	 */
	String invert() {
	    if (canInvert) {
	        cleanX = true;
	        cleanY = true;
	        endZero = "";
	        if (enterX) {
	            x = 1 / x;
	            return format(x);
	        } else if (! canCalculate) {
	            x = 1 / x;
	            return format(x);
	        } else {
	            y = 1 / y;
	            return format(y);
	        }
	    }
	    else return format(result);
	}
	
	/**
	 * Change sign of current number
	 * @return String for outputs of display panel
	 */
	String changeSign() {
	    minusOne = minusOne * (-1);
	    if (enterX) {
	        x = 0 - result;
	        cleanX = false;
	        prevOpn = "";
	        return format(x);
	    } else if (equalCalled) {
	        y = 0 - result;
	        cleanY = false;
	        return format(y);
	    } else {
            y = 0 - result;
            cleanY = false;
            return format(y);
	    }
	}
	
	/**
	 * Formats the number that is going to display on GUI:
	 * cutting ".0", cutting the tail of extra long numbers
	 * @param n Number going to show on GUI
	 * @return String with good format for outputs of display panel
	 */
	private String format(Double n) {
	    result = n;    // update the variable result
//	    if (!isError) {
	        String output = String.valueOf(n);
	        

	        // check 1/0 case (which leads to infinity)
	        if (Double.isInfinite(n)) {
	            isError = true;
	            canInvert = false;
	            result = Double.NaN;
	            equalCalled = true;
	            return "Error";
	        }

	        // check sqrt(-x) case (which leads to NaN)
	        if (Double.isNaN(n)) {
	            isError = true;
	            equalCalled = true;
	            return "Error";
	        }

	        // if the output contains a 'E', we cut the number of digits before 'E' to make the whole String
	        // display properly on screen
	        if (output.contains("E")) {
	            String[] tempArray;
	            tempArray = output.split("E");
	            if (Double.parseDouble(tempArray[1]) > 99 || Double.parseDouble(tempArray[1]) < -99) {
	                return "error";
	            }

	            tempArray[0] = tempArray[0].substring(0, Math.min(10, (tempArray[0].length())));  // here 10 is for the worst case of e-XX
	            output = tempArray[0] + "e+" + tempArray[1];
	        }

	        if (endZero.equals("")) {

	            if (output.endsWith(".0"))     // if ends with ".0", cut that
	                output = output.substring(0, Math.min(15, (output.length() - 2)));
	            else       // if ends with something else, we always want to keep the length below 15
	                output = output.substring(0, Math.min(15, (output.length())));

	        } else {

	            if (output.endsWith(".0")) 
	                output = output.substring(0, Math.min(15, (output.length() - 1)));
	            else
	                output = output.substring(0, Math.min(15, (output.length())));
	        }

	        output += endZero;
	        return output;
//	    }
//	    else return "Error";   // if isError then always output Error
	}
	

/**
 * Getters and Setters below
 */

	public String getPrevOpn() {
	    return prevOpn;
	}
	
	public boolean getEqualCalled() {
	    return equalCalled;
	}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getM() {
        return m;
    }

    public double getResult() {
        return result;
    }

    public boolean isDoAC() {
        return doAC;
    }

//    public boolean isEnterX() {
//        return enterX;
//    }
//
//    public boolean isCanCalculate() {
//        return canCalculate;
//    }
//
//    public boolean isCleanY() {
//        return cleanY;
//    }
//
//    public boolean[] getHasDecimalPoint() {
//        return hasDecimalPoint;
//    }
//
//    public int getMinusOne() {
//        return minusOne;
//    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setM(double m) {
        this.m = m;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setPrevOpn(String prevOpn) {
        this.prevOpn = prevOpn;
    }

    public void setDoAC(boolean doAC) {
        this.doAC = doAC;
    }

    public void setEnterX(boolean enterX) {
        this.enterX = enterX;
    }

    public void setCanCalculate(boolean canCalculate) {
        this.canCalculate = canCalculate;
    }

//    public void setCleanY(boolean cleanY) {
//        this.cleanY = cleanY;
//    }
//
//    public void setEqualCalled(boolean equalCalled) {
//        this.equalCalled = equalCalled;
//    }
//
//    public void setHasDecimalPoint(boolean[] hasDecimalPoint) {
//        this.hasDecimalPoint = hasDecimalPoint;
//    }
//
//    public void setMinusOne(int minusOne) {
//        this.minusOne = minusOne;
//    }
}
