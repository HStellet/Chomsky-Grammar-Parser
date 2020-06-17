import computation.contextfreegrammar.*;
import computation.parser.*;
import computation.parsetree.*;
import computation.derivation.*;
import java.io.*;
import java.util.*;

public class Parser implements IParser {
  public static ArrayList<ArrayList<String>> stEntry=new ArrayList<ArrayList<String>>();
  public static Integer i=-1;
  public boolean isInLanguage(ContextFreeGrammar cfg, Word w){
    // CYK obj=new CYK(w.toString(),cfg);
    // return obj.doSteps();
    Stack<ArrayList<String>> st=new Stack<ArrayList<String>>();
    ArrayList<String> start=new ArrayList<String>();
    Recursion obj1=new Recursion(w.toString(),cfg);
    start.add(obj1.startingSymbol);
    st.push(start);

    Recursion.st=st;
    obj1.checkIfExists(start,0,0);
    obj1.printStack();
    return obj1.resultCheck();
    // return false;
  }
  public ParseTreeNode generateParseTree(ContextFreeGrammar cfg, Word w) {
    // ParseTreeNode p;
    return null;
  }
}
