package sample.cafekiosk.unit.beverage;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AmericanoTest {
    @Test
    void getName() {
        // Americano americano = new Americano();
        // assertEquals(americano.getName(), "아메리카노");
        // assertThat(americano.getName()).isEqualTo("아메리카노");

        Americano americano = new Americano();
        assertThat(americano.getName()).isEqualTo("아메리카노");

    }

    @Test
    void getPrice() {

    }
}
