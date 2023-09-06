import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.ExampleTDD;

public class ExampleTDDTest {
    @Test
    public void calculateTest(){
        ExampleTDD exampleTDD = new ExampleTDD();
        Assertions.assertEquals(3, exampleTDD.calculate(1, 2, '+'));
        Assertions.assertEquals(-1, exampleTDD.calculate(1, 2, '-'));
        Assertions.assertEquals(2, exampleTDD.calculate(1, 2, '*'));
    }

}
