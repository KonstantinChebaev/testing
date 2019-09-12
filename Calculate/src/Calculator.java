import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

    /**
     * код не поддерживает отрицательные числа на входе

     * String input = "10/(2-7+3)*4";
     * String expectedResult = "-20";
     *
     *  String input = "10/2-7+3*4";
     *  String expectedResult = "10";

     */
//    public static void main(String[] args) {
//        String s = "10/(2-7+3)*4";
//        Calculator calc = new Calculator();
//        s = calc.evaluate(s);
//        System.out.println(s);
//
//    }

    public String evaluate(String statement) {
        boolean calcAble = true;
        if (statement == null||statement.equals("")||statement.contains("--")||statement.contains("++")||statement.contains("//")||statement.contains("**")||statement.contains(",")||statement.contains("..")){
            calcAble = false;
            return null;
        }
        char[] primeCh = statement.toCharArray();
        ArrayList<String> primeSt = new ArrayList<>();
        String buff = "";
        for (int i = 0;i<primeCh.length;i++) {
            char x = primeCh[i];
            if (x == '('||x ==')'||x=='+'||x=='-'||x=='*'||x=='/') {
                if(!(buff.equals(""))){
                    primeSt.add(buff);
                    buff="";
                }
                if(x=='-'){
                    primeSt.add("+");
                    buff+=x;
                }else {
                    primeSt.add(String.valueOf(x));
                }
            } else {
                buff+=String.valueOf(x);
            }
            if(i==(primeCh.length-1)&&!(buff.equals(""))){
                primeSt.add(buff);
            }
        }

        Stack<String> stack = new Stack<>();
        ArrayList<String> exit = new ArrayList<>();
        for (String token : primeSt){
            if(token.matches("[-]?[\\d]+([.][\\d]+)?")){
                exit.add(token);
            }
            else if(token.equals("+")||token.equals("-")){
                if(stack.empty()){
                    stack.push(token);
                }
                else if(!stack.peek().equals("(")){
                    exit.add(stack.peek());
                    stack.pop();
                    stack.push(token);
                } else {
                    stack.push(token);
                }
            }
            else if(token.equals("*")||token.equals("/")){
                if(stack.empty()){
                    stack.push(token);
                }
                else if(stack.peek().equals("+")||stack.peek().equals("-")||stack.peek().equals("(")){
                    stack.push(token);
                } else if (stack.peek().equals("*")||stack.peek().equals("/"))  {
                    exit.add(stack.peek());
                    stack.pop();
                    stack.push(token);
                }
            }
            else if(token.equals("(")){
                stack.push(token);
            }
            else if(token.equals(")")){
                if(!stack.contains("(")){
                    calcAble = false;
                    break;
                }
                while(!stack.peek().equals("(")){
                    exit.add(stack.peek());
                    stack.pop();
                }
               stack.pop();
            }
        }
        if(stack.contains("(")){
            calcAble = false;
            return null;
        }
        System.out.println("");
        for (int x = 0;x<stack.size()+1;x++){
            exit.add(stack.pop());
        }

        stack.clear();
        for (String token : exit){
            if(!calcAble)break;
            if(token.matches("[-]?[\\d]+([.][\\d]+)?")){
                stack.push(token);
            }
            else {
                double b = Double.parseDouble(stack.pop());
                double a = Double.parseDouble(stack.pop());
                double total=0;
                switch (token){
                    case "+": total = a+b; break;
                    case "-": total = a-b; break;
                    case "*": total = a*b; break;
                    case "/": if(b==0)calcAble=false;
                    else total=a/b; break;
                }
                String itog = String.valueOf(total);
                stack.push(itog);
            }
        }
        if (!(stack.size()==1)){
            calcAble = false;
        }

        if(calcAble){
            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);
            Double d = Double.parseDouble(stack.pop());
            String total = df.format(d);
            total = total.replace(",",".");
            return total;
        } else {
            return null;
        }

    }
}
