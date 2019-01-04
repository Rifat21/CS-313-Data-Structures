// Homework X:  Implementation of a class to calculate with polynomials stored as a (doubly) linked list.
//
// The Utility.run method is designed to print a pair of polynomials together with their sum, difference,
// product, quotient and remainder.  

/* For the 2 polynomials included with the same main program that calls run, the output should be:
 
Polynomials
p =  X^5
q =  X^2 -X + 1.0
Sum  X^5 + X^2 -X + 1.0
Difference  X^5 -X^2 + X -1.0
Product  X^7 -X^6 + X^5
Quotient  X^3 + X^2 -1.0
Remainder  -X + 1.0

*/


//  Your task in this homework is to write a complete implementation for the
//  class X15357366 which is used to implement the polynomial operations.
//  You will need to store terms of a polynomial in the nodes of a linked list, the head
//  should store the term with highest degree and the tail the term with lowest degree.
//  The implementations of the classes for node, list and term are fully implemented for you.
//  An abstract class Polynomial provides a method to print polynomials.  You should make
//  use of all these features and not try to reimplement any of them.

//  You should change the name X15357366 so that the last 8 digits are your QC ID number.
//  You should only make changes inside this class.
//  You do not need any more than 200 lines of code in the class.
//  After you have tested your work on venus (as well as in your development environment,
//  cut the file at the indicated point above the abstract class Polynomial and submit it by email.
//  Do not make any changes to the code below this line.

// Grading policy:
// 1 Point for homework that compiles correctly
// 1 Point for homework that has a correct constructor that handles a String argument to specify a polynomial
// 1 Point for homework that correctly adds and subtracts polynomials
// 1 Point for homework that correctly multiplies, divides and finds a remainder for polynomials.
// Maximum homework score = 4 points.

// Advice:
// There are three main steps to overcome:
//  1. Write the constructor to enter data from a String giving a polynomial
//  2. Write the add method, the subtract method will be very similar
//  3. Write the multiply method, this helps with the divide method although that still needs
//     work.  The remainder method is then easy.
//
//  The main problems in the constructor are to break the input string apart into terms and to deal
//  with conventions about (shortcuts in) formulas for terms.  How can we tell that a point of the 
//  String is the beginning of a new term?  What missing parts of a term might need to be added in? 
//
//  The add method works rather similarly to the merge method of mergeSort.  Can you see why these
//  are similar?
//
//  The multiply method needs to repeatedly multiply a single term from the first polynomial by the
//  second polynomial and use the add method to add together the results.  

//  See also the general homework guidelines for other instructions before you submit work. 

import java.util.StringTokenizer;

public class X15357366 extends Polynomial {
   public static void main(String args[]) throws Exception {
      Polynomial p = new X15357366(" X^5"), 
            q = new X15357366("X^2 - X + 1");
      Utility.run(p, q);
   }
   
   public X15357366(String s) {
      // complete this code
      s = s.replaceAll("\\s+","");
      s = s.replaceAll("\\-", "+-");
      Term poly;
      
      StringTokenizer stoken = new StringTokenizer(s, "+");
      String[] sar = new String[stoken.countTokens()];
      for(int i= 0; i < sar.length; i++){
         sar[i] = stoken.nextToken();
         poly= new Term(); 
         String coeff;
         String power;
  
         if(sar[i].contains("X") && sar[i].indexOf("X") == 0){
            poly.setCoefficient(1.0);
            if(sar[i].contains("^")){
               power = sar[i].substring(sar[i].indexOf("^")+1, sar[i].length());
               poly.setDegree(Integer.parseInt(power));
            }else poly.setDegree(1);  
         }else if(sar[i].contains("-") && sar[i].indexOf("X") == 1){
            poly.setCoefficient(-1.0);
            if(sar[i].contains("^")){
               power = sar[i].substring(sar[i].indexOf("^")+1, sar[i].length());
               poly.setDegree(Integer.parseInt(power));
            }else poly.setDegree(1);
         }else if(sar[i].contains("X")){
            coeff = sar[i].substring(0, sar[i].indexOf("X"));
            poly.setCoefficient(Double.parseDouble(coeff));
            if(sar[i].contains("^")){
               power = sar[i].substring(sar[i].indexOf("^")+1, sar[i].length());
               poly.setDegree(Integer.parseInt(power));
            }else poly.setDegree(1);
         }else {
            poly.setCoefficient(Double.parseDouble(sar[i]));
            poly.setDegree(0);
         }
         if(data.isEmpty()){
            data.addFirst(poly);
            }else{
               data.addLast(poly);
            }
      }  
   }

   public X15357366() {
      super();
   }

