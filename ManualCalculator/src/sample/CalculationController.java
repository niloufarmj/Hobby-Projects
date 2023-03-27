package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class CalculationController {
    public Label answer_label;
    public TextField expression_field;

    public void calculate(MouseEvent mouseEvent) {
        answer_label.setText(Integer.toString(evaluate(expression_field.getText())));
        answer_label.setVisible(true);
    }


    int precedence(char op){
        if (op == '+'||op == '-')
            return 1;
        if (op == '*'||op == '/')
            return 2;
        if (op == '^')
            return 3;
        if (op == '(')
            return 0;
        return 4;
    }

    int precedence(String op){
        if (op.equals("+") ||op.equals("-"))
            return 1;
        if (op.equals("*") ||op.equals("/"))
            return 2;
        if (op.equals("^"))
            return 3;
        if (op.equals("("))
            return 0;
        return 4;
    }

    int applyOp(int a, int b, Character op){
        switch(op){
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            case '^':
                double a_d = a;
                double b_d = b;
                return (int)(Math.pow(a_d , b_d));
        }
        return  0;
    }

    int evaluateSimple(String tokens){
        int i;

        Stack <Integer> values = new Stack<>();

        Stack <Character> ops = new Stack<>();

        for (i = 0; i < tokens.length(); i++){


            if(tokens.charAt(i) == '('){
                ops.push(tokens.charAt(i));
            }

            else if(Character.isDigit(tokens.charAt(i))){
                int val = 0;

                while(i < tokens.length() && Character.isDigit(tokens.charAt(i)))
                {
                    val = (val*10) + (tokens.charAt(i)-'0');
                    i++;
                }

                values.push(val);

                i--;
            }

            else if(tokens.charAt(i) == ')')
            {
                while(!ops.empty() && ops.peek() != '(')
                {
                    int val2 = values.peek();
                    values.pop();

                    int val1 = values.peek();
                    values.pop();

                    char op = ops.peek();
                    ops.pop();

                    values.push(applyOp(val1, val2, op));
                }

                // pop opening brace.
                if(!ops.empty())
                    ops.pop();
            }

            // Current token is an operator.
            else
            {

                while(!ops.empty() && precedence(ops.peek())
                        >= precedence(tokens.charAt(i))){
                    int val2 = values.peek();
                    values.pop();

                    int val1 = values.peek();
                    values.pop();

                    char op = ops.peek();
                    ops.pop();

                    values.push(applyOp(val1, val2, op));
                }

                // Push current token to 'ops'.
                ops.push(tokens.charAt(i));
            }
        }

        while(!ops.empty()){
            int val2 = values.peek();
            values.pop();

            int val1 = values.peek();
            values.pop();

            char op = ops.peek();
            ops.pop();

            values.push(applyOp(val1, val2, op));
        }

        // Top of 'values' contains result, return it.
        return values.peek();
    }

    int applyOpComplex(int a, int b, String op) {
        for (int i = 0; i < AddCustomController.myOperations.size(); i++) {
            String oper = AddCustomController.myOperations.get(i);
            if (oper.length() > 1) {
                Scanner sc = new Scanner(oper);
                ArrayList<String> words = new ArrayList<>();
                while (sc.hasNext()) {
                    words.add(sc.next());
                }
                oper = words.get(1);

                if (op.equals(oper)) {
                    String expression = AddCustomController.operationValues.get(i).replaceAll(words.get(0), Integer.toString(a));
                    expression = expression.replaceAll(words.get(2), Integer.toString(b));
                    return evaluateSimple(expression);
                }
            }
            else {
                if (op.equals(oper)) {
                    return applyOp(a, b, op.charAt(0));
                }
            }

        }
        return 0;
    }

    int evaluate(String tokens){
        int i;

        Stack<Integer> values = new Stack<>();
        Stack <String> ops = new Stack<>();
        for(i = 0; i < tokens.length(); i++){

            if(tokens.charAt(i) == '('){
                ops.push("(");
            }

            else if(Character.isDigit(tokens.charAt(i))){
                int val = 0;

                while(i < tokens.length() && Character.isDigit(tokens.charAt(i)))
                {
                    val = (val*10) + (tokens.charAt(i)-'0');
                    i++;
                }

                values.push(val);

                if (i == tokens.length())
                    continue;
                i--;

            }


            else if(tokens.charAt(i) == ')')
            {
                while(!ops.empty() && !ops.peek().equals("("))
                {
                    int val2 = values.peek();
                    values.pop();

                    int val1 = values.peek();
                    values.pop();

                    String op = ops.peek();
                    ops.pop();

                    values.push(applyOpComplex(val1, val2, op));
                }

                // pop opening brace.
                if(!ops.empty())
                    ops.pop();
            }

            // Current token is an operator.
            else if (tokens.charAt(i) == ' ')
            {

                i++;
                String oper = "";
                while (tokens.charAt(i) != ' ' && i < tokens.length()) {
                oper += tokens.charAt(i);
                i++;
            }

                if (oper.length() > 0) {
                    while(!ops.empty() && (precedence(ops.peek()) >= precedence(oper))) {
                        int val2 = values.peek();
                        values.pop();

                        int val1 = values.peek();
                        values.pop();

                        String op = ops.peek();
                        ops.pop();

                        values.push(applyOpComplex(val1, val2, op));
                    }

                    ops.push(oper);
                }
            }

            else
            {
                while(!ops.empty() && (precedence(ops.peek()) >= precedence(tokens.charAt(i)))) {

                int val2 = values.peek();
                values.pop();

                int val1 = values.peek();
                values.pop();

                String op = ops.peek();
                ops.pop();

                values.push(applyOpComplex(val1, val2, op));
            }
                String temp = "";
                temp += tokens.charAt(i);
                ops.push(temp);
            }
        }

        while(!ops.empty()) {
            int val2 = values.peek();
            values.pop();

            int val1 = values.peek();
            values.pop();

            String op = ops.peek();
            ops.pop();
            values.push(applyOpComplex(val1, val2, op));
        }

        return values.peek();
    }

}
