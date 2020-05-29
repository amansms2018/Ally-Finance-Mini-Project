package com.allyfinance.auction.service;

import com.allyfinance.auction.DTO.BidDTO;
import com.allyfinance.auction.customException.MyException;
import com.allyfinance.auction.model.AuctionItem;
import com.allyfinance.auction.model.Bid;
import com.allyfinance.auction.notificationService.NotificationService;
import com.allyfinance.auction.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionItemService {
    @Autowired
    private AuctionRepository auctionRepository;

    public Optional<AuctionItem> findAuctionById(Long actionItemId) {
        return auctionRepository.findById(actionItemId);
    }

    public AuctionItem saveAuctionItem(AuctionItem actionItem) {
        return auctionRepository.save(actionItem);
    }

    public List<AuctionItem> findAllAuction() {
        return auctionRepository.findAll();
    }


    public void registerBid(BidDTO bidDto) throws MyException {
        AuctionItem auctionItem = findAuctionById(bidDto.getAuctionItem().getActionItemId()).get();
        auctionItem.addBidder(new Bid( bidDto.getMaxAutoBidAmount(),bidDto.getBidderName()));

        if (auctionItem.getReservedPrice() > bidDto.getMaxAutoBidAmount()) {
            auctionItem.setCurrentBid(Math.max(auctionItem.getCurrentBid(), bidDto.getMaxAutoBidAmount()));
            throw new MyException("Current prices is higher than the your bid amount");
        }
        if (auctionItem.getCurrentBid() == 0) {
            auctionItem.setCurrentBid(bidDto.getMaxAutoBidAmount() + 1);
            auctionItem.setBidderName(bidDto.getBidderName());
        }
        else {
            Bid b1 = auctionItem.getHighestBid();
            Bid b2 = auctionItem.getSecondHighestBid();

            if (auctionItem.getBidderName().equals(b2.getBidderName())) {
                NotificationService.notifyAll("Bidder " + b2.getBidderName() + " has been outbidded.");
                auctionItem.setBidderName(b1.getBidderName());
                double dd= auctionItem.getCurrentBid();
                auctionItem.setCurrentBid(dd++);
            }
        }
        saveAuctionItem(auctionItem);
    }
}
