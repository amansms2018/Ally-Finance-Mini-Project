package com.allyfinance.auction.model;

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
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long  bidId;
    private double maxAutoBidAmount;
//    private  double  currentBid;
    private String bidderName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "actionItemId", nullable=false)
    private AuctionItem auctionItem;

    }

