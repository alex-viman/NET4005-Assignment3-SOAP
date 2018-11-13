import javax.jws.WebService;
import javax.jws.WebMethod;
import java.net.URL;

@WebService(targetNamespace="http://localhost",serviceName="ServiceB")
public interface ServiceB
{
    @WebMethod public String getSvcName(URL x);
}
