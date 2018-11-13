import javax.jws.WebService;
import javax.jws.WebMethod;
import java.net.URL;

@WebService(targetNamespace="http://localhost",serviceName="ServiceA")
public interface ServiceA
{
    @WebMethod public String getSvcName(URL x);
}
