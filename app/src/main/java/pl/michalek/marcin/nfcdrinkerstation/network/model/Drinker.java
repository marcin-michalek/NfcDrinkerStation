/**
 * Created by Marcin Michałek on 2015-01-02.
 *
 */
package pl.michalek.marcin.nfcdrinkerstation.network.model;

import java.io.Serializable;

/**
 *
 * @author Marcin Michałek
 */
public class Drinker implements Serializable{
  private String name;
  private double age;
  private double weight;
  private double height;
  private String stomach;
  private String gender;
  private String alcoholKind;
  private double alcoholVoltage;
  private double shotCapacity;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getAge() {
    return age;
  }

  public void setAge(double age) {
    this.age = age;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public String getStomach() {
    return stomach;
  }

  public void setStomach(String stomach) {
    this.stomach = stomach;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAlcoholKind() {
    return alcoholKind;
  }

  public void setAlcoholKind(String alcoholKind) {
    this.alcoholKind = alcoholKind;
  }

  public double getAlcoholVoltage() {
    return alcoholVoltage;
  }

  public void setAlcoholVoltage(double alcoholVoltage) {
    this.alcoholVoltage = alcoholVoltage;
  }

  public double getShotCapacity() {
    return shotCapacity;
  }

  public void setShotCapacity(double shotCapacity) {
    this.shotCapacity = shotCapacity;
  }
}
