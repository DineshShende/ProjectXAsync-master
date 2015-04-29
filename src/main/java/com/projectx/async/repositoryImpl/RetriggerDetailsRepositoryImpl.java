package com.projectx.async.repositoryImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.async.domain.RetriggerDetails;
import com.projectx.async.repository.RetriggerDetailsRepository;
import com.projectx.async.util.exception.ValidationFailedException;
import com.projectx.data.domain.RetriggerList;




@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class RetriggerDetailsRepositoryImpl implements
		RetriggerDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public RetriggerDetails save(RetriggerDetails retriggerDetails) {

		HttpEntity<RetriggerDetails> entity=new HttpEntity<RetriggerDetails>(retriggerDetails);
		
		ResponseEntity<ResponseDTO<RetriggerDetails>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/retriggerDetails",
					HttpMethod.POST,entity, new ParameterizedTypeReference<ResponseDTO<RetriggerDetails>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException(result.getBody().getErrorMessage());
		}
				
			
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody().getResult();
		else
			throw new ValidationFailedException(result.getBody().getErrorMessage());
	
	}

	@Override
	public List<RetriggerDetails> findAll() {
		
		
		ResponseEntity<ResponseDTO<RetriggerList>> savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/retriggerDetails/findAll",
				HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<RetriggerList>>() {});
				
		return savedEntity.getBody().getResult().getList();
		
		
	}

	@Override
	public Boolean deleteById(Long retriggerId) {

		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/retriggerDetails/deleteById/"+retriggerId,
				HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
						
		return savedEntity.getBody().getResult();
		

	}

	@Override
	public void clearTestData() {

		restTemplate.getForObject(env.getProperty("data.url")+"/retriggerDetails/clearTestData", Boolean.class);
	}

}
