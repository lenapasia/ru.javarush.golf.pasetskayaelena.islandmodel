import org.junit.Assert;
import org.junit.Test;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.Randomizer;

public class RandomizerTest {

    @Test
    public void random() {
        int r = Randomizer.rnd(1, 3);
        System.out.println(r);
        Assert.assertTrue(r >=1 && r <= 3);
    }
}
