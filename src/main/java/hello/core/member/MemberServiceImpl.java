package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//MemberService에 대한 구현체
@Component
public class MemberServiceImpl implements MemberService{

    //DIP 위반하고 있는중 : why? 추상화랑 구현체를 동시에 사용중
    private final MemberRepository memberRepository;

    @Autowired//자동 의존관계 주입. ac.getBean(MemberRepository.class)가 자동으로 들어감
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스으 용도
    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }
}
