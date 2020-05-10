package Seminar7;

// Usage:
// java Brackets "{(()[])}"
// Uses a stack of char to check if opening and closing brackets match
class Brackets {

    static boolean match(String expr) {
        Stack S = new Stack();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c=='(' || c=='[' || c=='{') {
                S.push(c);
            } else if (c==')' || c==']' || c=='}') {
                if (S.isEmpty()) {
                    return false;
                }
                char d = S.pop();
                if ( !(    (c==')' && d=='(')
                        || (c==']' && d=='[')
                        || (c=='}' && d=='{')
                       ) ) {
                    return false;
                }   
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            return;
        }
        if (match(args[0])) {
            System.out.println("Brackets match");
        } else {
            System.out.println("Brackets don't match");
        }
    }
}
