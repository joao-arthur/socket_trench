package socketTrench.engine

import org.junit.Test
import kotlin.test.assertEquals

public class EngineTest {
    @Test
    fun BoxDimTest() {
        val boxDim = BoxDim(1, 2, 3, 4)
        assertEquals("BoxDim(x=1, y=2, w=3, h=4)", boxDim.toString())
        assertEquals(1, boxDim.x)
        assertEquals(2, boxDim.y)
        assertEquals(3, boxDim.w)
        assertEquals(4, boxDim.h)
    }

    @Test
    fun BoxPosTest() {
        val boxPos = BoxPos(1, 2, 3, 4)
        assertEquals("BoxPos(x1=1, y1=2, x2=3, y2=4)", boxPos.toString())
        assertEquals(1, boxPos.x1)
        assertEquals(2, boxPos.y1)
        assertEquals(3, boxPos.x2)
        assertEquals(4, boxPos.y2)
    }

    @Test
    fun PointTest() {
        val point = Point(1, 2)
        assertEquals("Point(x=1, y=2)", point.toString())
        assertEquals(1, point.x)
        assertEquals(2, point.y)
    }
}
