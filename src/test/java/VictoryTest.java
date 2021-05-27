import org.junit.jupiter.api.Test;
import pojo.Victory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VictoryTest {
    @Test
    void winColtest(){
        assertTrue(Victory.winCol(new int[][]{
                {0,0,0,0},
                {1,1,1,0},
                {0,0,0,0},
                {0,1,0,0},
                {0,0,0,0}
        },1,2));

    }

    @Test
    void winRowtest(){
        assertTrue(Victory.winRow(new int[][]{
                {1,0,0,0},
                {1,0,0,0},
                {1,0,0,0},
                {0,1,0,0},
                {0,0,0,0}
        },0,0));
    }

    @Test
    void winLeftLeatest(){
        assertTrue(Victory.winLeftLea(new int[][]{
                {0,0,0,0},
                {0,1,0,1},
                {0,0,1,0},
                {0,0,0,1},
                {0,0,0,0}
        },1,1));
    }

    @Test
    void winRightLeatest(){
        assertTrue(Victory.winRightLea(new int[][]{
                {0,0,1,0},
                {0,1,0,0},
                {1,0,0,0},
                {0,1,0,0},
                {0,0,0,0}
        },1,1));
    }
}
