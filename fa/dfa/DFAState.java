package fa.dfa;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import fa.State;

/**
 * DFAState class that extends the State class.
 * @author Sam Wilcox; Tyler Fernandez
 */
public class DFAState extends State {
    private final String name;
    private final Map<Character, State> delta = new HashMap<>();

    /**
     * Constructor that creates a new DFAState object instance.
     * @param name the name for the state.
     */
    public DFAState(String name) {
        super(name);
        this.name = name;
    }

    /**
     * Returns the name of the state.
     * @return the name of the state
     */
    public String getName() {
        return name;
    }

    /**
     * Get the transition.
     * @param c the character to get transition for.
     * @return the transition for the given character.
     */
    public State getTransition(char c) {
        return delta.get(c);
    }

    /**
     * Put the given character into the transition.
     * @param c the character to place into the transition
     * @param to the state for the character.
     */
    public void putTransition(char c, State to) {
        delta.put(c, to);
    }

    /**
     * Returns whether the given object equals this current DFAState.
     * @return true if equals, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DFAState)) return false;
        DFAState that = (DFAState) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Returns the object's hash code.
     * @return this DFAState object's hash code string.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     * Override the toString method.
     * @return the name of this DFAState object.
     */
    @Override
    public String toString() {
        return getName();
    }
}