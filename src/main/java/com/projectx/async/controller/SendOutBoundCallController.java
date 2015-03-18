package com.projectx.async.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.async.domain.ivr.CustomerIdMobileDTO;
import com.projectx.async.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.async.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepositoty;

@RestController
@RequestMapping(value="/outboundcall")
public class SendOutBoundCallController {

	@Autowired
	FreightRequestByCustomerStatusDTO freightRequestByCustomerStatusDTO;
	
	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepositoty questionPossibleAnswersSelectedAnswerRepositoty;
	
	@RequestMapping(value="/startHandShake",method=RequestMethod.POST)
	public void outBoundCall(@RequestBody CustomerIdMobileDTO customerIdMobileDTO)
	{
		
		
		
	}
	
}
