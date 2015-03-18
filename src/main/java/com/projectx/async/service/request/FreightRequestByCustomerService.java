package com.projectx.async.service.request;

import java.util.List;

import com.projectx.async.domain.request.FreightRequestByVendorDTO;
import com.projectx.async.util.exception.ResourceAlreadyPresentException;
import com.projectx.async.util.exception.ResourceNotFoundException;
import com.projectx.async.util.exception.ValidationFailedException;
import com.projectx.rest.domain.request.FreightRequestByCustomer;


public interface FreightRequestByCustomerService {

	FreightRequestByCustomer save(FreightRequestByCustomer freightRequestByCustomer)
									throws ResourceAlreadyPresentException,ValidationFailedException;
	
	FreightRequestByCustomer getRequestById(Long requestId) throws ResourceNotFoundException;
	
	List<FreightRequestByCustomer> getAllRequestForCustomer(Long customerId);
	
	List<FreightRequestByCustomer> getCustmerReqForVendorReq(FreightRequestByVendorDTO freightRequestByVendorDTO);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
}
