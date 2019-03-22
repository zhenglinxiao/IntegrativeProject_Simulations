/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populationdynamics;

import java.util.Objects;

/**
 *
 * @author lxz19
 */
public class AnimalPreset {
    
    private final String name;
    private final int type;
    private final int lifespan;
    private final int offspring;
    private final int iniPop;
    private final int capacity;
    
    public AnimalPreset(String n, int t, int ls, int os, int po, int c){
        name = n;
        type = t;
        lifespan = ls;
        offspring = os;
        iniPop = po;
        capacity = c;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getLifespan() {
        return lifespan;
    }

    public int getOffspring() {
        return offspring;
    }

    public int getIniPop() {
        return iniPop;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnimalPreset other = (AnimalPreset) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
