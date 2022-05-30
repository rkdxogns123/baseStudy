package hello.core.autowired;

import hello.core.beanfind.ApplicationContextTest;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest
{
    @Test
    void AutoWiredOption()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    //빈 없는거 아무거나 넣음
    static class TestBean
    {
        //(required = false) -> 의존관계 없으면 호출 안시킴.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1)
        {
            System.out.println("noBean1 = " + noBean1);
        }

        //null뜸.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2)
        {
            System.out.println("noBean2 = " + noBean2);
        }

        //Optional.empty뜸.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3)
        {
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
