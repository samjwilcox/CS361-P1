package fa.dfa;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
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

    /**
     * Constructor for setting up the instance variables.
     */
    public DFA() {
        this.states = new LinkedHashSet<>();
        this.sigma = new LinkedHashSet<>();
        this.finalStates = new LinkedHashSet<>();
        this.startState = null;
    }

    @Override
    public boolean addState(String name) {
        Iterator<State> iter = states.iterator();

        while (iter.hasNext()) {
            State current = (State) iter.next();

            if (current.getName().equals(name)) { // If found, return false since it exists
                return false;
            }
        }

        // If we got here, no states matched, so add the state and return true
        states.add(new DFAState(name));
        return true;
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
        Iterator<State> iter = states.iterator();
        while (iter.hasNext()) { // Iterate through states to find if name exists
            State current = (State) iter.next();
            if (current.getName().equals(name)) { // If found, make start state & return true
                startState = current;
                return true;
            }
        }

        return false; // Name doesn't exist in states, return false
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
        if (startState == null || s == null) return false; // Start state or s cannot be null to continue.
        State current = startState;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!sigma.contains(c)) return false; // Symbol is not part of the language
            State next = ((DFAState) current).getTransition(c);
            if (next == null) return false; // Missing an edge
            current = next;
        }

        // We will accept if the end state is final
        return finalStates.contains(current);
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
        if (!sigma.contains(onSymb)) return false; // If the sigma set does not contain the symbol, return false

        State from = getState(fromState);
        State to = getState(toState);

        if (from == null || to == null) return false; // If from or to are null, return false

        DFAState detFrom = (DFAState) from;

        if (detFrom.getTransition(onSymb) != null) { // If not null, this would most likely create two transitions on the same symbol
            return false;
        }

        detFrom.putTransition(onSymb, to);
        return true;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        DFA dfa = new DFA();
        for (char c : this.sigma) dfa.addSigma(c); // Copy sigma into our new DFA object
        Map<String, State> linkedName = new LinkedHashMap<>();

        for (State s : this.states) { // Loop through our states and add it to our new DFA object
            dfa.addState(s.getName());
            linkedName.put(s.getName(), dfa.getState(s.getName()));
        }

        // Set our start and final
        if (this.startState != null) dfa.setStart(this.startState.getName());
        for (State s : this.finalStates) dfa.setFinal(s.getName());

        // Flag to let us know whether to swap or not
        boolean swapFlag = sigma.contains(symb1) && sigma.contains(symb2);

        // Go through each state and compare symbols
        for (State s : this.states) {
            DFAState dfaState = (DFAState) s;

            for (char c : this.sigma) {
                State target;

                if (swapFlag) {
                    if (c == symb1) { // If the symbol is equal to symbol 1, then set to symbol 2
                        target = dfaState.getTransition(symb2);
                    } else if (c == symb2) { // If the symbol is equal to symbol 2, then set to symbol 1
                        target = dfaState.getTransition(symb1);
                    } else { // On all other cases, set to the current loop character
                        target = dfaState.getTransition(c);
                    }
                } else { // Not swapping, so set to the current loop character
                    target = dfaState.getTransition(c);
                }

                // As long as the target is not null, then we add the transition
                if (target != null) {
                    dfa.addTransition(s.getName(), target.getName(), c);
                }
            }
        }

        return dfa;
     }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Start with Q
        sb.append("Q = {");

        Iterator<State> statesIter = this.states.iterator();

        if (statesIter.hasNext()) {
            sb.append(' ').append(statesIter.next().getName());

            while (statesIter.hasNext()) {
                sb.append(' ').append(statesIter.next().getName());
            }

            sb.append(' ');
        }

        sb.append("}\n");

        // Now for Sigma
        sb.append("Sigma = {");

        Iterator<Character> sigmaIter = this.sigma.iterator();

        if (sigmaIter.hasNext()) {
            sb.append(' ').append(sigmaIter.next());

            while (sigmaIter.hasNext()) {
                sb.append(' ').append(sigmaIter.next());
            }

            sb.append(' ');
        }

        sb.append("}\n");

        // Now for Delta
        sb.append("delta =\n");

        if (!this.sigma.isEmpty()) {
            sb.append(' ');

            for (char c : sigma) {
                sb.append(' ').append(c);
            }

            sb.append("\n");
        }

        for (State s : this.states) {
            sb.append(s.getName());

            for (char c : this.sigma) {
                State st = ((DFAState) s).getTransition(c);
                sb.append(' ').append(st == null ? "-" : st.getName());
            }

            sb.append("\n");
        }

        // Now for q0
        sb.append("q0 = ").append(this.startState == null ? "-" : this.startState.getName()).append("\n");

        // Now for F
        sb.append("F = {");

        Iterator<State> finalIter = this.finalStates.iterator();

        if (finalIter.hasNext()) {
            sb.append(' ').append(finalIter.next().getName());

            while (finalIter.hasNext()) {
                sb.append(' ').append(finalIter.next().getName());
            }

            sb.append(' ');
        }

        sb.append("}");

        return sb.toString();
    }
}
