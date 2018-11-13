import javax.jws.WebService;
import java.net.URL;

@WebService(targetNamespace="http://localhost",serviceName="ServiceB",endpointInterface="ServiceB",portName="ServiceBPort")
public class ServiceB_Impl implements ServiceB {

    public String getSvcName(URL x)
    {
        return "ServiceB = " + x.getPort();
    }

}