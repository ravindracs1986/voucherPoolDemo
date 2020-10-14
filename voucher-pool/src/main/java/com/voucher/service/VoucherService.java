package com.voucher.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voucher.configuration.ConfigConstants;
import com.voucher.entity.Recipient;
import com.voucher.entity.Voucher;
import com.voucher.model.RecipientDto;
import com.voucher.model.RecipientResonse;
import com.voucher.model.VoucherDto;
import com.voucher.model.VoucherResonse;
import com.voucher.repo.RecipientRepository;
import com.voucher.repo.VoucherRepository;
import com.voucher.util.DateUtil;
import com.voucher.util.OfferTypes;

@Service
@Transactional
public class VoucherService {

	@Autowired
	private VoucherRepository voucherRepository;

	@Autowired
	private RecipientRepository recipientRepository;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean generateOneVoucherCode(RecipientDto recipientDto) throws IOException {
		boolean resp = true;
		Date date = null;
		Recipient recipient = new Recipient();
		recipient.setEmail(recipientDto.getEmail());
		recipient.setName(recipientDto.getName());
		Recipient recipientTmp = recipientRepository.save(recipient);
		if (recipientTmp != null && !StringUtils.isEmpty(recipientTmp.getId())) {
			Voucher voucher = new Voucher();
			voucher.setIsUsed("N");
			voucher.setVoucherCode(generateRandomNumber());
			voucher.setRecipient(recipientTmp);
			try {
				date = DateUtil.getFormatDate(recipientDto.getExpDate(), ConfigConstants.DT_DD_MM_YYYY_SLASH);
			} catch (ParseException e) {
				e.printStackTrace();
				resp = false;
			}
			voucher.setExpDate(date);
			voucher.setUsedDate(null);
			voucher.setOfferName(recipientDto.getOfferName());
			voucherRepository.save(voucher);
		}

		return resp;

	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean generateForAllVoucherCode(RecipientDto recipientDto) throws IOException {
		boolean resp = true;
		Date expDate = null;
		int count = 0;
		List<Recipient> recipientlst = recipientRepository.findAll();

		try {
			expDate = DateUtil.getFormatDate(recipientDto.getExpDate(), ConfigConstants.DT_DD_MM_YYYY_SLASH);
		} catch (ParseException e) {
			e.printStackTrace();
			resp = false;
		}
		for (Recipient recipient : recipientlst) {
			Voucher voucher = new Voucher();
			voucher.setIsUsed("N");
			voucher.setVoucherCode(generateRandomNumber());
			voucher.setRecipient(recipient);
			voucher.setExpDate(expDate);
			voucher.setUsedDate(null);
			voucher.setOfferName(recipientDto.getOfferName());
			Voucher voucherTemp = voucherRepository.save(voucher);
			count++;
		}
		if (recipientlst.size() != count) {
			resp = false;
		}

		return resp;

	}

	public String generateRandomNumber() {
		int n = 8;
		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();

		// remove all spacial char
		String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < AlphaNumericString.length(); k++) {

			if (Character.isLetter(AlphaNumericString.charAt(k)) && (n > 0)
					|| Character.isDigit(AlphaNumericString.charAt(k)) && (n > 0)) {

				r.append(AlphaNumericString.charAt(k));
				n--;
			}
		}
		return r.toString();
	}

	public RecipientResonse validateVoucherCode(String voucherCode, String email) {
		RecipientResonse response = new RecipientResonse();
		boolean isFound = true;
		Recipient res = recipientRepository.findByEmail(email);
		if (!StringUtils.isEmpty(res)) {
			Set<Voucher> voucherLst = res.getVoucher();
			for (Voucher voucher : voucherLst) {
				if (voucher.getVoucherCode().equalsIgnoreCase(voucherCode)) {
					Integer offerValue = OfferTypes.getOfferTypes(voucher.getOfferName());
					response.setDiscount(offerValue + "%");
					String date = DateUtil.getFormatDate(voucher.getExpDate(), ConfigConstants.DT_DD_MM_YYYY_SLASH);
					response.setExpDate(date);
					response.setStatus("SUCCESS");
					// response.setMessage("success");
					isFound = false;
					break;
				}

			}

		} else {
			response.setStatus("Fail");
			response.setMessage("No Recipient available ");
		}
		if (isFound) {
			response.setStatus("Fail");
			response.setMessage("No Recipient available ");
		}
		return response;
	}

	public VoucherResonse findAllVoucherCodes(String email) {
		VoucherResonse voucherResonse = new VoucherResonse();
		Set<VoucherDto> response = new HashSet<>();
		Recipient res = recipientRepository.findByEmail(email);
		if (!StringUtils.isEmpty(res)) {
			Set<Voucher> voucherLst = res.getVoucher();
			for (Voucher voucher : voucherLst) {
				VoucherDto dto = new VoucherDto();
				String date = DateUtil.getFormatDate(voucher.getExpDate(), ConfigConstants.DT_DD_MM_YYYY_SLASH);
				dto.setExpDate(date);
				dto.setVoucherCode(voucher.getVoucherCode());
				dto.setOfferName(voucher.getOfferName());
				response.add(dto);

			}

		}
		if (response.size() != 0) {
			voucherResonse.setResponse(response);
			voucherResonse.setStatus("SUCCESS");
			voucherResonse.setMessage("VoucherCode found");
		} else {
			voucherResonse.setResponse(response);
			voucherResonse.setStatus("Fail");
			voucherResonse.setMessage("VoucherCode Not found");
		}

		return voucherResonse;
	}

	public RecipientResonse deleteVoucherCodes(String voucherCode, String email) {
		boolean resp=false;
		RecipientResonse recipientResonse =new RecipientResonse();
		Recipient res = recipientRepository.findByEmail(email);
		if (!StringUtils.isEmpty(res)) {
			Set<Voucher> voucherLst = res.getVoucher();
			for (Voucher voucher : voucherLst) {
				if(voucher.getVoucherCode().equalsIgnoreCase(voucherCode)) {
					voucherRepository.deleteVoucherById(voucher.getId());
					resp=true;
				}
				

			}

		}
		if(resp) {
			recipientResonse.setMessage("VoucherCode Deleted");
			recipientResonse.setStatus("SUCCESS");
		}else {
			recipientResonse.setMessage("Something wrong,Please try after soe time ");
			recipientResonse.setStatus("FAIL");
		}
		return recipientResonse;
	}

	public RecipientResonse addRecipient(String name, String email) {
		RecipientResonse recipientResonse =new RecipientResonse();
		Recipient res = recipientRepository.findByEmail(email);
		if (!StringUtils.isEmpty(res)) {
			recipientResonse.setMessage("Already registred Recipient ");
			recipientResonse.setStatus("FAIL");
		}else {
			Recipient recipient=new Recipient();
			recipient.setEmail(email);
			recipient.setName(name);
			recipientRepository.save(recipient);
			recipientResonse.setMessage("Recipient registred");
			recipientResonse.setStatus("SUCCESS");
		}
		
		return recipientResonse;
	}

	public List<RecipientDto> findAllRecipient() {
		List<Recipient> res = recipientRepository.findAll();
		List<RecipientDto> lst=new ArrayList<>();
		for (Recipient recipient : res) {
			RecipientDto dto=new RecipientDto();
			dto.setEmail(recipient.getEmail());
			dto.setName(recipient.getName());
			lst.add(dto);
		}
		
		return lst;
	}

}
