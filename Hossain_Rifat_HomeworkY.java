import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// import java.util.Stack;   // You will probably want to make use of this

// Homework Y:  Expression trees.

// In the project you need to code one class that extends the abstract class ExpressionTree.  
// Your class needs to code one constructor and one output method as well as any helper methods
// that you find useful.
// The goal is to process mathematical expressions that are provided as strings.

// In the main program that tests your class,
// the Utility getInput method reads a mathematical expression that is typed as input.
// The expression can contain any combination of numbers, variable identifiers and mathematical operations.
// These mathematical operations are +, -, *, /, ( and )

// The constructor should turn an input expression into the content of a binary (expression) tree.
// The tree can then be printed in prefix, postfix, or fully parenthesised infix notation.
// The first two of these methods have been coded for you in the abstract class ExpressionTree, you need to code the third.

// For example the expression   5 + (x / y + z * 7) - 2  would be printed as
//                              - + 5 + / x y * z 7 2  in prefix order
//  or                          5 x y / z 7 * + + 2 -  in postfix order
//  or               (( 5 + ((x / y) + (z * 7))) - 2)  in fully parenthesised infix notation.


// To simplify things in this project the - sign can only be used between 2 quantities that are being subtracted.
// This will prevent you from ever working with a negative number such as -2 which would involve a different
// use for the - character.  However the simplification means that your code does not need to work out which meaning
// to attach to any copy of a - character.  

// Another simplification for this project is that you may assume that only correct mathematical expressions are entered.
// You do not need to check that an input String from getInput makes sense as an expression.
// If an illegal expression is encountered, your program can behave in any way that is convenient for you.

// You should change the class name Y00000000 so that the last 8 digits are your QC ID number.
// You should only make changes inside this class.
// You do not need any more than 100 lines of code in the class.
// After you have tested your work on venus (as well as in your development environment)
// cut the file above the class Utility and submit it by email.
// See also the general homework guidelines for other instructions before you submit work. 

// The hard part of this assignment is the constructor.  
// In terms of planning it, I suggest that you think about which operation is the last to be performed in the expression.
// Can you write down a rule to identify this operation? Deal with the easier case when there are no parentheses first.
//
// Once you accomplish this you can finish quickly using a recursion.
//
// The fullyParenthesised method can also be done using a recursion --- 
//     this will require an auxiliary recursive method to call on.

// Suggested strategy:
// 1. Make a constructor that deals with expressions without parentheses  (1st step partial credit).
// 2. Make a fullyParenthesised output method  (2nd step partial credit).
// 3. Adapt the constructor to deal with parentheses.  Hint a Stack will help here.  (Last part of credit).

public class Y15357366 extends ExpressionTree {

   public static void main(String args[]) {
      Y15357366 y = new Y15357366("5 + 6 / 7");
      Utility.print(y);
      y = new Y15357366(Utility.getInput());
      Utility.print(y);
   }
   
   public static boolean hasOperands(String x){
      if(plusOrMinus(x) || multOrDiv(x)){
         return true;
      }
      return false;
   }
   
   public static boolean plusOrMinus(String x){
      if(x.contains("+") || x.contains("-")){
         return true;
      }
      return false;
   }
   
   public static boolean multOrDiv(String x){
      if(x.contains("*") || x.contains("/")){
         return true;
      }
      return false;
   }
   
   public static Integer lastIndexMultOrDiv(String x){
      int index =0;
      if(x.lastIndexOf("*") > x.lastIndexOf("/")){
         index = x.lastIndexOf("*");
      }else index = x.lastIndexOf("/");
     
      return index;
   }
   
   public static Integer lastIndexPlusOrMinus(String x){
      int index =0;
      if(x.lastIndexOf("+") > x.lastIndexOf("-")){
         index = x.lastIndexOf("+");
      }else index = x.lastIndexOf("-");
     
      return index;
   }
     
   public String fullyParenthesised() {
      // add implementation here
      String ans = "";
      
      ArrayList<BNode<String>> l = this.inOrder(); 
      for (Node<String> b:l) ans += "(" + b.getData() + " )";
      return ans;
   }
   
   public Y15357366(String s) {
      super();
      // add implementation here
      BinaryTree<String> g = new BinaryTree<>();
      BNode<String> pointer = null;
      s = s.replaceAll("\\s+","");
 
      try {
           if(g.root == null){
                 if(plusOrMinus(s)){
                    int i = lastIndexPlusOrMinus(s);
                    g.addRoot(Character.toString(s.charAt(i)));
                    pointer = (BNode<String>) g.root();
                    g.addLeft(pointer, s.substring(0, i));
                    g.addRight(pointer, s.substring(i+1, s.length()));
                 } else if(multOrDiv(s)){
                    int i = lastIndexMultOrDiv(s);
                    g.addRoot(Character.toString(s.charAt(i)));
                    pointer = (BNode<String>) g.root();
                    g.addLeft(pointer, s.substring(0, i));
                    g.addRight(pointer, s.substring(i+1, s.length()));
                 }
              }
           if(g.root != null){
              String temp = null;
             if(hasOperands(pointer.getLeft().getData())){
                temp = pointer.getLeft().getData();
             } else temp = pointer.getRight().getData();
             if(plusOrMinus(temp)){
                int i = lastIndexPlusOrMinus(temp);
                if(hasOperands(pointer.getLeft().getData())){
                   pointer.getLeft().setData(Character.toString(temp.charAt(i)));
                   pointer = pointer.getLeft();
                } else {
                   pointer.getRight().setData(Character.toString(temp.charAt(i)));
                   pointer = pointer.getRight();
                }
                g.addLeft(pointer, temp.substring(0, i));
                g.addRight(pointer, temp.substring(i+1, temp.length())); 
             }
             if(multOrDiv(temp)){
                int i = lastIndexMultOrDiv(temp);
                if(hasOperands(pointer.getLeft().getData())){
                   pointer.getLeft().setData(Character.toString(temp.charAt(i)));
                   pointer = pointer.getLeft();
                } else {
                   pointer.getRight().setData(Character.toString(temp.charAt(i)));
                   pointer = pointer.getRight();
                }
                g.addLeft(pointer, temp.substring(0, i));
                g.addRight(pointer, temp.substring(i+1, temp.length())); 
             }
           }
           this.root = g.root();
         
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}

//-------  cut here.  Place your new code above this line and submit only the ------
//-------  code above this line as your homework.  Do not make any code changes ----
//-------  below this line.---------
