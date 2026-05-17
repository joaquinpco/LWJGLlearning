package org.example.algorithm;

import java.util.ArrayList;
import java.util.List;

public class AStarPathFinder {

    private static final int[][] DIRECTIONS = {
        {0, -1},
        {0, 1},
        {-1, 0},
        {1, 0}
    };

    public AStarPathFinder() {
    }

    public List<PathNode> findPath(PathNode current, PathNode goal, boolean[][] walkable) {
        if (current == null || goal == null || walkable == null || walkable.length == 0) {
            return new ArrayList<>();
        }

        int height = walkable.length;
        int width = walkable[0].length;

        if (!isWithinBounds(current, width, height) || !isWithinBounds(goal, width, height)) {
            return new ArrayList<>();
        }

        if (!isWalkable(current, walkable) || !isWalkable(goal, walkable)) {
            return new ArrayList<>();
        }

        List<PathNode> openList = new ArrayList<>();
        List<PathNode> closeList = new ArrayList<>();

        current.setgCost(0);
        current.parent = null;
        new Heuristic(current, goal).calculateCosts();
        openList.add(current);

        while (!openList.isEmpty()) {
            int bestIndex = 0;
            PathNode currentNode = openList.get(0);

            for (int i = 1; i < openList.size(); i++) {
                PathNode candidate = openList.get(i);
                if (candidate.getfCost() < currentNode.getfCost()
                        || (candidate.getfCost() == currentNode.getfCost()
                                && candidate.gethCost() < currentNode.gethCost())) {
                    currentNode = candidate;
                    bestIndex = i;
                }
            }

            openList.remove(bestIndex);
            closeList.add(currentNode);

            if (currentNode.getX() == goal.getX() && currentNode.getY() == goal.getY()) {
                return reconstructPath(currentNode);
            }

            for (int[] direction : DIRECTIONS) {
                int neighborX = currentNode.getX() + direction[0];
                int neighborY = currentNode.getY() + direction[1];

                if (!isWithinBounds(neighborX, neighborY, width, height)) {
                    continue;
                }

                if (!walkable[neighborY][neighborX]) {
                    continue;
                }

                if (listContains(closeList, neighborX, neighborY)) {
                    continue;
                }

                float tentativeG = currentNode.getgCost() + 1;
                PathNode neighbor = getNodeFromList(openList, neighborX, neighborY);

                if (neighbor == null) {
                    neighbor = new PathNode(neighborX, neighborY);
                    neighbor.setgCost(tentativeG);
                    new Heuristic(neighbor, goal).calculateCosts();
                    neighbor.parent = currentNode;
                    openList.add(neighbor);
                } else if (tentativeG < neighbor.getgCost()) {
                    neighbor.setgCost(tentativeG);
                    new Heuristic(neighbor, goal).calculateCosts();
                    neighbor.parent = currentNode;
                }
            }
        }

        return new ArrayList<>();
    }

    private boolean isWithinBounds(PathNode node, int width, int height) {
        return node != null && isWithinBounds(node.getX(), node.getY(), width, height);
    }

    private boolean isWithinBounds(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private boolean isWalkable(PathNode node, boolean[][] walkable) {
        return walkable[node.getY()][node.getX()];
    }

    private boolean listContains(List<PathNode> list, int x, int y) {
        return getNodeFromList(list, x, y) != null;
    }

    private PathNode getNodeFromList(List<PathNode> list, int x, int y) {
        for (PathNode node : list) {
            if (node.getX() == x && node.getY() == y) {
                return node;
            }
        }
        return null;
    }

    private List<PathNode> reconstructPath(PathNode node) {
        List<PathNode> path = new ArrayList<>();
        PathNode current = node;
        while (current != null) {
            path.add(0, current);
            current = current.parent;
        }
        return path;
    }
}
