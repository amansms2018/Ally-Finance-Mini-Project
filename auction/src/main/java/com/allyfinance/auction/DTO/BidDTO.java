package com.allyfinance.auction.DTO;

import com.allyfinance.auction.model.AuctionItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BidDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long  bidId;
    private double maxAutoBidAmount;
    private String bidderName;

//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "actionItemId", nullable=false)
    private AuctionItem auctionItem;

    }

