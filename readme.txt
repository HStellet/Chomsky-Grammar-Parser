This document contains information about the various classes used in the program.

1) Helper Classes:
  a) ContextFreeGrammar-> this builds the context free grammar in CNF form and the object of the class contains information about variables, terminals,starting symbol and rules of the grammar.
  b) Rule-> builds the rule (example:- E->AB)
  c) Terminal-> It is used to store the terminals
  d) Variable-> It is used to store the variables.
  e) Word-> It stores the right hand side of the production rules.
  f) Symbol->superclass to store objects of Variable class and Terminal class.

2)
  2.1) Main Classes (algorithm1):
    a) MyGrammar-> It makes an object of the context free grammar and returns it to main class from where it was called.
    b) Parser-> It implements the function 'isInLanguage(cfg,word)' which helps to check if the given word(string) can be made from the given grammar.
                It also implements the generateParseTree fucntion which generates the parse tree of the given string using the cnf form cfg.
    c) Recursion-> It implements the recursive function which returns true if it is possible to reach the string from the given grammar else it returns false.
    d) Main-> The program initiates from here. It invokes the Recursion class  that tells if a particular string can be made out of the given grammar and also builds the parse tree.

  2.2) Main Classes (algorithm2):
    a) MyGrammar-> It makes an object of the context free grammar and returns it to main class from where it was called.
    b) Parser-> It implements the function 'isInLanguage(cfg,word)' which helps to check if the given word(string) can be made from the given grammar.
                It also implements the generateParseTree fucntion which generates the parse tree of the given string using the cnf form cfg.
    c) CYK-> this class implements the famous CYK algorithm to check if a string can be made from the given grammar or not. This algorithm is implemented using dynamic programming.
    d) Main-> The program initiates from here. It invokes the Recursion class  that tells if a particular string can be made out of the given grammar and also builds the parse tree.

3) Steps to test the application:
  a) in the main directory where Main.java is contained, run 'javac *.java' (compiles all the files).
  b) then run 'java Main'
  c) this will open a menu
  d) choose option 2 to test on system given inpus and follow the on screen instructions.
  e) choose option 3 to test on the grammar and strings given in assignment.
