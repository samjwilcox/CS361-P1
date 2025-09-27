package fa.dfa;

import java.util.Iterator;
import java.util.Set;

import fa.State;

/**
 * DFA concrete class that implements the DFAInterface.
 * @author Sam Wilcox; Tyler Fernandez
 */
public class DFA implements DFAInterface {

    // Instance Variables
    private Set<State> states;
    private Set<Character> sigma;
    private State startState;
    private Set<State> finalStates;

    @Override
    public boolean addState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addState'");
    }

    @Override
    public boolean setFinal(String name) {
        Iterator<State> iter = states.iterator();
        while (iter.hasNext()) { // Iterate through states to find if name exists
            State current = (State) iter.next();
            if (current.getName().equals(name)) { // If found, add to final states & return true
                finalStates.add(current);
                return true;
            }
        }

        return false; // Name doesn't exist in states, return false
    }

    @Override
    public boolean setStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStart'");
    }

    @Override
    public void addSigma(char symbol) {
        if (sigma.contains(symbol)) {
            System.out.println("Transition already exists");
        } else {
            sigma.add(symbol);
        }
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        Iterator<State> iter = states.iterator();
        while (iter.hasNext()) { // Iterate through states to find if name exists
            State current = (State) iter.next();
            if (current.getName().equals(name)) { // If found, return state
                return current;
            }
        }

        return null; // Name doesn't exist in states, return null
    }

    @Override
    public boolean isFinal(String name) {
        Iterator<State> iter = finalStates.iterator();
        while (iter.hasNext()) { // Iterate through final states to find if name exists
            State current = (State) iter.next();
            if (current.getName().equals(name)) { // State is final, return true
                return true;
            }
        }

        return false; // Name doesn't exist in final states, return false
    }

    @Override
    public boolean isStart(String name) {
        if(startState.getName().equals(name)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }
}
