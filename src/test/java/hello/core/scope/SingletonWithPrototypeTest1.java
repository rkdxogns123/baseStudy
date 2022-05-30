package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1
{
    @Test
    void prototypeFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype()
    {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean
    {
        //private final PrototypeBean prototypeBean;//생성시점에 주입 x01

        //싱글톤과 프로토타입을 함께 사용할 수 있게 Provider기능 사용.
        @Autowired
        //ObjectProvider 스프링에 의존하는 DL정도의 기능만 제공하는 것.
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        //Provider<PrototypeBean>를 사용한 것.
        private Provider<PrototypeBean> prototypeBeanProvider;
        //@Autowired
        //ApplicationContext applicationContext;
/*
        @Autowired
        ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }*/

        public int logic()
        {
            //제일 무식한 방법으로 실행할때마다 호출
            //PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;
        public void addCount()
        {
            count++;
        }

        public int getCount()
        {
            return count;
        }

        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy()
        {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
