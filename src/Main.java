import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println(persons);


        //Количество несовершеннолетних (люди младше 18 лет)
        long numberOfMinor = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(numberOfMinor);


        System.out.println("__________________________________________________________________________");


        //Cписок фамилий призывников (мужчинs от 18 и до 27 лет).
        List<String> conscriptSurname = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscriptSurname);


        System.out.println("__________________________________________________________________________");


        //Отсортированный по фамилии список потенциально работоспособных людей
        // с высшим образованием
        // от 18 до 60 лет для женщин и до 65 лет для мужчин
        List<Person> workingMan = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60 ||
                        person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65)
                .sorted((Comparator.comparing(Person::getFamily)))
                .collect(Collectors.toList());
        System.out.println(workingMan);

    }
}

