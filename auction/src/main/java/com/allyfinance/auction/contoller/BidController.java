package com.allyfinance.auction.contoller;

import com.allyfinance.auction.customException.MyException;
import com.allyfinance.auction.model.Bid;
import com.allyfinance.auction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController {
    @Autowired
    private BidService bidService;
    @PostMapping("/bids")
    public ResponseEntity<Bid> saveBidPost(@RequestBody Bid bid) {



        if(bid!=null){
            Bid bid1= bidService.saveBidPost(bid);
            return  new ResponseEntity<>( bidService.saveBidPost(bid), HttpStatus.CREATED);
        }
        else

        return  new ResponseEntity<Bid>((MultiValueMap<String, String>) new MyException(" The Bid content is Empty, Please enter values  the following field " +bid.toString()), HttpStatus.BAD_REQUEST);

    }
}
