package com.projectx.async.domain.ivr;

public class CustomerIdMobileDTO {
	
	private Long customerRequestId;
	
	private Long mobile;

	public CustomerIdMobileDTO() {

	}

	public CustomerIdMobileDTO(Long customerRequestId, Long mobile) {
		super();
		this.customerRequestId = customerRequestId;
		this.mobile = mobile;
	}

	public Long getCustomerRequestId() {
		return customerRequestId;
	}

	public void setCustomerRequestId(Long customerRequestId) {
		this.customerRequestId = customerRequestId;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "CustomerIdMobileDTO [customerRequestId=" + customerRequestId
				+ ", mobile=" + mobile + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((customerRequestId == null) ? 0 : customerRequestId
						.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerIdMobileDTO other = (CustomerIdMobileDTO) obj;
		if (customerRequestId == null) {
			if (other.customerRequestId != null)
				return false;
		} else if (!customerRequestId.equals(other.customerRequestId))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}
	
	

}
