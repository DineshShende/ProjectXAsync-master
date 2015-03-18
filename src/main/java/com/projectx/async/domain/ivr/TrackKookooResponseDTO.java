package com.projectx.async.domain.ivr;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="singleton", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class TrackKookooResponseDTO {

	private ConcurrentMap<Long , KooKooRequestEntity> requestStatus;

	
	public TrackKookooResponseDTO() {
		
		requestStatus=new ConcurrentHashMap<Long , KooKooRequestEntity>();
	}

	public TrackKookooResponseDTO(
			ConcurrentMap<Long, KooKooRequestEntity> responseStatus) {

		this.requestStatus = new ConcurrentHashMap<Long , KooKooRequestEntity>();
	}


	public void add(Long sid,KooKooRequestEntity kooKooRequestEntity )
	{
		requestStatus.put(sid, kooKooRequestEntity);
	}

	public KooKooRequestEntity get(Long sid)
	{
		KooKooRequestEntity fetchedEntity=requestStatus.get(sid);
		
		return fetchedEntity;
	}
	
	public void delete(Long sid)
	{
		requestStatus.remove(sid);
	}

	@Override
	public String toString() {
		return "TrackKookooResponseDTO [responseStatus=" + requestStatus + "]";
	}
	
	
	
	
}
