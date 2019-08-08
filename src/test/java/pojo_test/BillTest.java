package pojo_test;

import ua.mkorniie.model.pojo.Bill;

import static org.junit.jupiter.api.Assertions.fail;

public class BillTest implements PojoTestI {
    @Override
    public void constructorsTest() {
        try {
            Bill b = new Bill();
//            b = new Bill(1, true, );
        } catch (Exception e) {
            fail("These constructors shouldn't throw the exception");
        }

        try {
            Bill b = new Bill();
//            b = new Bill(1, true, );
        } catch (Exception e) {
        }
    }

    @Override
    public void gettersTest() {

    }

    @Override
    public void settersTest() {

    }

    @Override
    public void equalsTest() {

    }
}
