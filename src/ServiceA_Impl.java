import javax.jws.WebService;
import java.net.URL;

@WebService(targetNamespace="http://localhost",serviceName="ServiceA",endpointInterface="ServiceA",portName="ServiceAPort")
public class ServiceA_Impl implements ServiceA{

    public String getSvcName(URL x)
    {
        return "ServiceA = " + x.getPort();
    }

}