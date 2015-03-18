package com.projectx.rest.domain.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.async.domain.request.FreightRequestByVendorDTO;

public class FreightRequestByVendorList {

	List<FreightRequestByVendorDTO> list;

	public FreightRequestByVendorList() {

	}

	@JsonCreator
	public FreightRequestByVendorList(List<FreightRequestByVendorDTO> list) {
		this.list = list;
	}

	public List<FreightRequestByVendorDTO> getList() {
		return list;
	}

	public void setList(List<FreightRequestByVendorDTO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "FreightRequestByVendorList [list=" + list + "]";
	}
	

	
	
}
