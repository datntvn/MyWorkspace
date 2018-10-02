package com.starhub.services;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.datnt.models.Device;
import com.datnt.services.HouseholdServicesImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseholdServicesImplTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HouseholdServicesImpl service;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void testGetDevices() {
        mockServer.expect(once(), 
                requestTo("http://datntdomain.com/subpath1/subpath2/subpath3/mobile ... ..."))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"updatedOn\":null,\"irid\":\"IRID...\"}", MediaType.APPLICATION_JSON));

        ResponseEntity<Device> result = service.getDevices("100", "OBJECT INFO");
        System.out.println("THE BODY OF RESPONSE: "+result.getBody());
        Device device = (Device) result.getBody();
        System.out.println("Verify device : "+device.getIrid());
        mockServer.verify();
        assertEquals("IRID...", device.getIrid());
    }

    // TODO: this test works, but mockito Matcher is deprecated
//    @Test
//    public void testGetDevices(){
//        ResponseEntity<List<Device>> myEntity = new ResponseEntity<List<Device>>(HttpStatus.ACCEPTED);
//        Mockito.when(restTemplate.exchange(
//                Matchers.eq("http://datntdomain.com/subpath1/subpath2/subpath3/mobile.... "),
//                Matchers.eq(HttpMethod.GET),
//                Matchers.<HttpEntity<List<Device>>>any(),
//                Matchers.<ParameterizedTypeReference<List<Device>>>any())
//            ).thenReturn(myEntity);
//    }
}
