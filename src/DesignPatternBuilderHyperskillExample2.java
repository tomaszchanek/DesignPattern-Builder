import java.util.List;
import java.util.Scanner;

/**
 * Builder interface describe step of object creation.
 **/
interface Builder {
    Builder setType(String type);

    Builder setLanguages(List<String> languages);

    Builder setExperience(int experience);
}

/**
 * ConcreteComponent - Geek.
 **/
class Geek {

    private final String type;
    private final List<String> languages;
    private final int experience;

    Geek(String type, List<String> languages, int experience) {
        this.type = type;
        this.languages = languages;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Type : " + type + "\n" +
                "Languages : " + languages + "\n" +
                "Experience : " + experience + " years";
    }

}

/**
 * Concrete Builder build Geek component.
 **/
class GeekBuilder implements Builder {

    private String type;
    private List<String> languages;
    private int experience;

    @Override
    public GeekBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public GeekBuilder setLanguages(List<String> languages) {
        this.languages = languages;
        return this;
    }

    @Override
    public GeekBuilder setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public Geek getResult() {
        return new Geek(this.type, this.languages, this.experience);
    }
}

/**
 * Builder Director.
 */
class GeekDirector {
    private final Builder builder;

    public GeekDirector(Builder builder) {
        this.builder = builder;
    }

    public void buildAdmin() {
        this.builder
                .setType("Admin")
                .setLanguages(List.of("Perl", "PowerShell"))
                .setExperience(10);
    }

    public void buildBackend() {
        this.builder.
                setType("Backend").
                setLanguages(List.of("Python", "PHP")).
                setExperience(5);
    }

    public void buildRockstar() {
        this.builder.
                setType("Rockstar").
                setLanguages(List.of("Java", "Kotlin", "Scala", "Angular")).
                setExperience(20);
    }
}

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String geekName = scanner.nextLine();
        final String geekType = scanner.nextLine();

        scanner.close();

        GeekBuilder builder = new GeekBuilder();
        GeekDirector director = new GeekDirector(builder);
        Geek geek;

        switch (geekType) {
            case "Rockstar" -> {
                director.buildRockstar();
                geek = builder.getResult();
            }
            case "Backend" -> {
                director.buildBackend();
                geek = builder.getResult();
            }
            case "Admin" -> {
                director.buildAdmin();
                geek = builder.getResult();
            }
            default -> {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Geek " + geekName + " created.");
        System.out.println(geek);
    }
}