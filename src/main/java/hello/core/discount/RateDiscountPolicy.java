package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Gradle;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Primary //우선순위 정해주는 것.
public class RateDiscountPolicy implements DiscountPolicy{

    private int dicountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGradle() == Gradle.VIP)
        {
            return price * dicountPercent / 100;
        }
        else {
            return 0;
        }
    }
}