package com.projectx.rest.domain.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.async.domain.request.FreightRequestByCustomerDTO;

public class FreightRequestByCustomerList {

	private List<FreightRequestByCustomerDTO> list;

	public FreightRequestByCustomerList() {

	}

	@JsonCreator
	public FreightRequestByCustomerList(List<FreightRequestByCustomerDTO> list) {
		this.list = list;
	}

	public List<FreightRequestByCustomerDTO> getList() {
		return list;
	}

	public void setList(List<FreightRequestByCustomerDTO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "FreightRequestByCustomerList [list=" + list + "]";
	}
	
	
	
	
}
