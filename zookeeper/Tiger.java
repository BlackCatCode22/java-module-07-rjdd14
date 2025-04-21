package zookeeper;

public class Tiger extends Animal {

    public Tiger() {
        super();
        Animal.numOfTigers++;
    }

    public Tiger(String sex, int age, int weight, String animalName,
                 String animalID, String animalBirthDate, String animalColor,
                 String animalOrigin, String animalArrivalDate) {
        super(sex, age, weight, animalName, animalID, animalBirthDate, animalColor,
              animalOrigin, animalArrivalDate);
        Animal.numOfTigers++;
    }
}
