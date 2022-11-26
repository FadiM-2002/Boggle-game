package boggle;

import java.util.HashMap;
import java.util.Map;

public class GridPrototypeRegistry {
    /**
     * A hashmap that is used to store the clone of Boggle grids
     */
    private Map<String, BoggleGrid> registry = new HashMap<>();

    /**
     * Stores the clone of a Boggle grid to registry
     */
    public void addGrid(String gridName, BoggleGrid grid) {
        this.registry.put(gridName, grid.clone());
    }

    /**
     * Gets the clone of a Boggle grid from registry by using the grid name
     */
    public BoggleGrid getGridByName(String gridName) {
        return this.registry.get(gridName);
    }
}
