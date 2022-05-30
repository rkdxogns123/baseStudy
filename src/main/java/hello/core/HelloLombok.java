package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok
{
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("aasdfq");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
