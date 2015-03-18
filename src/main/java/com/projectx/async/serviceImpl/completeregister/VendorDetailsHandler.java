package com.projectx.async.serviceImpl.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.asyn.service.completeregister.VendorDetailsService;
import com.projectx.async.domain.request.VehicleDetailsDTO;
import com.projectx.async.util.exception.ResourceAlreadyPresentException;
import com.projectx.async.util.exception.ValidationFailedException;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class VendorDetailsHandler implements VendorDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public Boolean vehicleClearTestData() {

		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.url")+"/vendor/vehicle/clearTestData", Boolean.class);
		
		return result;

	}

	@Override
	public VehicleDetailsDTO save(VehicleDetailsDTO vehicleDetailsDTO) {
		
		if(vehicleDetailsDTO.getInsertTime()==null)
			vehicleDetailsDTO.setInsertTime(new Date());
		
		vehicleDetailsDTO.setUpdateTime(new Date());
		
		HttpEntity<VehicleDetailsDTO> entity=new HttpEntity<VehicleDetailsDTO>(vehicleDetailsDTO);
		
		ResponseEntity<VehicleDetailsDTO> result=null;
		
		try{
			result=restTemplate
					.exchange(env.getProperty("rest.url")+"/vendor/vehicle",HttpMethod.POST, entity, VehicleDetailsDTO.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		
		throw new ResourceAlreadyPresentException(); 

	}


	
	
}
