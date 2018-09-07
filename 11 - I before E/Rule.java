import java.util.*;

/** @authors Ryan Spear, Kent Loh, Logan Richard, Angus McMillan
    COSC 326 summer school 2018
**/

public class Rule{
    String unallowed;
    ArrayList<String> exceptions = new ArrayList<String>();
    
    public Rule(String unallowed, ArrayList<String> exceptions){
        this.unallowed = unallowed;
        this.exceptions = exceptions;
    }

    public Rule(String unallowed){
        this.unallowed = unallowed;
    }

    public void addException(String exception){
        this.exceptions.add(exception);
    }

    public String getRule(){
        return unallowed;
    }

    public ArrayList<String> getExceptions(){
        if(exceptions.isEmpty()){
            return null;
        }
        return exceptions;
    }

}
