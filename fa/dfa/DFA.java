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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
    }

    @Override
    public State getState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public boolean isFinal(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinal'");
    }

    @Override
    public boolean isStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStart'");
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
