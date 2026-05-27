package org.example.interfaces;

import java.util.List;

import org.example.algorithm.PathNode;

public interface AStar {

    /**
     * Finds a path from the current node to the goal node using the A* pathfinding algorithm.
     *
     * @param current the starting node for the pathfinding
     * @param goal the destination node to reach
     * @param walkable a 2D boolean array indicating which cells are traversable (true = walkable, false = obstacle)
     * @return a list of PathNode objects representing the path from current to goal, or an empty list if no path exists
     */
    public List<PathNode> findPath(PathNode current, PathNode goal, boolean[][] walkable);
}
