package zookeeper;

public class Bear extends Animal {

    public Bear() {
        super();
        Animal.numOfBears++;
    }

    public Bear(String sex, int age, int weight, String animalName,
                String animalID, String animalBirthDate, String animalColor,
                String animalOrigin, String animalArrivalDate) {
        super(sex, age, weight, animalName, animalID, animalBirthDate, animalColor,
              animalOrigin, animalArrivalDate);
        Animal.numOfBears++;
    }
}
