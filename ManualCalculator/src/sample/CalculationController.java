package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Stack;

public class CalculationController {
    public Label answer_label;
    public TextField expression_field;

    public void calculate(MouseEvent mouseEvent) {
        answer_label.setText(Integer.toString(evaluate(expression_field.getText())));
        answer_label.setVisible(true);
    }


    String findAndReplaceAll(String data, String toSearch, String replaceStr)
    {
        // Get the first occurrence
        size_t pos = data.find(toSearch);
        // Repeat till end is reached
        while( pos != string::npos)
        {

            data.replace(pos, toSearch.size(), replaceStr);

            pos =data.find(toSearch, pos + replaceStr.size());
        }
        return data;
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

    int precedence(string op){
        if (op == "+"||op == "-")
            return 1;
        if (op == "*"||op == "/")
            return 2;
        if (op == "^")
            return 3;
        if (op == "(")
            return 0;
        return 4;
    }

    int applyOp(int a, int b, char op){
        switch(op){
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            case '^': return pow(a , b);
        }
    }

    int evaluateSimple(String tokens){
        int i;

        Stack <Integer> values = new Stack<>();

        Stack <Character> ops = new Stack<>();

        for (i = 0; i < tokens.length(); i++){


            if(tokens[i] == '('){
                ops.push(tokens[i]);
            }

            else if(isdigit(tokens[i])){
                int val = 0;

                while(i < tokens.length() &&
                        isdigit(tokens[i]))
                {
                    val = (val*10) + (tokens[i]-'0');
                    i++;
                }

                values.push(val);

                i--;
            }

            else if(tokens[i] == ')')
            {
                while(!ops.empty() && ops.top() != '(')
                {
                    int val2 = values.top();
                    values.pop();

                    int val1 = values.top();
                    values.pop();

                    char op = ops.top();
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

                while(!ops.empty() && precedence(ops.top())
                        >= precedence(tokens[i])){
                    int val2 = values.top();
                    values.pop();

                    int val1 = values.top();
                    values.pop();

                    char op = ops.top();
                    ops.pop();

                    values.push(applyOp(val1, val2, op));
                }

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        while(!ops.empty()){
            int val2 = values.peek();
            values.pop();

            int val1 = values.top();
            values.pop();

            char op = ops.top();
            ops.pop();

            values.push(applyOp(val1, val2, op));
        }

        // Top of 'values' contains result, return it.
        return values.top();
    }

    int applyOpComplex(int a, int b, string op) {
        for (int i = 0; i < myOperations.size(); i++) {
            string oper = myOperations[i];
            if (myOperations[i].length() > 1) {
                stringstream ss(oper);
                vector <string> words;
                string word;
                while (ss >> word) {
                    words.push_back(word);
                }
                oper = words[1];

                if (op == oper) {
                    string expression = findAndReplaceAll(operationValues[i], words[0], to_string(a));
                    expression = findAndReplaceAll(expression, words[2], to_string(b));
                    return evaluateSimple(expression);
                }
            }
            else {
                if (op == oper) {
                    return applyOp(a, b, op[0]);
                }
            }

        }
    }

    int evaluate(String tokens){
        int i;

        Stack<Integer> values = new Stack<>();
        Stack <String> ops = new Stack<>();
        for(i = 0; i < tokens.length(); i++){

            if(tokens.charAt(i) == '('){
                ops.push("(");
            }

            else if(isdigit(tokens.charAt(i))){
                int val = 0;

                while(i < tokens.length() && isdigit(tokens.charAt(i)))
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
                while(!ops.empty() && ops.peek() != "(")
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
