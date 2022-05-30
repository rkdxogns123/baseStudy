package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        //AppConfig랑 충돌 안나기 위해 필터씌워서 빼기.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //basePackages = "hello.core.member" //어디서부터 찾는지 지정할 수 있다.
        //
)

public class AutoAppConfig
{
    /*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
    }
    */


}
