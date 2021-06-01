import org.junit.jupiter.api.Test;
import model.Victory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VictoryTest {
    @Test
    void winColTest(){
        assertTrue(Victory.winCol(new int[][]{
                {0,0,0,0},
                {1,1,1,0},
                {0,0,0,0},
                {0,1,0,0},
                {0,0,0,0}
        },1,2));

    }

    @Test
    void winRowTest(){
        assertTrue(Victory.winRow(new int[][]{
                {1,0,0,0},
                {1,0,0,0},
                {1,0,0,0},
                {0,1,0,0},
                {0,0,0,0}
        },0,0));
    }

    @Test
    void winLeftLeaTest(){
        assertTrue(Victory.winLeftLea(new int[][]{
                {0,0,0,0},
                {0,1,0,1},
                {0,0,1,0},
                {0,0,0,1},
                {0,0,0,0}
        },1,1));
    }

    @Test
    void winRightLeaTest(){
        assertTrue(Victory.winRightLea(new int[][]{
                {0,0,1,0},
                {0,1,0,0},
                {1,0,0,0},
                {0,1,0,0},
                {0,0,0,0}
        },1,1));
    }
}
