import Models.BakedGoods;
import Resources.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class bakedGoodTest {
        public LinkedList<BakedGoods> list = new LinkedList<>();

        @BeforeEach
        void setUp() {
            BakedGoods bg1 = new BakedGoods("1", "France", "Dough");
            BakedGoods bg2 = new BakedGoods("2", "Italy", "Pizza");
            list.add(bg1);
            list.add(bg2);
        }

        @AfterEach
        void tearDown() {
            list.remove();

        }

        @Test
        void getBakedGoodName() {
            assertEquals("Pie", ((BakedGoods) list.get(0)).getBakedGoodName());
            assertEquals("Bread", ((BakedGoods) list.get(1)).getBakedGoodName());

        }

        @Test
        void setBakedGoodNameTest() {
            assertEquals("Pie", ((BakedGoods) list.get(0)).getBakedGoodName());
            ((BakedGoods) list.get(0)).setBakedGoodName("Bread");
            assertEquals("Bread", ((BakedGoods) list.get(0)).getBakedGoodName());

        }

        @Test
        void getOriginCountryTest() {
            assertEquals("France", ((BakedGoods) list.get(0)).getCountryOfOrigin());
            assertEquals("Italy", ((BakedGoods) list.get(1)).getCountryOfOrigin());
        }

        @Test
        void setOriginCountryTest() {
            assertEquals("France", ((BakedGoods) list.get(0)).getCountryOfOrigin());
            ((BakedGoods) list.get(0)).setCountryOfOrigin("Italy");
            assertEquals("Italy", ((BakedGoods) list.get(0)).getCountryOfOrigin());

        }
    }