import java.io.*;
import java.util.*;
import computation.contextfreegrammar.*;


public class CYK{

    public String word;
    public String startingSymbol;
    public boolean isTokenWord = false;
    public ArrayList<String> terminals = new ArrayList<String>();
    public ArrayList<String> nonTerminals = new ArrayList<String>();
    public TreeMap<String,ArrayList<String>> grammar = new TreeMap<>();

    CYK(String w,ContextFreeGrammar cfg)
    {
      this.word=w;
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
    public boolean doSteps(){
        String[][] cykTable = createCYKTable();
        return drawTable(doCyk(cykTable));
    }
    public String getWord(String[] args){
        if(!isTokenWord) { return args[1]; }
        String[] argsWithoutFile = new String[args.length - 1];
        for(int i = 1; i < args.length; i++){
            argsWithoutFile[i-1] = args[i];
        }
        return toString(argsWithoutFile);
    }


    public boolean drawTable(String[][] cykTable){

        if(cykTable[cykTable.length-1][cykTable[cykTable.length-1].length-1].contains(startingSymbol)){
            return true;
        }else{
            return false;
          }
    }

    public int findLongestString(String[][] cykTable){
        int x = 0;
        for(String[] s : cykTable){
            for(String d : s){
                if(d.length() > x){ x = d.length(); }
            }
        }
        return x;
    }

//Jagged Array for the Algorithm
    public String[][] createCYKTable (){
        int length = isTokenWord ? toArray(word).length : word.length();
        String[][] cykTable = new String[length + 1][];
        cykTable[0] = new String[length];
        for(int i = 1; i < cykTable.length; i++){
            cykTable[i] = new String[length - (i - 1)];
        }
        for(int i = 1; i < cykTable.length; i++){
            for(int j = 0; j < cykTable[i].length; j++){
                cykTable[i][j] = "";
            }
        }
        return cykTable;
    }

    public String[][] doCyk(String[][] cykTable){
    //Step 1: Fill header row
        for(int i = 0; i < cykTable[0].length; i++){
            cykTable[0][i] = manageWord(word, i);
        }
    //Step 2: Get productions for terminals
        for(int i = 0; i < cykTable[1].length; i++){
            String[] validCombinations = checkIfProduces(new String[] {cykTable[0][i]});
            cykTable[1][i] = toString(validCombinations);
        }
        if(word.length() <= 1) { return cykTable; }
    //Step 3: Get productions for subwords with the length of 2
        for(int i = 0; i < cykTable[2].length; i++){
            String[] downwards = toArray(cykTable[1][i]);
            String[] diagonal = toArray(cykTable[1][i+1]);
            String[] validCombinations = checkIfProduces(getAllCombinations(downwards, diagonal));
            cykTable[2][i] = toString(validCombinations);

        }
        if(word.length() <= 2){ return cykTable; }
    //Step 3: Get productions for subwords with the length of n
        TreeSet<String> currentValues = new TreeSet<String>();
System.out.print("\n");
        for(int i = 3; i < cykTable.length; i++){
            for(int j = 0; j < cykTable[i].length; j++){
                for(int compareFrom = 1; compareFrom < i; compareFrom++){
                    String[] downwards = cykTable[compareFrom][j].split("\\s");
                    String[] diagonal = cykTable[i-compareFrom][j+compareFrom].split("\\s");
                    String[] combinations = getAllCombinations(downwards, diagonal);
                    String[] validCombinations = checkIfProduces(combinations);

                    if(cykTable[i][j].isEmpty()){
                        cykTable[i][j] = toString(validCombinations);
                    }else{
                        String[] oldValues = toArray(cykTable[i][j]);
                        ArrayList<String> newValues = new ArrayList<String>(Arrays.asList(oldValues));
                        newValues.addAll(Arrays.asList(validCombinations));
                        currentValues.addAll(newValues);
                        cykTable[i][j] = toString(currentValues.toArray(new String[currentValues.size()]));
                    }

                }
                currentValues.clear();
            }
        }
        return cykTable;
    }

    public String manageWord(String word, int position){
        if(!isTokenWord){ return Character.toString(word.charAt(position)); }
        return toArray(word)[position];
    }

    public String[] checkIfProduces(String[] toCheck){
        ArrayList<String> storage = new ArrayList<>();
        for(String s : grammar.keySet()){
            for(String current : toCheck){
                if(grammar.get(s).contains(current)){
                    storage.add(s);
                }
            }
        }
        if(storage.size() == 0) { return new String[] {}; }
        return storage.toArray(new String[storage.size()]);
    }

    public String[] getAllCombinations(String[] from, String[] to){
        int length = from.length * to.length;
        int counter = 0;
        String[] combinations = new String[length];
        if(length == 0){ return combinations; };
        for(int i = 0; i < from.length; i++){
            for(int j = 0; j < to.length; j++){
                combinations[counter] = from[i] + to[j];
                counter++;
            }
        }
        return combinations;
    }

    public String toString(String[] input){
        return Arrays.toString(input).replaceAll("[\\[\\]\\,]", "");
    }

    public String[] toArray(String input){
        return input.split("\\s");
    }


}
