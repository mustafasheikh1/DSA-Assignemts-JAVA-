

import java.util.Stack;

public class Evaluation {

    //seleted operators
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')';
    }

    //precedence chart generater
    private int getPrecedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // A utility function to check if the given character is operand
    private boolean isOperand(char ch) {
        if (Character.isDigit(ch)) {
            return true;
        }
        return false;
    }

    // method to convert infix to postfix
    public String inToPost(String infix) {
        if (protocolChecker(infix) == false) {
            exit();
        }
        Stack<Character> stack = new Stack<Character>();
        StringBuffer postfix = new StringBuffer(infix.length());
        char c;

        for (int i = 0; i < infix.length(); i++) {
            c = infix.charAt(i);

            if (isOperand(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } // If the scanned character is an ‘)’, pop and output from the stack
            // until an ‘(‘ is encountered.
            else if (c == ')') {

                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return null;
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (isOperator(c)) // operator encountered
            {
                if (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    //postfix calculation (not working correctly)
    public float posEval(String postfix) {
        float result = 0;
        
        /*for (int i = 0; i < postfix.length(); i++) {
            String replace = "";
            if (i + 2 < postfix.length()) {
                if (isOperand(postfix.charAt(i)) && isOperand(postfix.charAt(i + 1)) && isOperator(postfix.charAt(i + 2))) {
                    int op1 = Character.getNumericValue(postfix.charAt(i));
                    int op2 = Character.getNumericValue(postfix.charAt(i + 1));
                    replace = Integer.toString(postfix.charAt(i)) + Integer.toString(postfix.charAt(i + 1)) + Integer.toString(postfix.charAt(i + 2));
                    switch (postfix.charAt(i + 2)) {
                        case '+':
                            result = op1 + op2;
                            break;
                        case '-':
                            result = op1 - op2;
                            break;
                        case '*':
                            result = op1 * op2;
                            break;
                        case '/':
                            result = op1 / op2;
                            break;
                        case '^':
                            result = op1 ^ op2;
                            break;
                        default:
                            break;
                    }
                }
            }
            postfix = postfix.replace(replace, Float.toString(result));
        }*/

        return 0;
    }

    /*
        This will be responsible for the checking of input of infix expression 
        weather the input follows the rules and ragulations of infix expression
        
        This method will prevent the program to execute an invalid infix express 
        for example  :  5*8/ (is is invalid)
        more over you are not allowed to enter any number greater than 99 or less than 0
     */
    private boolean protocolChecker(String infixExp) {
        int i = 0;
        while (i < infixExp.length()) {
            if (Character.toString(infixExp.charAt(i)).matches("[-*/+^]")) {  // no spaces are allowed
                if (i == 0 || i == infixExp.length() - 1 || Character.toString(infixExp.charAt(i + 1)).matches("[-*/+^]")) {
                    return false;
                }
                i++;

                /*
                the code below with nested if is reponsible for the 2 digit limit of the expression
                 */
            } else if (Character.toString(infixExp.charAt(i)).matches("[0-9]")) {
                if (Character.getNumericValue(infixExp.charAt(i)) > 0) {
                    if (!(i + 1 >= infixExp.length())) {
                        if (Character.toString(infixExp.charAt(i + 1)).matches("[0-9]")) {
                            if (!(i + 2 >= infixExp.length())) {
                                if (Character.toString(infixExp.charAt(i + 2)).matches("[0-9]")) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                i++;
            } else if (Character.toString(infixExp.charAt(i)).matches("[()]")) {

                /*
                    )5/5 : invalid
                    5*5( : invalid
                 */
                if (infixExp.charAt(0) == ')' || infixExp.charAt(infixExp.length() - 1) == '(') {
                    return false;
                }
                int temp1 = 0;    // opening bracket counter
                int temp2 = 0;    // closing bracket counter
                for (int x = 0; x < infixExp.length() - 1; x++) {
                    if (infixExp.charAt(x) == '(') {
                        temp1++;
                    }
                    if (infixExp.charAt(x) == ')') {
                        temp2++;
                    }
                }
                if (temp1 == temp2) {    // the number of opening ( should match number of ) brackets
                    i++;
                } else {
                    return false;
                }

            } else if (infixExp.length() < 3) {  //mimmum lenght of expression e.g : 4+5
                return false;
            } else {                             //anything elase will cause in an invalid input
                return false;
            }
        }
        return true;
    }

    //in case of invalid input
    private void exit() {
        System.out.print("\nInvalid Expression\n");
        System.exit(0);
    }
}
