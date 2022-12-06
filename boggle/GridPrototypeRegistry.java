package boggle;

import java.util.HashMap;
import java.util.Map;

/**
 * A class used for storing grids which is part of the user story 1.4 (copy grid)
 */
public class GridPrototypeRegistry {
    /**
     * A hashmap that is used to store the clone of Boggle grids
     */
    private Map<String, BoggleGrid> registry = new HashMap<>();

    /**
     * Stores the clone of a Boggle grid to registry
     * @param gridName name of the grid to will be saved
     * @param grid the grid that will be saved
     */
    public void addGrid(String gridName, BoggleGrid grid) { this.registry.put(gridName, grid.clone()); }

    /**
     * Gets the clone of a Boggle grid from registry by using the grid name
     * @param gridName the name of the grid that will be returned
     * @return Boggle grid which is saved with the name gridName
     */
    public BoggleGrid getGridByName(String gridName) { return this.registry.get(gridName); }

    /**
     * Gets the number of grids currently saved in the registry
     * @return the number of grids saved in registry
     */
    public int getSavedGridNum() { return this.registry.size(); }

    /**
     * Checks whether a grid with the name gridName already exists in the registry
     * @param gridName the grid name to check if already exists in registry
     * @return returns true if a grid with gridName exists in registry, otherwise returns false
     */
    public boolean gridNameExists(String gridName) {
        return this.registry.containsKey(gridName);
    }


    public void printAllSavedGrids() {
        System.out.println("List of saved grids: ");
        for (String gridName : registry.keySet()) {
            System.out.println(gridName);
        }
        System.out.println("\n\n");
    }
}
