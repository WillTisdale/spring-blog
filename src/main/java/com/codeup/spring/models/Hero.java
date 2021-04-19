package com.codeup.spring.models;

import com.codeup.spring.dao.DaoFactory;
import jdk.jfr.Unsigned;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unsigned
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mainStat;

    @Column(nullable = false)
    private String intelligence;

    @Column(nullable = false)
    private String agility;

    @Column(nullable = false)
    private String strength;

    @Column(nullable = false)
    private String health;

    @Column(nullable = false)
    private String physicalAttack;

    @Column(nullable = false)
    private String magicAttack;

    @Column(nullable = false)
    private String armor;

    @Column(nullable = false)
    private String magicDefense;

    @Column
    private String dodge;

    @Column
    private String armorPenetration;

    @Column
    private String magicPenetration;

    @Column
    private String vampirism;

    @Column
    private String critHitChance;

    public Hero(){}

    public Hero(long id, String main_stat, String name, String intelligence, String agility, String strength, String health, String physical_attack, String magic_attack, String armor, String magic_defense, String dodge, String armor_penetration, String magic_penetration, String vampirism, String crit_hit_chance) {
        this.id = id;
        this.mainStat = main_stat;
        this.name = name;
        this.intelligence = intelligence;
        this.agility = agility;
        this.strength = strength;
        this.health = health;
        this.physicalAttack = physical_attack;
        this.magicAttack = magic_attack;
        this.armor = armor;
        this.magicDefense = magic_defense;
        this.dodge = dodge;
        this.armorPenetration = armor_penetration;
        this.magicPenetration = magic_penetration;
        this.vampirism = vampirism;
        this.critHitChance = crit_hit_chance;
    }

     public String showHeroInfo(){
        String info = "Name: " + getName() + "\n" +
                "Main Stat: " + getMainStat() + "\n" +
                "Intelligence: " + getIntelligence() + "\n" +
                "Agility: " + getAgility() + "\n" +
                "Strength: " + getStrength() + "\n" +
                "Health: " + getHealth() + "\n" +
                "Physical Attack: " + getPhysicalAttack() + "\n" +
                "Magic Attack: " + getMagicAttack() + "\n" +
                "Armor: " + getArmor() + "\n" +
                "Magic Defense: " + getMagicDefense() + "\n";
        if(getDodge() != null){
            info += "Dodge: " + getDodge() + "\n";
        }
        if(getArmorPenetration() != null){
            info += "Armor Penetration: " + getArmorPenetration() + "\n";
        }
        if(getMagicPenetration() != null){
            info += "Magic Penetration: " + getMagicPenetration() + "\n";
        }
        if(getVampirism() != null){
            info += "Vampirism: " + getVampirism() + "\n";
        }
        if(getCritHitChance() != null){
            info += "Critical Hit: " + getCritHitChance() + "\n";
        }
        return info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainStat() {
        return mainStat;
    }

    public void setMainStat(String mainStat) {
        this.mainStat = mainStat;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getAgility() {
        return agility;
    }

    public void setAgility(String agility) {
        this.agility = agility;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(String physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public String getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(String magicAttack) {
        this.magicAttack = magicAttack;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(String magicDefense) {
        this.magicDefense = magicDefense;
    }

    public String getDodge() {
        return dodge;
    }

    public void setDodge(String dodge) {
        this.dodge = dodge;
    }

    public String getArmorPenetration() {
        return armorPenetration;
    }

    public void setArmorPenetration(String armorPenetration) {
        this.armorPenetration = armorPenetration;
    }

    public String getMagicPenetration() {
        return magicPenetration;
    }

    public void setMagicPenetration(String magicPenetration) {
        this.magicPenetration = magicPenetration;
    }

    public String getVampirism() {
        return vampirism;
    }

    public void setVampirism(String vampirism) {
        this.vampirism = vampirism;
    }

    public String getCritHitChance() {
        return critHitChance;
    }

    public void setCritHitChance(String critHitChance) {
        this.critHitChance = critHitChance;
    }
}
