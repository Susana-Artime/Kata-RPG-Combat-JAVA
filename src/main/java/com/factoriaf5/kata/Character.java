package com.factoriaf5.kata;

public class Character {

    private int health;
    private int level;
    private boolean alive;
    private int attackRange;
    private boolean isMelee; 

    // Constructor
    public Character(boolean isMelee) {
        this.health = 1000;
        this.level = 1;
        this.alive = true;
        this.isMelee = isMelee;
        this.attackRange = isMelee ? 2 : 20;
    }

    public int getHealth() {
        return health;
    }





    public void setHealth(int health) {
        this.health = health;
    }





    public int getLevel() {
        return level;
    }





    public void setLevel(int level) {
        this.level = level;
    }





    public boolean isAlive() {
        return alive;
    }





    public void setAlive(boolean alive) {
        this.alive = alive;
    }





    public int getAttackRange() {
        return attackRange;
    }





    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }


    public boolean isMelee() {
        return isMelee;
    }


    public void setMelee(boolean isMelee) {
        this.isMelee = isMelee;
        this.attackRange = isMelee ? 2 : 20;
    }


    public void dealDamage(Character villan, int damage, int distance) {
        if (this != villan && villan.isAlive() && isInRange(villan, distance)) {
            if (villan.getLevel() >= this.level + 5) {
                damage *= 0.5; 
            } else if (villan.getLevel() <= this.level - 5) {
                damage *= 1.5;
            }
            villan.receiveDamage(damage);
        }
    }

    
    public void receiveDamage(int damage) {
        if (alive) {
            health -= damage;
            if (health <= 0) {
                health = 0;
                alive = false; 
            }
        }
    }

    
    public void heal(int healAmount) {
        if (this.isAlive()) { 
            health = Math.min(health + healAmount, 1000);
        }
    }


    private boolean isInRange(Character villan, int distance) {
        return distance <= this.attackRange;
    }

    public void displayStatus() {
        System.out.println("Health: " + health + ", Level: " + level + ", Alive: " + alive+ "attackRange: " + attackRange + "isMelee= " + isMelee);
    }

}



    


