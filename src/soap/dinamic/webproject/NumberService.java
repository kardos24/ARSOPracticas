package soap.dinamic.webproject;

import javax.jws.WebService;

@WebService
public interface NumberService{
	Integer getNumber(String number) throws NumberServiceException ;
}
