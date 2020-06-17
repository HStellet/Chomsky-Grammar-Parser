import computation.contextfreegrammar.*;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;



public class MyGrammar {
	public static ContextFreeGrammar makeGrammar() {

		Set<Terminal> terminals=new HashSet<Terminal>();
		terminals.add(new Terminal('+'));
		terminals.add(new Terminal('*'));
		terminals.add(new Terminal(')'));
		terminals.add(new Terminal('('));
		terminals.add(new Terminal('x'));

		Set<Variable> variables=new HashSet<Variable>();
		variables.add(new Variable('E'));
		variables.add(new Variable('F'));
		variables.add(new Variable('T'));
		Variable[] vars=Variable.subscriptedVariables('E',7);
		for(int i=0;i<7;i++)
		{
			variables.add(vars[i]);
		}
		Variable startSymbol = new Variable('E');

		List<Rule> rules=new ArrayList<Rule>();

		rules.add(new Rule(new Variable('E'),new Word("x")));
		rules.add(new Rule(new Variable('T'),new Word("x")));
		rules.add(new Rule(new Variable('F'),new Word("x")));
		rules.add(new Rule(new Variable("E1"),new Word("+")));
		rules.add(new Rule(new Variable('E'),new Word(new Variable('E'), new Variable("E0"))));
		rules.add(new Rule(new Variable("E0"),new Word(new Variable("E1"), new Variable('T'))));
		rules.add(new Rule(new Variable("T"),new Word(new Variable('T'), new Variable("E2"))));
		rules.add(new Rule(new Variable("E2"),new Word(new Variable("E3"), new Variable('F'))));
		rules.add(new Rule(new Variable("E3"),new Word("*")));
		rules.add(new Rule(new Variable('F'),new Word(new Variable("E4"), new Variable("E5"))));
		rules.add(new Rule(new Variable("E5"),new Word(")")));
		rules.add(new Rule(new Variable("E4"),new Word(new Variable("E6"), new Variable('E'))));
		rules.add(new Rule(new Variable("E6"),new Word("(")));


		ContextFreeGrammar cfg=new ContextFreeGrammar(variables,terminals,rules,startSymbol);
		// System.out.println(cfg);
		// You can write your code here to make the context-free grammar from the assignment

		return cfg;
	}
}
