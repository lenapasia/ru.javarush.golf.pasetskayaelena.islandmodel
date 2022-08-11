import org.junit.Assert;
import org.junit.Test;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.DirectionType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Coordinates;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.DirectionUtils;

import static org.junit.Assert.*;

public class DirectionUtilsTest {

    @Test
    public void getAvailableDirections() {
        final int width = 3;
        final int height = 3;

        DirectionType[] dr = DirectionUtils.getAvailableDirections(0, 0, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Down, DirectionType.Right}, dr);

        DirectionType[] drl = DirectionUtils.getAvailableDirections(0, 1, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Down, DirectionType.Right, DirectionType.Left}, drl);

        DirectionType[] dl = DirectionUtils.getAvailableDirections(0, 2, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Down, DirectionType.Left}, dl);

        DirectionType[] udr = DirectionUtils.getAvailableDirections(1, 0, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Up, DirectionType.Down, DirectionType.Right}, udr);

        DirectionType[] udrl = DirectionUtils.getAvailableDirections(1, 1, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Up, DirectionType.Down, DirectionType.Right, DirectionType.Left}, udrl);

        DirectionType[] udl = DirectionUtils.getAvailableDirections(1, 2, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Up, DirectionType.Down, DirectionType.Left}, udl);

        DirectionType[] ur = DirectionUtils.getAvailableDirections(2, 0, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Up, DirectionType.Right}, ur);

        DirectionType[] url = DirectionUtils.getAvailableDirections(2, 1, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Up, DirectionType.Right, DirectionType.Left}, url);

        DirectionType[] ul = DirectionUtils.getAvailableDirections(2, 2, width, height);
        Assert.assertArrayEquals(new DirectionType[]{DirectionType.Up, DirectionType.Left}, ul);
    }

    @Test
    public void calculateCoordinates() {
        final int width = 3;
        final int height = 3;

        Coordinates coordinates = DirectionUtils.calculateCoordinates(0, 0, DirectionType.Right, 1, width, height);
        Assert.assertTrue(coordinates.getX() == 0 && coordinates.getY() == 1);

        Coordinates coordinates1 = DirectionUtils.calculateCoordinates(1, 1, DirectionType.Left, 2, width, height);
        Assert.assertTrue(coordinates1.getX() == 1 && coordinates1.getY() == 0);

        Coordinates coordinates2 = DirectionUtils.calculateCoordinates(0, 2, DirectionType.Up, 2, width, height);
        Assert.assertTrue(coordinates2.getX() == 0 && coordinates2.getY() == 2);

        Coordinates coordinates3 = DirectionUtils.calculateCoordinates(0, 1, DirectionType.Down, 3, width, height);
        Assert.assertTrue(coordinates3.getX() == 2 && coordinates3.getY() == 1);
    }
}
