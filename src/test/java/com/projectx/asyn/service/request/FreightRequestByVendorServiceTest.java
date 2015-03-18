package com.projectx.asyn.service.request;

import static com.projectx.async.fixtures.complete.VehicleDetailsDataFixtures.standardVehicleDetails;
import static com.projectx.async.fixtures.complete.VehicleDetailsDataFixtures.standardVehicleDetailsClosed;
import static com.projectx.async.fixtures.complete.VehicleDetailsDataFixtures.standardVehicleDetailsFlexible;
import static com.projectx.async.fixtures.complete.VehicleDetailsDataFixtures.standardVehicleDetailsOpen307;
import static com.projectx.async.fixtures.request.FreightRequestByCustomerDataFixture.standardFreightRequestByCustomerFullTruckLoad;
import static com.projectx.async.fixtures.request.FreightRequestByCustomerDataFixture.standardFreightRequestByCustomerFullTruckLoadError;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.REQ_AVAIL_TIME_UPDATED;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.REQ_DESTINATION_UPDATED;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.REQ_PICK_UP_RANGE;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.standardFreightRequestByVendor;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.standardFreightRequestByVendorClosed;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.standardFreightRequestByVendorFlexible;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.standardFreightRequestByVendorOpen307;
import static com.projectx.async.fixtures.request.FreightRequestByVendorDataFixture.standardFreightRequestByVendorWithError;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import com.projectx.async.domain.request.VehicleDetailsDTO;
import com.projectx.async.service.request.FreightRequestByCustomerService;
import com.projectx.async.service.request.FreightRequestByVendorService;
import com.projectx.async.util.exception.ValidationFailedException;
import com.projectx.rest.domain.request.FreightRequestByCustomer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class FreightRequestByVendorServiceTest {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;

	@Before
	public void clearData()
	{
		freightRequestByVendorService.clearTestData();
		vendorDetailsService.vehicleClearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		assertEquals(savedEntity, freightRequestByVendorService.getRequestById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void saveWithError()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=null;
		
		try{
			savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendorWithError());
		}catch(ValidationFailedException e)
		{
			assertNull(savedEntity);
		}
		
		assertEquals(0, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		savedEntity.setSource(REQ_DESTINATION_UPDATED);
		savedEntity.setAvailableTime(REQ_AVAIL_TIME_UPDATED);
		savedEntity.setPickupRangeInKm(REQ_PICK_UP_RANGE);
		
		FreightRequestByVendorDTO updatedEntity=freightRequestByVendorService.save(savedEntity);
		
		assertEquals(savedEntity, updatedEntity);
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
		
		freightRequestByVendorService.deleteRequestById(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
		
		freightRequestByVendorService.clearTestData();
		
		assertEquals(0, freightRequestByVendorService.count().intValue());
	}
	
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService.getAllRequestForVendor(savedEntity.getVendorId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}

	@Test
	public void getMatchingVendorReqForCustReq()
	{
		
		vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO  savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		vendorDetailsService.save(standardVehicleDetailsOpen307());
		
		freightRequestByVendorService.save(standardFreightRequestByVendorOpen307());
		
		vendorDetailsService.save(standardVehicleDetailsClosed());
		
		freightRequestByVendorService.save(standardFreightRequestByVendorClosed());
		
		vendorDetailsService.save(standardVehicleDetailsFlexible());
		
		freightRequestByVendorService.save(standardFreightRequestByVendorFlexible());
		
		FreightRequestByCustomer freightRequestByCustomer=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		List<FreightRequestByVendorDTO> matchList=freightRequestByVendorService.getMatchingVendorReqForCustReq(freightRequestByCustomer.toFreightRequestByCustomerDTO());
		
		assertEquals(1, matchList.size());
	}
	
	@Test
	public void getMatchingVendorReqForCustReqError()
	{

		try{
			List<FreightRequestByVendorDTO> matchList=freightRequestByVendorService
					.getMatchingVendorReqForCustReq(standardFreightRequestByCustomerFullTruckLoadError());
			assertEquals(0, 1);
			
		}catch(ValidationFailedException e)
		{
			assertEquals(1, 1);
		}
		
	}
	
}
