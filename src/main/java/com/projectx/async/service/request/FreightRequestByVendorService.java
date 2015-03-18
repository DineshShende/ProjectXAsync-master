package com.projectx.async.service.request;

import java.util.List;

import com.projectx.async.domain.request.FreightRequestByCustomerDTO;
import com.projectx.async.domain.request.FreightRequestByVendorDTO;





public interface FreightRequestByVendorService {

	FreightRequestByVendorDTO save(FreightRequestByVendorDTO freightRequestByCustomer);
	
	FreightRequestByVendorDTO getRequestById(Long requestId);
	
	List<FreightRequestByVendorDTO> getAllRequestForVendor(Long vendorId);
	
	List<FreightRequestByVendorDTO> getMatchingVendorReqForCustReq(FreightRequestByCustomerDTO freightRequestByCustomer);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
}
