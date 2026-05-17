package org.example.algorithm;

import java.util.Objects;

public class PathNode {
    public int x, y;
    public float gCost; // Cost from start to this node
    public float hCost; // Heuristic cost to goal
    public float fCost; // Total cost (g + h)
    public PathNode parent; // Parent node for path reconstruction

    public PathNode() {
    }

    public PathNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float gethCost() {
        return hCost;
    }

    public float getfCost() {
        return fCost;
    }

    public float getgCost() {
        return gCost;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void sethCost(float hCost) {
        this.hCost = hCost;
    }

    public void setfCost(float fCost) {
        this.fCost = fCost;
    }

    public void setgCost(float gCost) {
        this.gCost = gCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PathNode)) {
            return false;
        }
        PathNode pathNode = (PathNode) o;
        return x == pathNode.x && y == pathNode.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
