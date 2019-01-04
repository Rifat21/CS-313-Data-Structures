// Homework Z:  Counting words.

// In this project you will complete a word counting program.

// Your task is to write a complete implementation for the
// class Z15357366 which is used to count generic Objects.  The type X is a
// key for looking up objects and the type Y is the type of Objects to be entered.

// In our application, X is used as a String and represents the lower case version
// of a word (with any final letter s removed).
// In our application, Y is also used as a String and represents the word as found.

// The main method which tests your class uses it to construct a Counter object.
// The Utility run method operates with your Counter object. The method reads a file called
// text.txt (or a user specified file if this does not exist) and reports a count of words 
// in it. Two words are considered the same if their only difference is capitalization or
// a final letter s on the end.  The report lists all words that appear at least 10 times
// together with a count for the word and a list of all versions of the word that were found.
// A typical line of the report might read:
//   
// end:   total count 61, end, END, Ends, ends, End
//
// This means that end was found 61 times and appeared in 5 different forms as
//   end, END, Ends, ends and End.

//  You must complete an implementation for the
//  class Z15357366 which is used to count generic Objects. 

//  You might like to implement it as a Map adapter so that you don't have to code a Map ADT.
//  Would you prefer to adapt a java HashMap or TreeMap?
//  What type of keys should be used for the map?  What type of values do you need to access?
//  (Hint: I do not think that the Map that you store as an instance variable should use type
//         parameters X and Y.  Why not?)
//  If either of these keys or values are of a complex type (more than just one String) you should
//  consider implementing a class to store objects of this type).  You could place this
//  new class definition inside the code for Z15357366 to comply with the requirement that only
//  changes to this class should be made.

//  You should change the name Z15357366 so that the last 8 digits are your QC ID number.
//  You should only make changes inside this class.

//  You do not need any more than 100 lines of code in the class.
//  After you have tested your work on venus (as well as in your development environment)
//  cut the file above the interface Counter and class Utility and submit it by email.
//  See also the general homework guidelines for other instructions before you submit work. 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Z15357366<X extends Comparable<X>, Y> implements Counter<X, Y> {

      public static void main(String args[]) throws FileNotFoundException {
         Counter<String, String> x = new Z15357366<String, String>();
         Utility.run(x);
      }
      
      public String get(X word) {
         Object[] a = unique.keySet().toArray();
         ArrayList<String> b = new ArrayList<String>();
         String c = null;
         for(int i = 0; i < a.length; i++){
            c = a[i].toString();
            c = dropS(c);
            if(c.equals(word)){
               b.add((String) a[i]);
            }
         }
         return "Total count " + getCount(word) + ", " + b.toString();
      }

      public int getCount(X word) {
         // implement this
         int i= count.get(word);
         return i;
      }

      public Set<X> keySet() {
         // implement this
         Set<String> values = map.keySet();
         return (Set<X>) values;
      }

      public void put(X keyWord, Y word) {
         // implement this
         
         unique.put(word.toString(), word.toString());
         map.put(keyWord.toString(), word.toString());
         
         if(count.containsKey(keyWord.toString())){
            count.put(keyWord.toString(), count.get(keyWord) +1 );
         }else count.put(keyWord.toString(), 1);
      }

      // declare any required instance variables or helpful auxiliary types and methods here
      TreeMap<String, String> map = new TreeMap<String, String>();
      TreeMap<String, String> unique = new TreeMap<String, String>();
      TreeMap<String, Integer> count =  new TreeMap<String, Integer>();
      
      public String dropS(String word) {
         String ans = word.toLowerCase();
         if (ans.endsWith("s"))
            return ans.substring(0, ans.length() - 1);
         return ans;
      }
}

// -------  cut here.  Place your new code above this line and submit only the ------
// -------  code above this line as your homework.  Do not make any code changes ----
// -------  below this line.                                                ---------

interface Counter<X extends Comparable<X>, Y> {
   int getCount(X word);
   Set<X> keySet();
   void put(X keyWord, Y word);
   String get(X word);
}

class Utility {
   public static void run(Counter<String, String> words) throws FileNotFoundException{
      Scanner terminal = new Scanner(System.in);
      System.out.println("Give the name of a file for analysis:");
      String fileName = terminal.next();
      Scanner input = new Scanner(new File(
            "C:\\Users\\Rifat\\Documents\\" + fileName));
      input.useDelimiter("\\W+");

      while (input.hasNext()) {
         String word = input.next();
         String keyWord = Utility.dropS(word);
         words.put(keyWord, word);
      }

      for (String word : words.keySet())
         if (words.getCount(word) >= 10)
            System.out.println(word +":\t" + words.get(word));

      input.close();
      terminal.close();
   }
   
   static String dropS(String word) {
      String ans = word.toLowerCase();
      if (ans.endsWith("s"))
         return ans.substring(0, ans.length() - 1);
      return ans;
   }
}