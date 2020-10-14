package com.voucher.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.configuration.ConfigConstants;
import com.voucher.model.RecipientDto;
import com.voucher.model.RecipientResonse;
import com.voucher.model.VoucherResonse;
import com.voucher.service.VoucherService;
import com.voucher.util.DateUtil;
import com.voucher.util.OfferTypes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@Api(value="Voucher pool", description="Voucher pool microservices")
@RestController
@RequestMapping("/api/v1")
public class VoucherController {
	private static final Logger LOGGER = LoggerFactory.getLogger(VoucherController.class);
	@Autowired
    private VoucherService service;
	
	@ApiOperation(value = "Generate VoucherCode for Recipient")
	@RequestMapping(value = {"/generateVoucherCode"},method = RequestMethod.POST)
    public RecipientResonse generate(@RequestBody RecipientDto recipientDto) {
        RecipientResonse resp=new RecipientResonse();
        boolean res=true;
        try {
	        //only one 
	        if(!StringUtils.isEmpty(recipientDto) && !StringUtils.isEmpty(recipientDto.getEmail()) && !StringUtils.isEmpty(recipientDto.getName())) {
	        	if(isValidInput(recipientDto)) {
	        		res=service.generateOneVoucherCode(recipientDto);
	        		if(res) {
	        			resp.setStatus("SUCCESS");
		                resp.setMessage("Voucher Code Geerated");
	        		}else {
	        			resp.setStatus("Fail");
		                resp.setMessage("Service is failing plase try after sometime");
	        		}
					
	        	}else {
	        		resp.setStatus("Fail");
	                resp.setMessage("OfferName or Exp date is  not correct");
	        	}
	        	
	        	
	        }else if(!StringUtils.isEmpty(recipientDto)) {//all record
	        	if(isValidInput(recipientDto)) {
	        		res=service.generateForAllVoucherCode(recipientDto);
	        		if(res) {
	        			resp.setStatus("SUCCESS");
		                resp.setMessage("Voucher Code Geerated");
	        		}else {
	        			resp.setStatus("Fail");
		                resp.setMessage("Service is failing plase try after sometime");
	        		}
	        	}else {
	        		resp.setStatus("Fail");
	                resp.setMessage("OfferName or Exp date is  not correct");
	        	}
	        	
	        	
	        }else {
	        	resp.setStatus("Fail");
	            resp.setMessage("Request input not correct");
	        }
        } catch (IOException e) {
			resp.setStatus("Fail");
            resp.setMessage("Request input not correct");
		}
    	return resp;
    }

    private boolean isValidInput(RecipientDto recipientDto) {
		
    	Integer offerValue=OfferTypes.getOfferTypes(recipientDto.getOfferName());
    	if(recipientDto.getOfferName() !=null && offerValue!=null &&
    			recipientDto.getExpDate()!=null) {
    		try {
				Date date=DateUtil.getFormatDate(recipientDto.getExpDate(),ConfigConstants.DT_DD_MM_YYYY_SLASH);
				LOGGER.info("validated request date "+date);
				if(!StringUtils.isEmpty(date)) {
					return true;
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
    		
    	}
    	return false;
	}

    @ApiOperation(value = "Validate VoucherCode for Recipient")
    @RequestMapping(value = { "/validateVoucherCode"}, method = RequestMethod.GET)
    public RecipientResonse validateVoucherCode(@RequestParam(name = "voucherCode") String voucherCode,@RequestParam(name = "email") String email) {
       
    	RecipientResonse resp=new RecipientResonse();
    	
    	if(voucherCode !=null && email !=null) {
    		resp =service.validateVoucherCode(voucherCode,email);
    	}
    	
    	return resp;
    }

    @ApiOperation(value = "Find all VoucherCode for given Email")
    @RequestMapping(value = { "/voucherCodes"}, method = RequestMethod.GET)
    public VoucherResonse findAllVoucherCodes(@RequestParam(name = "email") String email) {
    	VoucherResonse resp=null;
    	if(email !=null) {
    		resp =service.findAllVoucherCodes(email);
    	}
    	
    	return resp;
    	
    }

    @ApiOperation(value = "Delete VoucherCode for given Email")
    @RequestMapping(value = { "/delete/{voucherCode}"}, method = RequestMethod.DELETE)
    public RecipientResonse deleteVoucherCodes(@PathVariable String voucherCode,@RequestParam(name = "email") String email) {
      RecipientResonse resp=new RecipientResonse();
    	if(voucherCode!=null && email!=null) {
    		resp =service.deleteVoucherCodes(voucherCode,email);
    	}
      
    	return resp;
    }
    
    @ApiOperation(value = "Add Recipient Details")
    @RequestMapping(value = { "/addRecipient"}, method = RequestMethod.POST)
    public RecipientResonse addRecipient(@RequestBody RecipientDto recipientDto) {
      RecipientResonse resp=new RecipientResonse();
    	if(recipientDto.getEmail()!=null && recipientDto.getName()!=null) {
    		resp =service.addRecipient(recipientDto.getName(),recipientDto.getEmail());
    	}else {
    		resp.setStatus("FAIL");
    		resp.setMessage("Email and Name should not be null");
    	}
      
    	return resp;
    }
    
    @ApiOperation(value = "Find all Recipients in systems")
    @RequestMapping(value = { "/allRecipients"}, method = RequestMethod.GET)
    public List<RecipientDto> findAllRecipient() {
    	return service.findAllRecipient();
    	
    }

}
