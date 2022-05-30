package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price)
    {
        if (member.getGradle() == Gradle.VIP)
        {
            return discountFixAmount;
        }
        else
        {
            return 0;
        }

    }
}