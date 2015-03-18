package com.projectx.async.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.projectx.async.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.async.domain.ivr.QuestionListWithCounter;

import com.projectx.async.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.async.domain.request.FreightRequestByVendorDTO;
import com.projectx.async.domain.request.MobileMessgeDTO;
import com.projectx.async.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepositoty;
import com.projectx.async.service.request.FreightRequestByCustomerService;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;

@RestController
@RequestMapping(value="/request")
public class RequestHandlingController {

	@Autowired
	FreightRequestByCustomerStatusDTO freightRequestByCustomerStatusDTO; 
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepositoty questionPossibleAnswersSelectedAnswerRepositoty;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/freightRequestByCustomer/getMatchingCustomerReqForVendorReq",method=RequestMethod.POST)
	public void HandleWaitingRequests(@RequestBody FreightRequestByVendorDTO freightRequestByVendorDTO)
	{
		
		List<FreightRequestByCustomer> list=freightRequestByCustomerService
													. getCustmerReqForVendorReq(freightRequestByVendorDTO);

		//Call parallel/sequential to customers(OutBound Call) ,It will complete this functionality
		
		
		
		List<QuestionPossibleAnswersSelectedAnswer> qlist=questionPossibleAnswersSelectedAnswerRepositoty.getAll();
		
			
		list.forEach(e->{
			
			QuestionListWithCounter questionListWithCounter =new QuestionListWithCounter(e.getRequestId(),0,qlist);
			
		});
		
		
	}
	
	
	
	
	@RequestMapping(value="/testParallel")
	public void testParallelCall() throws InterruptedException, ExecutionException
	{
		
		List<MobileMessgeDTO> list=new ArrayList<MobileMessgeDTO>();
		
		list.add(new MobileMessgeDTO(1L, "1L"));
		list.add(new MobileMessgeDTO(2L, "2L"));
		list.add(new MobileMessgeDTO(3L, "3L"));
		list.add(new MobileMessgeDTO(4L, "4L"));
		list.add(new MobileMessgeDTO(5L, "5L"));
		list.add(new MobileMessgeDTO(6L, "6L"));
		list.add(new MobileMessgeDTO(7L, "7L"));
		list.add(new MobileMessgeDTO(8L, "8L"));
		list.add(new MobileMessgeDTO(9L, "9L"));
		
		
		list.parallelStream().forEach(ele->{
			
			Integer result=callIVR(ele);
			
			System.out.println("The object is:"+ele+",The result is:"+result+",Time of handling:"+new Date());
		});
		
		
		/*
		
		Serial execution:
		
		list.stream().forEach(ele->{
			
			Integer result=callIVR(ele);
			
			System.out.println("The object is:"+ele+",The result is:"+result+",Time of handling:"+new Date());
			
		});
		
		*/
		/*
		 * 
		
		Parallel execution with ForkJoinPool:
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(list.size());
		
		forkJoinPool.submit(()->{
			
			list.parallelStream().forEach(ele->{
				
				Integer result=callIVR(ele);
				
				System.out.println("The object is:"+ele+",The result is:"+result+",Time of handling:"+new Date());
				
			});
			
		}).get();
		*/
		
		
		
		/*
		 * 
		Parallel execution with set System property for desired thread count:
		 
		System.out.println("Available processors:"+Runtime.getRuntime().availableProcessors());
		
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", Integer.toString(list.size()));
		
		
		list.parallelStream().forEach(ele->{
			
			Integer result=callIVR(ele);
			
			System.out.println("The object is:"+ele+",The result is:"+result+",Time of handling:"+new Date());
			
		});
		*/
		
	}

	//@RequestMapping(value="/callIVR",method=RequestMethod.POST)
	public Integer callIVR(@RequestBody MobileMessgeDTO mobileMessageDTO)
	{
		System.out.println("Started processing:"+mobileMessageDTO);
		try {
			Thread.sleep((long)(Math.random()*60000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Ended processing:"+mobileMessageDTO);
		
		
		return (new Integer((int)(Math.random()*3)));
	}
	
}
