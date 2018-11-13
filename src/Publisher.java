import javax.xml.ws.Endpoint;

public class Publisher{
  public static void main(String[] args){
    //Service A
    Endpoint.publish("http://localhost:8000/ServiceA", new ServiceA_Impl());
    Endpoint.publish("http://localhost:8001/ServiceA", new ServiceA_Impl());

    //Service B
    Endpoint.publish("http://localhost:9000/ServiceB", new ServiceB_Impl());
    Endpoint.publish("http://localhost:9001/ServiceB", new ServiceB_Impl());
  }
}
