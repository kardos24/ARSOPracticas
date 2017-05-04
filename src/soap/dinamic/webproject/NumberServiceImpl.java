package soap.dinamic.webproject;

import javax.jws.WebService;

@WebService(endpointInterface="soap.NumberService")
public class NumberServiceImpl implements NumberService{

	@Override
	public Integer getNumber(String number) throws NumberServiceException {
		try{
		return Integer.parseInt(number);
		} catch(Exception e){
			throw new NumberServiceException(e.getMessage());
		}
	}

}