   public Polynomial add(Polynomial p) {
      Polynomial ans = new X15357366();
      
      try {
        DNode<Term> a = this.data.getFirst();
        DNode<Term> b = p.data.getFirst();
        Term temp;

        
         while(a.getData()!= null){
            if(a.getData().getDegree() > b.getData().getDegree()){
               temp = new Term();
               temp = a.getData();
               if(ans.data.isEmpty()){
                  ans.data.addFirst(temp);
               }else ans.data.addLast(temp);
               a = a.getNext();
            } else if(a.getData().getDegree() == b.getData().getDegree()){
               temp = new Term();
               temp.setCoefficient(a.getData().getCoefficient() + b.getData().getCoefficient());
               temp.setDegree(a.getData().getDegree());
               if(ans.data.isEmpty()){
                  ans.data.addFirst(temp);
               }else ans.data.addLast(temp);
               a = a.getNext();
               b= b.getNext();
            } else if(a.getData().getDegree() < b.getData().getDegree()){
               temp = new Term();
               temp = b.getData();
               if(ans.data.isEmpty()){
                  ans.data.addFirst(temp);
               }else ans.data.addLast(temp);
               b = b.getNext();
               
               if(a.getData() != null && b.getData()==null){
                  while(a.getData() != null){
                     temp = new Term();
                     temp = a.getData();
                     if(ans.data.isEmpty()){
                        ans.data.addFirst(temp);
                     }else ans.data.addLast(temp);
                     a = a.getNext();
                     }
                  }
               }
         }
         
         if(a.getData() == null && b.getData()!=null){
            while(b.getData() != null){
               temp = new Term();
               temp = b.getData();
               if(ans.data.isEmpty()){
                  ans.data.addFirst(temp);
               }else ans.data.addLast(temp);
               b = b.getNext();
            }
         }
      
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return ans;
   }

   public Polynomial subtract(Polynomial p) {
      Polynomial ans = new X15357366();
      // complete this code
      try {
         DNode<Term> a = this.data.getFirst();
         DNode<Term> b = p.data.getFirst();
         Term temp;

         
          while(a.getData()!= null){
             if(a.getData().getDegree() > b.getData().getDegree()){
                temp = new Term();
                temp = a.getData();
                if(ans.data.isEmpty()){
                   ans.data.addFirst(temp);
                }else ans.data.addLast(temp);
                a = a.getNext();
             } else if(a.getData().getDegree() == b.getData().getDegree()){
                temp = new Term();
                temp.setCoefficient(a.getData().getCoefficient() - b.getData().getCoefficient());
                temp.setDegree(a.getData().getDegree());
                if(ans.data.isEmpty()){
                   ans.data.addFirst(temp);
                }else ans.data.addLast(temp);
                a = a.getNext();
                b= b.getNext();
             } else if(a.getData().getDegree() < b.getData().getDegree()){
                temp = new Term();
                temp.setCoefficient(-b.getData().getCoefficient());
                temp.setDegree(b.getData().getDegree());
                if(ans.data.isEmpty()){
                   ans.data.addFirst(temp);
                }else ans.data.addLast(temp);
                b = b.getNext();
                
                if(a.getData() != null && b.getData()==null){
                   while(a.getData() != null){
                      temp = new Term();
                      temp.setCoefficient(-a.getData().getCoefficient());
                      temp.setDegree(a.getData().getDegree());
                      if(ans.data.isEmpty()){
                         ans.data.addFirst(temp);
                      }else ans.data.addLast(temp);
                      a = a.getNext();
                      }
                   }
                }
          }
          
          if(a.getData() == null && b.getData()!=null){
             while(b.getData() != null){
                temp = new Term();
                temp.setCoefficient(-b.getData().getCoefficient());
                temp.setDegree(b.getData().getDegree());
                if(ans.data.isEmpty()){
                   ans.data.addFirst(temp);
                }else ans.data.addLast(temp);
                b = b.getNext();
             }
          }
       
       } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }
      return ans;
   }

   public Polynomial multiply(Polynomial p) {
      Polynomial ans = new X15357366();

      // complete this code
      try {
         DNode <Term> a = this.data.getFirst();
         DNode <Term> b = p.data.getFirst();
         Term temp;
         
         while(a.getData() != null){
            while(b.getData() != null){
               temp = new Term();
               temp.setCoefficient(a.getData().getCoefficient() * b.getData().getCoefficient());
               temp.setDegree(a.getData().getDegree() + b.getData().getDegree());
               if(ans.data.isEmpty()){
                  ans.data.addFirst(temp);
               }else ans.data.addLast(temp);
               b = b.getNext();
            }
            a = a.getNext();
            b = p.data.getFirst();
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
  
      return ans;
   }

   public Polynomial divide(Polynomial p) throws Exception {
      Polynomial ans = new X15357366();
      // complete this code
      DNode <Term> a = this.data.getFirst();
      DNode <Term> b = p.data.getFirst();
      Term temp;
      
      if(b.getData().getDegree()>a.getData().getDegree()){
         temp = new Term(0.0,0);
         ans.data.addFirst(temp);
      }else{
         
      }
      return ans;
   }

   public Polynomial remainder(Polynomial p) throws Exception {
      Polynomial ans = new X15357366();
      // complete this code
      
      DNode <Term> a = this.data.getFirst();
      DNode <Term> b = p.data.getFirst();
      Term temp;
      
      if(b.getData().getDegree()>a.getData().getDegree()){
         temp = new Term(0.0,0);
         ans.data.addFirst(temp);
      }else{
         
      }
      return ans;
   }
}
