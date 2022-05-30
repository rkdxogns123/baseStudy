package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//어플리케이션의 실제 동작에 필요한 구현객체를 생성한다.
//생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입 해준다. => 이것이 의존성 주입.
//이것을 통해 DIP를 지킬 수 있음.

@Configuration
public class AppConfig {


    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    // => 이러면 싱글톤이 깨지는게 아닌가?

    //생성자 주입
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService()
    {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy()
    {
        return new FixDiscountPolicy();
        //return new RateDiscountPolicy();
    }

}
