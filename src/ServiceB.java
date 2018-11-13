//------------------------------------------------------------//
// FILENAME - ServiceB.java                                  //
// AUTHORS - Alexandru Viman   - 100967379                  //
//         - Georges Ankenmann - 100935237                 //
// COURSE - NET4005 - Assignment 3                        //
// DESCRIPTION - Interface for ServiceB                  //
//------------------------------------------------------//

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.net.URL;

@WebService(targetNamespace="http://localhost",serviceName="ServiceB")
public interface ServiceB
{
    @WebMethod String getSvcName(URL x) throws Exception;
}
