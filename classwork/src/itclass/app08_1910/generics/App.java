package itclass.app08_1910.generics;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = null;

        System.out.print("Введите выражение = ");
        input = scanner.nextLine();

        CharStack charStack = new CharStack(input.length());

        boolean isBalanced = true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    charStack.push(c);
                    break;
                case ')':
                    try {
                        char prev = charStack.peek();
                        if (prev == '(') {
                            charStack.pop();
                        }
                    } catch (IllegalStateException e) {
                        isBalanced = false;
                        break;
                    }
                    break;
                case ']':
                    try {
                        char prev = charStack.peek();
                        if (prev == '[') {
                            charStack.pop();
                        }
                    } catch (IllegalStateException e) {
                        isBalanced = false;
                        break;
                    }
                    break;
                case '}':
                    try {
                        char prev = charStack.peek();
                        if (prev == '{') {
                            charStack.pop();
                        }
                    } catch (IllegalStateException e) {
                        isBalanced = false;
                        break;
                    }
                    break;
                default:
                    break;
            }
        }

        if (charStack.size() != 0) {
            isBalanced = false;
        }

        if (isBalanced) {
            System.out.println("Выражение сбалансировано");
        } else {
            System.err.println("Выражение не сбалансировано");
        }


    }

}
