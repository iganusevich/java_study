package soap.tests;

import org.oorsprong.websamples_countryinfo.CountryInfoService;
import org.testng.Assert;
import org.testng.annotations.Test;
//import net.webservicex.GeoIP;
//import net.webservicex.GeoIPService;


public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        //GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("62.216.46.168");
        //Assert.assertEquals(geoIP.getCountryCode(), "UA") ;
        //http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL
        CountryInfoService service = new CountryInfoService();
        String code = service.getCountryInfoServiceSoap().countryISOCode("France");
        String city = service.getCountryInfoServiceSoap().capitalCity("FR");
        int i = 0;


    }
    
}
