package org.example.game;

public enum Difficulty {
    EASY(7.0f),
    MEDIUM(17.0f),
    HARD(60.0f);

    private final float enemySpeed;

    Difficulty(float enemySpeed) {
        this.enemySpeed = enemySpeed;
    }

    public float getEnemySpeed() {
        return enemySpeed;
    }
}
