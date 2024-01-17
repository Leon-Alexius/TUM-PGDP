package DiscreteStructure;

import java.util.*;
import java.util.stream.Collectors;

// TODO: NOT FINISHED -> EVALUATE A BOOLEAN IN STRING FORMAT
public class TruthTable_Ex {
    private String expression;
    private List<String> variables;
    private Map<String, Boolean> values;

    public TruthTable_Ex(String expression) {
        this.expression = expression;
        this.variables = extractVariables(expression);
        this.values = new HashMap<>();
        for (String variable : variables) {
            values.put(variable, false);
        }
    }

    private List<String> extractVariables(String expression) {
        String[] parts = expression.replaceAll("[^a-z]", "").split("");
        return Arrays.stream(parts).distinct().collect(Collectors.toList());
    }

    public void generate() {
        do {
            // TODO: Evaluate boolean value in String format
            boolean result = BooleanExpressionEvaluator.evaluateBooleanExpression(evaluateExpression());
            System.out.println(result);
        } while (incrementValues());
    }

    private String evaluateExpression() {
        String evaluatedExpression = expression;
        for (String variable : variables) {
            evaluatedExpression = evaluatedExpression.replaceAll(variable, values.get(variable).toString());
        }
        System.out.println(evaluatedExpression); // TODO: Remove after done
        return evaluatedExpression;
    }

    private boolean incrementValues() {
        for (String variable : variables) {
            boolean value = values.get(variable);
            if (!value) {
                values.put(variable, true);
                return true;
            } else {
                values.put(variable, false);
            }
        }
        return false;
    }

    // TODO: to evaluate a String
    public class BooleanExpressionEvaluator {
        public static boolean evaluateBooleanExpression(String expression) {
            Stack<Boolean> operandStack = new Stack<>();
            Stack<Character> operatorStack = new Stack<>();

            for (char ch : expression.toCharArray()) {
                if (ch == ' ') {
                    // Ignore spaces
                    continue;
                }

                if (ch == 't') {
                    operandStack.push(true);
                } else if (ch == 'f') {
                    operandStack.push(false);
                } else if (ch == '&' || ch == '|') {
                    if (!operatorStack.isEmpty() && hasHigherPrecedence(ch, operatorStack.peek())) {
                        processOperator(operandStack, operatorStack.pop());
                    }
                    operatorStack.push(ch);
                } else if (ch == '!') {
                    operatorStack.push(ch);
                } else if (ch == '(') {
                    operatorStack.push(ch);
                } else if (ch == ')') {
                    if (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        processOperator(operandStack, operatorStack.pop());
                    }
                    if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                        operatorStack.pop(); // Pop '('
                    } else {
                        throw new IllegalArgumentException("Mismatched parentheses");
                    }
                }
            }

            while (!operatorStack.isEmpty()) {
                processOperator(operandStack, operatorStack.pop());
            }

            if (operandStack.size() != 1) {
                throw new IllegalArgumentException("Invalid expression");
            }

            return operandStack.pop();
        }

        private static void processOperator(Stack<Boolean> operandStack, char operator) {
            if (operator == '&') {
                if (operandStack.size() >= 2) {
                    boolean operand2 = operandStack.pop();
                    boolean operand1 = operandStack.pop();
                    operandStack.push(operand1 && operand2);
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            } else if (operator == '|') {
                if (operandStack.size() >= 2) {
                    boolean operand2 = operandStack.pop();
                    boolean operand1 = operandStack.pop();
                    operandStack.push(operand1 || operand2);
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            } else if (operator == '!') {
                if (!operandStack.isEmpty()) {
                    operandStack.push(!operandStack.pop());
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            }
        }

        private static boolean hasHigherPrecedence(char op1, char op2) {
            return (op1 == '&' && op2 == '|') || (op1 == '&' && op2 == '!') || (op1 == '|' && op2 == '!');
        }
    }

    // test here
    public static void main(String[] args) {
        TruthTable_Ex truthTableEx = new TruthTable_Ex("(a && b || c) && d");
        truthTableEx.generate();
    }
}
