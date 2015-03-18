package com.projectx.asyn.service.request;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.asyn.service.completeregister.VendorDetailsService;
import com.projectx.async.config.Application;
import com.projectx.async.domain.request.FreightRequestByVendorDTO;
import com.projectx.async.service.request.FreightRequestByCustomerService;
import com.projectx.async.service.request.FreightRequestByVendorService;
import com.projectx.async.util.exception.ValidationFailedException;
import com.projectx.rest.domain.request.FreightRequestByCustomer;

import static com.projectx.async.fixtures.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.*;
import static com.projectx.async.fixtures.complete.VehicleDetailsDataFixtures.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class FreightRequestByCustomerServiceTest {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Before
	public void clearData()
	{
		freightRequestByCustomerService.clearTestData();
		freightRequestByVendorService.clearTestData();
		vendorDetailsService.vehicleClearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		
		assertEquals(savedEntity, freightRequestByCustomerService.getRequestById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
	}
	
	


	@Test
	public void update()
	{
				
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		savedEntity.setBodyType(CREQ_BODYTYPE_CLOSED);
		
		freightRequestByCustomerService.save(savedEntity);
		
		assertEquals(savedEntity, freightRequestByCustomerService.getRequestById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
		
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
		
		freightRequestByCustomerService.deleteRequestById(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByCustomerService.count().intValue());
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
	}

	
	@Test
	public void clearTestData()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
		
		freightRequestByCustomerService.clearTestData();
		
		assertEquals(0, freightRequestByCustomerService.count().intValue());
	}
	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerService.getAllRequestForCustomer(savedEntity.getCustomerId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}

	
	@Test
	public void getCustmerReqForVendorReq()
	{
		freightRequestByCustomerService.clearTestData();
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad110()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoadClosedAcerReq()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoadOpenTataReq()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoad15()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenAcer()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenTata()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel()));
		
		//FreightRequestByVendor vendorRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		
		vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO testRequest=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		//assertEquals(3, freightRequestByCustomerService.getCustmerReqForVendorReq(standardFreightRequestByVendor()).size());

	}
	
	@Test
	public void getCustmerReqForVendorReqWithError()
	{
		try{
			freightRequestByCustomerService.getCustmerReqForVendorReq(standardFreightRequestByVendorWithError());
			assertEquals(0, 1);
		}catch(ValidationFailedException e)
		{
			assertEquals(0, 0);
		}
		
		
	}

}
