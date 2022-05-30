package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
//주문 클라이언트
public class OrderServiceImpl implements OrderService{


    /*
    이런 구조가 과연 SOLID를 잘 지켰는가?
    NONO. OCP, DIP 제대로 지키지 못했음.
    1)DIP위반 : OrderServiceImpl은 추상(인터페이스)에도 의존하고 구체적인 부분에도 의존하기 때문.
    2)OCP위반 : 고정할인을 퍼센트할인으로 바꾸는 순간 OrderServiceImpl의 코드도 변경해야됨.
    즉, 어플을 공연, 인터페이스를 배역이라고 생각하면,
    주연 배우가 다른 주연배우를 초빙하는 것과 비슷한 구조이다.
    그러나, 배역을 정하는 것은 공연 기획자이지 배우가 아니다.
    이 구조를 바꾸려면 공연기획자를 어플에 만들면 된다.
    이 역할은 AppConfig가 할 것. 구현객체를 생성하고 연결하는 책임을 가지는 별도의 클래스.

     */

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //할인정책 변경에 따라 코드가 변경됨.
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //인터페이스에만 의존하도록 설계와 코드를 변경.
    //private DiscountPolicy discountPolicy; =>이대로하면 절대 코드 안돌아감.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    //수정 주입
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository)
    {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    //수정 주입
    @Autowired
    public void DiscountPolicy(DiscountPolicy discountPolicy)
    {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */
    @Autowired
    //생성자가 있으면 무조건 값을 다 채워 넣어야한다.
    //생성자가 딱 1개 있으면 @Autowired 생략 가능

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice)
    {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }


}
