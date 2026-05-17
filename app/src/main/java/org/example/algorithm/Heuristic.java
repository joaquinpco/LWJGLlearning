package org.example.algorithm;

public class Heuristic {
    private PathNode current, goal;

    public Heuristic() {
    }

    public Heuristic(PathNode current, PathNode goal) {
        this.current = current;
        this.goal = goal;
    }

    /**
     * Finds the shortest path from a start node to a goal node using the A*
     * algorithm.
     * 
     * @param current  the starting PathNode for pathfinding
     * @param goal     the target PathNode to reach
     * @param walkable a 2D boolean array representing the navigation grid, where
     *                 {@code true}
     *                 indicates a walkable tile and {@code false} indicates an
     *                 obstacle or wall
     * @return a {@link java.util.List} of {@link PathNode} objects representing the
     *         path from
     *         start to goal, or an empty list if no path is found
     * 
     * @see PathNode
     * @see Heuristic
     */
    public void calculateCosts() {
        current.sethCost( Math.abs(goal.getX() - current.getX()) + Math.abs(goal.getY() - current.getY())); // Manhattan                                                                                           // distance
        current.setfCost(current.getgCost() + current.gethCost());
    }
}
