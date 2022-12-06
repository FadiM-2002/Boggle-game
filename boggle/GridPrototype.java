package boggle;

/**
 * An interface used for user story 1.4 (copy grid) which is implemented by
 * the Prototype pattern
 */
public interface GridPrototype {
    /**
     * Clones the grid that this method is called on
     */
    public BoggleGrid clone();

}
