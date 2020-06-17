import java.io.*;
import java.util.*;
import computation.contextfreegrammar.*;

public class Recursion{
  public ArrayList<String> word=new ArrayList<String>();
  public boolean flag=false;
  public String startingSymbol;
  public ArrayList<String> terminals = new ArrayList<String>();
  public ArrayList<String> nonTerminals = new ArrayList<String>();
  public TreeMap<String,ArrayList<String>> grammar = new TreeMap<>();
  public Stack<ArrayList<String>> st1=new Stack<ArrayList<String>>();
  public static Stack<ArrayList<String>> st=new Stack<ArrayList<String>>();

  Recursion(String w,ContextFreeGrammar cfg)
  {
    for(int i=0;i<w.length();i++)
    {
      word.add(String.valueOf(w.charAt(i)));
    }
    System.out.println(word);
    startingSymbol=cfg.getStartVariable().toString();
    for(Terminal element:cfg.getTerminals())
    {
      this.terminals.add(element.toString());
    }
    for(Variable element:cfg.getVariables())
    {
      this.nonTerminals.add(element.toString());
    }
    for(Rule rule:cfg.getRules())
    {
      String var=rule.getVariable().toString();
      String wordCheck=rule.getExpansion().toString();
      if(!grammar.containsKey(var))
      {
        grammar.put(var, new ArrayList<String>());
      }
      grammar.get(var).add(wordCheck);
    }

  }
  public void checkIfExists(ArrayList<String> str,int countTer,int count1)
  {

    if(str.size()==1 && ("").equals(str.get(0)))
    {
      st.pop();
      return;

    }
    if(count1>2*(word.size())-1)
    {
      st.pop();
      return;
    }
    if(this.flag==true)
    {
      st.pop();
      return;
    }
    if(countTer>word.size())
    {
      st.pop();
      return;
    }
    if(str.equals(word) && count1==2*(word.size())-1)
    {

      st1=st;
      // st.pop();

      this.flag=true;
      return;
    }
    int cnt=0;
    for(String s:str)
    {
      if(terminals.contains(s))
        cnt++;
    }
    if(cnt==str.size())
    {
      st.pop();
      return;
    }
    int count3;
    // int k=0;
    for(String i:str)
    {
      // k++;
      if(nonTerminals.contains(i))
      {
        for(String j:grammar.get(i))
        {
          ArrayList<String> arr1=new ArrayList<String>();

          if(!j.equals("ε"))
          {
            for(int k=0;k<j.length()-1;k++)
            {
              if(j.charAt(k+1) =='₀' || j.charAt(k+1) =='₁' || j.charAt(k+1) =='₂'||j.charAt(k+1) == '₃'||j.charAt(k+1) == '₄'||j.charAt(k+1) == '₅'||j.charAt(k+1) == '₆'||j.charAt(k+1) == '₇'||j.charAt(k+1) == '₈'||j.charAt(k+1) == '₉')
              {
                arr1.add(String.valueOf(j.charAt(k))+String.valueOf(j.charAt(k+1)));
                k++;
              }
              else
              {
                arr1.add(String.valueOf(j.charAt(k)));
              }
            }
            int k=j.length()-1;
            if(!(j.charAt(k) =='₀' || j.charAt(k) =='₁' || j.charAt(k) =='₂'||j.charAt(k) == '₃'||j.charAt(k) == '₄'||j.charAt(k) == '₅'||j.charAt(k) == '₆'||j.charAt(k) == '₇'||j.charAt(k) == '₈'||j.charAt(k) == '₉'))
            {
              arr1.add(String.valueOf(j.charAt(k)));
            }
          }
          else{
            arr1.add("");
          }

          ArrayList<String> arr2=new ArrayList<String>();
          int flag1=0;
          count3=count1;
          for(String k:str)
          {
            if(k.equals(i) && flag1==0)
            {
              count3++;
              for(String k1:arr1)
              {
                arr2.add(k1);
              }
              flag1=1;
            }
            else
            {
              arr2.add(k);
            }
          }
          int count=0;
          for(String l:arr2)
          {
            if(terminals.contains(l))
              count++;
          }
          st.push(arr2);
          checkIfExists(arr2,count,count3);
        }
      }
    }
    if(this.flag==false)
      st.pop();
  }
  public boolean resultCheck()
  {
    return this.flag;
  }
  public void printStack()
  {

    for(ArrayList<String> l:st1)
    {
      System.out.println(l);

    }
  }

}
