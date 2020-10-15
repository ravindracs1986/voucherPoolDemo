# This project is design for create vouche and validate voucher for recipient

# Server URL
http://voucherpool.cf/swagger-ui.html#/

Offer Name and there value is constant so while using api call please use same offerName
offerName ="OFFER_2"  and value =2,
offerName ="OFFER_5"  and value =5,
offerName ="OFFER_10" and value =10,
offerName ="OFFER_20" and value =20,
offerName ="OFFER_50" and value =50

Sample request
--------------------------
{
  "email": "ravindra1@gmail.com",
  "expDate": "20/11/2020",
  "name": "Ravindra kumar",
  "offerName": "OFFER_2"
}

expDate is string date and format is like (DD/MM/YYYY)

Total there is 6 API for test 
1.POST /api/v1/addRecipient---add Recipient
request :
{
  "email": "ravindra1@gmail.com",
  "name": "Ravindra kumar"
  
} 

2.GET /api/v1/allRecipients

3.DELETE /api/v1/delete/{voucherCode}
request:
api/v1/delete/xb2GGfNA?email=ravindra1%40gmail.com

4.POST /api/v1/generateVoucherCode--for all Recipients

Request :
{
  
  "expDate": "20/11/2020",
  "offerName": "OFFER_5"
}

5.GET /api/v1/validateVoucherCode

/api/v1/validateVoucherCode?voucherCode=xb2GGfNA&email=ravindra1%40gmail.com

response:
{
  "status": "SUCCESS",
  "discount": "2%",
  "expDate": "19/11/2020"
}

6.GET /api/v1/voucherCodes -gett all from system for given email

api/v1/voucherCodes?email=ravindra1%40gmail.com

response:
{
  "status": "SUCCESS",
  "message": "VoucherCode found",
  "response": [
    {
      "voucherCode": "xb2GGfNA",
      "expDate": "19/11/2020",
      "offerName": "OFFER_2"
    }
  ]
}


