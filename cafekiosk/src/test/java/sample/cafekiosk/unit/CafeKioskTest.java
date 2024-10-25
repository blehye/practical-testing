package sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;
import java.time.*;

public class CafeKioskTest {
    @Test
    void add_manual_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println(">>> 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());
    }

    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    @Test
    void add() {
        // CafeKiosk cafeKiosk = new CafeKiosk();
        // cafeKiosk.add(new Americano());

        // assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        // assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
        // assertThat(cafeKiosk.getBeverages()).hasSize(1);

        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");

        // 한 개 넣었을 땐 사이즈와 그 한 개의 이름 확인
    }

    @Test
    void addSeveralBeverages() {
        // CafeKiosk cafeKiosk = new CafeKiosk();
        // Americano americano = new Americano();

        // cafeKiosk.add(americano, 2);
        
        // assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        // assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);

        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);

        // 여러 개 넣었을 땐 각각의 원소가 내가 넣은 원소가 맞는지 확인
    }

    @Test
    void addZeroBeverages() {
        // CafeKiosk cafeKiosk = new CafeKiosk();
        // Americano americano = new Americano();

        // assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
        //     .isInstanceOf(IllegalArgumentException.class)
        //     .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");

        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");

        // 일부러 예외를 발생시키고 그 예외 클래스와 예외 메세지를 확인
    }

    @Test
    void clear() {
        // CafeKiosk cafeKiosk = new CafeKiosk();
        // Americano americano = new Americano();
        // Latte latte = new Latte();
        
        // cafeKiosk.add(americano);
        // cafeKiosk.add(latte);
        // assertThat(cafeKiosk.getBeverages()).hasSize(2);

        // cafeKiosk.clear();
        // assertThat(cafeKiosk.getBeverages()).isEmpty();

        // 키오스크에 음료 2잔 넣고 사이즈가 2가 맞는지 체크
        // 클리어
        // isEmpty인지 확인

        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();

        assertThat(cafeKiosk.getBeverages()).isEmpty();

    }

    @Test
    void calculateTotalPrice() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();
        
        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        int totalPrice = cafeKiosk.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(8500);
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();

    }

    @Test
    void createOrderWithCurrentTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder(LocalDateTime.of(2024, 10, 16, 10, 0));
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderOutsideOpenTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024, 10, 16, 9, 59)))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }
}
