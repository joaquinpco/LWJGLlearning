package org.example;

import org.example.algorithm.AStarPathFinder;
import org.example.algorithm.PathNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AStartFinderTest {
    @Test
    void findPathTest() {
        boolean[][] walkable = {
            {true, true, true, true},
            {true, false, true, true},
            {true, true, true, true},
            {true, true, true, true}
        };

        PathNode start = new PathNode(0, 0);
        PathNode goal = new PathNode(2, 2);
        AStarPathFinder aStarPathFinder = new AStarPathFinder();

        List<PathNode> path = aStarPathFinder.findPath(start, goal, walkable);

        assertFalse(path.isEmpty(), "Expected a path to be found");
        assertEquals(5, path.size(), "Shortest Manhattan path around the obstacle should have length 5");
        assertEquals(start, path.get(0), "Path should begin at the start node");
        assertEquals(goal, path.get(path.size() - 1), "Path should end at the goal node");

        for (int i = 1; i < path.size(); i++) {
            PathNode previous = path.get(i - 1);
            PathNode currentNode = path.get(i);
            int dx = Math.abs(previous.getX() - currentNode.getX());
            int dy = Math.abs(previous.getY() - currentNode.getY());
            assertEquals(1, dx + dy, "Each move should be a single cardinal step");
        }
    }
}
