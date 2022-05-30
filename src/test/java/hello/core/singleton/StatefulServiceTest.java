package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest
{
    @Test
    void statefulServiceSingleton()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB : B사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자 A가 주문 금액 조회
        //int price = statefulService1.getPrice();

        //결과값 20000원. 즉, A사용자 이후 주문된 B에 A사용자의 정보가 덮어씌워짐.
        //price필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경했기 때문.
        //즉, 스프링은 항상 무상태로 설계해야 한다.
        //System.out.println("price = " + statefulService1);
        //이렇게 바꾸면 10000으로 뜬다.
        System.out.println("price = " + userAPrice);

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig
    {
        @Bean
        public StatefulService statefulService()
        {
            return new StatefulService();
        }
    }

}