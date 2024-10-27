package com.factoriaf5.kata;

public class Character {

    private int health;
    private int level;
    private boolean alive;

    // Constructor
    public Character() {
        this.health = 1000;
        this.level = 1;
        this.alive = true;
    }
    
    public void dealDamage(Character target, int damage) {

        if (this == target) {
            System.out.println("Un personaje no se puede inflingir daño a si mismo.");
            return; 
        }
            if (target.alive) {
                
                if (target.level >= this.level + 5) {
                    damage = (int) (damage * 0.5); 
                } else if (target.level <= this.level - 5) {
                    damage = (int) (damage * 1.5); 
                }

                health = health - damage; 
                if (health <= 0) {
                    health = 0; 
                    alive = false; 
                    System.out.println("Personaje muerto.");
                }
                } else {
                System.out.println("No se puede hacer daño a un personaje muerto.");
                }
    }
    

    public void heal() {
        if (this.alive) {
            if (this.health < 1000) {
                this.health += 100; 
                if (this.health > 1000) {
                    this.health = 1000;
                }
                System.out.println("Curar al personaje. Salud actual: " + this.health);
            } else {
                System.out.println("El personaje ya tiene la salud maxima.");
            }
        } else {
            System.out.println("Un personaje muerto no se puede curar.");
        }
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

    public void displayStatus() {
        System.out.println("Health: " + health + ", Level: " + level + ", Alive: " + alive);
    }


}



