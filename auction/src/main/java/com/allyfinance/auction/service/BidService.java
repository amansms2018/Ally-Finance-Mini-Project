package com.allyfinance.auction.service;

import com.allyfinance.auction.model.Bid;
import com.allyfinance.auction.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;
       public Bid saveBidPost(Bid bid){
        return  bidRepository.save(bid);
    }


}
