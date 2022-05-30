package hello.core.singleton;

public class SingletonService
{
    //이렇게 하면 클레스 레벨에 올라가 딱 하나만 생성
    //외부에서 생성할 수 없음.
    private static final SingletonService instance = new SingletonService();

    //public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용.
    public static SingletonService getInstance()
    {
        return instance;
    }

    //생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 막는다.
    private SingletonService()
    {

    }

    public void logic()
    {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
