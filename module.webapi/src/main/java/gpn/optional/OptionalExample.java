package gpn.optional;

import java.util.Optional;

public class OptionalExample {
//    public static void main(String[] args) {

        // 1 Optional.of
//        Optional<String> name = Optional.of(null);
//        System.out.println(name);

        // 2 Optional.ofNullable
//        Optional<String> name2 = Optional.ofNullable(null);
//        System.out.println(name2);

        // 3 Optional.empty для создания пустого Optional
//        Optional<String> emptyOptional = Optional.empty();
//        System.out.println(emptyOptional);

        // 4 Optional isPresent
//        Optional<String> name = Optional.of("John"); // Optional.empty()
//        if (name.isPresent()) {
//            System.out.println(name.get());
//        }

        // 5 Optional orElse
//        Optional<String> name = Optional.of("John"); //Optional.empty()
//        System.out.println(name.orElse("blank")); //output John

        // 6 Optional orElseThrow
//        Optional<String> name = Optional.of("John"); //Optional.empty()
//        String nameValue = name.orElseThrow(() -> new RuntimeException());
//        System.out.println(nameValue);

        // 7  Optional map
//        Optional<String> name = Optional.of("JOHN"); //Optional.empty()
//        System.out.println(name.map(String::toLowerCase));
 //   }

    public static void main(String[] args) {

// ----
//        Optional<String> name = Optional.of("John");
//        System.out.println(name); //output Optional[John]
// ----

// ----
        //В метод Optional.of нельзя передавать null, если конечно мы не хотим получить NullPointerException
//
//        Optional<String> name = Optional.of(null); // java.lang.NullPointerException
//        System.out.println(name);

        // - Optional.ofNullable
        // А вот в метод Optional.ofNullable передавать null можно безопасно
// ----

// ----
//        Optional<String> name = Optional.ofNullable(null);
//        System.out.println(name); //output Optional[John]

//        String john = null;
//        Optional<String> name = Optional.ofNullable(john);
//        System.out.println(name); //output Optional.empty
// ----


// ----
        // Для получения значения из Optional используется метод Optional.get,
        // но он является небезопасным и может бросить NoSuchElementException

//        Optional<String> name = Optional.of("John");
//        System.out.println(name.get()); //output John
//
//        Optional<Object> nullOptional = Optional.ofNullable(null);
//        System.out.println(nullOptional.get()); // java.util.NoSuchElementException: No value present
// ----



// ----
//        Optional isPresent
//        Метод Optional.isPresent возвращает true,
//        если значение в нем присутствует, иначе возвращает false
//        Метод Optional.get лучше использовать в паре с Optional.isPresent чтобы предотвратить исключения

//        Optional<String> name = Optional.of("John");
//        if (name.isPresent()) {             //условие выполнится и мы увидим имя
//            System.out.println(name.get()); //output John
//        }
////
//        Optional<Object> empty = Optional.empty();
//        if (empty.isPresent()) {            //условие не выполнится, значит исключения не будет
//            System.out.println(empty.get());
//        }
// ----



// ----
//        Optional ifPresent
//        Метод Optional.ifPresent выполняет переданное действие, если значение в Optional присутствует,
//        иначе игнорирует его. Метод принимает лямбда-выражение известное как потребитель (Consumer).

//        Optional<String> name = Optional.of("John");
//        name.ifPresent(val -> System.out.println(val)); //условие выполнится и мы увидим John
//
//        Optional<Object> empty = Optional.empty();
//        empty.ifPresent(val -> System.out.println(val)); //условие не выполнится, действие игнорируется
// ----


// ----
//        Метод Optional.orElse возвращает переданное значение, если Optional пустой
//        Optional<String> name = Optional.of("John");
//        System.out.println(name.orElse("blank")); //output John
//
//        Optional<Object> empty = Optional.empty();
//        System.out.println(empty.orElse("blank")); //output blank
// ----


// ----
//        Optional orElseGet
//        Метод Optional.orElseGet возвращает переданное значение из лямда-выражение , если Optional пустой
//        Optional<String> name = Optional.of("John");
//        System.out.println(name.orElseGet(() -> "blank")); //output John
//
//        Optional<Object> empty = Optional.empty();
//        System.out.println(empty.orElseGet(() -> "blank")); //output blank
// ----



// ----
//        Optional orElseThrow
//        Метод Optional.orElseThrow бросает переданное исключение , если Optional пустой
//        Optional<String> name = Optional.of("John");
//        String nameValue = name.orElseThrow(() -> new RuntimeException());
//        System.out.println(nameValue);                                      //output John
//
//        Optional<Object> empty = Optional.empty();
//        Object emptyValue = empty.orElseThrow(() -> new RuntimeException()); //java.lang.RuntimeException
// ----


// ----
//        Optional map
//        Метод Optional.map служит для преобразования значения внутри Optional.
//        Если Optional пустой преобразование не будет происходить
//
//        Optional<String> name = Optional.of("JOHN");
//        System.out.println(name.map(String::toLowerCase));  //output Optional[john]
//
//        Optional<String> empty = Optional.empty();
//        System.out.println(empty.map(String::toLowerCase)); //output Optional.empty
// ----


// ----
//        Optional flatMap
//        Метод Optional.flatMap преобразовывает значение внутри Optional, но при этом не оборачивает их
//
//        Optional<Optional<String>> name = Optional.of(Optional.of("JOHN"));
//        Optional<String> lowerCaseName = name.flatMap(o -> o.map(String::toLowerCase));
//        System.out.println(lowerCaseName);  //output Optional[john]
//
//        Optional<Optional<String>> empty = Optional.of(Optional.empty());
//        System.out.println(empty.flatMap(o -> o.map(String::toLowerCase))); //output Optional.empty
// ----


//        Предыдущий пример можно упростить, если использовать статические ссылки на методы:
//
//        Converter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("123");
//        System.out.println(converted);   // 123
//


        //        Java 8 позволяет вам передавать ссылки на методы или конструкторы.
//        Для этого нужно использовать ключевое слово ::. Предыдущий пример
//        иллюстрирует передачу ссылки на статический метод. Однако мы также можем ссылаться на экземплярный метод:

        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"




//        Давайте посмотрим, как передавать ссылки на конструкторы.
//        Сперва определим бин с несколькими конструкторами:
        // class Person
// Затем определим интерфейс фабрики, которая будет использоваться для создания новых персон
   // PersonFactory
// Теперь вместо реализации интерфейса мы соединяем все вместе при помощи ссылки на конструктор:

//        PersonFactory<Person> personFactory = Person::new;
//        Person person = personFactory.create("Peter", "Parker");

//Мы создаем ссылку на конструктор с помощью Person::new.
//Компилятор автоматически выбирает подходящий конструктор,
//сигнатура которого совпадает с сигнатурой PersonFactory.create.


    }

}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}


class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}


interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}