package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//javax로 시작하면 자바언어에서 공식적으로 지원하는 것.
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//인터페이스 이용한 빈 소멸주기.
//public class NetworkClient implements InitializingBean, DisposableBean
public class NetworkClient
{
    private String url;
    public NetworkClient()
    {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect()
    {
        System.out.println("connect = " + url);
    }

    public void call(String message)
    {
        System.out.println("call:  " + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect()
    {
        System.out.println("close: " + url);
    }

    //의존관계 끝나면 호출해 주겠다는 프로퍼티.
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //afterPropertiesSet 이 빈이 종료될 때 호출해줄 것.

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
