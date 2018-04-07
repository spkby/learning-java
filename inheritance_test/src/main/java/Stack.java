import java.util.ArrayList;

public class Stack extends ArrayList<Integer> {

    public void push(int number) {
        this.add(number);
    }

    public int pop() {
        return this.remove(this.size() - 1);
    }

}

class Stack2 {

    private ArrayList<Integer> arrayList;

    public Stack2(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }


    public void push(int number) {
        arrayList.add(number);
    }

    public int pop() {
        return arrayList.remove(arrayList.size() - 1);
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

}

class Stack3 {

    private ArrayList<Integer> arrayList;

    public Stack3() {
        this.arrayList = new ArrayList<>();
    }


    public void push(int number) {
        arrayList.add(number);
    }

    public int pop() {
        return arrayList.remove(arrayList.size() - 1);
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

}


class App2 {
    public static void main(String[] args) {

        /*
        Stack stack = new Stack();
        for (int i = 0; i < 10; i++) {
            stack.push(i);

        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        */

        /*
        Stack2 stack = new Stack2(null);
        for (int i = 0; i < 10; i++) {
            stack.push(i);

        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }*/

        Stack3 stack = new Stack3();
        for (int i = 0; i < 10; i++) {
            stack.push(i);

        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }
}