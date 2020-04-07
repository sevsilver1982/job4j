package search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("name1", "surname1", "12345", "address1")
        );
        var persons = phones.find("name1");
        assertEquals(
                "surname1",
                persons.iterator().next().getSurname()
        );
    }

}