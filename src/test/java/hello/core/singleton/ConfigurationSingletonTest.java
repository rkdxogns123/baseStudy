package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest
{
    @Test
    void configurationTest()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);


        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService => memberRepository = " + memberRepository1);
        System.out.println("orderService => memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

    }

    @Test
    void configurationDeep()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        //정상적이면 class hello.core.AppConfig 이렇게 출력이되어야함
        //but 현실은 class hello.core.AppConfig$$EnhancerBySpringCGLIB$$8d524624
        //why? 스프링이 CGLIB라는 바이트코드 조작 라이브러리르 사용해서 AppConfig클래스를 상속받은
        //임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링빈으로 등록한 것.
        System.out.println("bean = " + bean.getClass());

    }
}
