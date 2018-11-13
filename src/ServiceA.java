import javax.jws.WebService;
import javax.jws.WebMethod;
import java.net.URL;

@WebService(targetNamespace="http://localhost",serviceName="ServiceA")
public interface ServiceA
{
    @WebMethod String getSvcName(URL x) throws Exception;
}
