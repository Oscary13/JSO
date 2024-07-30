/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebascerti;

class Animal {
    
    public String noise(){
        return "peep";
    }
    public static void main(String[] args) {
        Animal animal = new Dog();
        Cat cat = (Cat)animal;
        System.out.println(cat.noise());
        
    }

}
class Dog extends Animal{
    public  String noise(){
        return "bark";
    }
}

class Cat extends  Animal{
    public String noise(){
        return "meow";
    }
}
