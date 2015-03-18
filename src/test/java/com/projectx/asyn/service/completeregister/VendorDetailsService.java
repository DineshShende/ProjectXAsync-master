package com.projectx.asyn.service.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.async.domain.request.VehicleDetailsDTO;

@Service
public interface VendorDetailsService {
	
	Boolean vehicleClearTestData();
	
	VehicleDetailsDTO save(VehicleDetailsDTO vehicleDetailsDTO);

}
