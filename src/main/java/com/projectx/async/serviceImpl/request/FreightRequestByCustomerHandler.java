package com.projectx.async.serviceImpl.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.projectx.async.domain.request.FreightRequestByCustomerDTO;
import com.projectx.async.domain.request.FreightRequestByVendorDTO;
import com.projectx.async.service.request.FreightRequestByCustomerService;
import com.projectx.async.util.exception.ResourceAlreadyPresentException;
import com.projectx.async.util.exception.ResourceNotFoundException;
import com.projectx.async.util.exception.ValidationFailedException;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByCustomerList;


@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByCustomerHandler implements
		FreightRequestByCustomerService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public FreightRequestByCustomer save(
			FreightRequestByCustomer freightRequestByCustomer)throws ResourceAlreadyPresentException,ValidationFailedException {
		
		if(freightRequestByCustomer.getInsertTime()==null)
			freightRequestByCustomer.setInsertTime(new Date());
		
		if(freightRequestByCustomer.getUpdateTime()==null)
			freightRequestByCustomer.setUpdateTime(new Date());
			
		FreightRequestByCustomerDTO freightRequestByCustomerDTO=freightRequestByCustomer.toFreightRequestByCustomerDTO();
		
		HttpEntity<FreightRequestByCustomerDTO> entity=new HttpEntity<FreightRequestByCustomerDTO>(freightRequestByCustomerDTO);
		
		ResponseEntity<FreightRequestByCustomerDTO> result=null;
		
		try{	
		result=restTemplate.exchange(env.getProperty("rest.url")+"/request/freightRequestByCustomer",
				HttpMethod.POST,entity, FreightRequestByCustomerDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return FreightRequestByCustomer.fromFreightRequestByCustomerDTO(result.getBody());
		
		throw new ResourceAlreadyPresentException();
		
	}

	@Override
	public FreightRequestByCustomer getRequestById(Long requestId) throws ResourceNotFoundException{
		
		ResponseEntity<FreightRequestByCustomerDTO> status=restTemplate.exchange(env.getProperty("rest.url")+"/request/freightRequestByCustomer/getById/"+requestId,
				HttpMethod.GET,null,FreightRequestByCustomerDTO.class);
		
		if(status.getStatusCode()==HttpStatus.FOUND)
			return FreightRequestByCustomer.fromFreightRequestByCustomerDTO(status.getBody());
		
		throw new ResourceNotFoundException();
	}

	@Override
	public List<FreightRequestByCustomer> getAllRequestForCustomer(
			Long customerId) {
		
		
		FreightRequestByCustomerList status=null;
		
		try{
			status=restTemplate
					.getForObject(env.getProperty("rest.url")+"/request/freightRequestByCustomer/getByCustomerId/"+customerId, FreightRequestByCustomerList.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		List<FreightRequestByCustomer> customerList=new ArrayList<FreightRequestByCustomer>();
		
		for(int i=0;i<status.getList().size();i++)
		{
			customerList.add(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(status.getList().get(i)));
		}
		
		return customerList;

		
	}



	@Override
	public List<FreightRequestByCustomer> getCustmerReqForVendorReq(
			FreightRequestByVendorDTO freightRequestByVendorDTO) {
	
		FreightRequestByCustomerList status=null;
		
		try{
			status=restTemplate.postForObject(env.getProperty("rest.url")+"/request/freightRequestByCustomer/getMatchingCustomerReqForVendorReq",
					freightRequestByVendorDTO, FreightRequestByCustomerList.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		List<FreightRequestByCustomer> returnList=new ArrayList<FreightRequestByCustomer>();
		
		for(int i=0;i<status.getList().size();i++)
			returnList.add(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(status.getList().get(i)));
		
		
		return returnList;
	

		
		
	}
	
	@Override
	public Boolean deleteRequestById(Long requestId) {

		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.url")+"/request/freightRequestByCustomer/deleteById/"+requestId, Boolean.class);
		
		return status;
	}

	@Override
	public Boolean clearTestData() {

		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.url")+"/request/freightRequestByCustomer/clearTestData", Boolean.class);
		
		return status;

	}

	@Override
	public Integer count() {

		Integer status=restTemplate
				.getForObject(env.getProperty("rest.url")+"/request/freightRequestByCustomer/count", Integer.class);
		
		return status;
		
	}

}
