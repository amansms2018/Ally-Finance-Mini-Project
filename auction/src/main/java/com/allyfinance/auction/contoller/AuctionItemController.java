package com.allyfinance.auction.contoller;

import com.allyfinance.auction.customException.MyException;
import com.allyfinance.auction.model.AuctionItem;
import com.allyfinance.auction.service.AuctionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class AuctionItemController {

    @Autowired
    private AuctionItemService auctionItemService;
    @PostMapping("/auctionItems")
    public ResponseEntity<Long> addAuctionItem(@RequestBody AuctionItem auctionItem){
         if( auctionItem!=null) {
             return  new ResponseEntity<Long>(auctionItemService.saveAuctionItem(auctionItem).getActionItemId(), HttpStatus.CREATED);
         }
        else
            return  new ResponseEntity<Long>((MultiValueMap<String, String>) new FileNotFoundException("please enter Auction data following the field "+ auctionItem.toString()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/auctionItems/{auctionItemId}")
    public ResponseEntity<?> getAuctionItems(@PathVariable("auctionItemId") Long auctionItemId) {
//        return auctionService.findAuctionById(auctionItemId).orElseThrow(
//                {() -> new ResourceNotFoundException("Auction not found with auctionItemId " + auctionItemId);
//                }
//    );
        Optional<AuctionItem> auctionItem = auctionItemService.findAuctionById(auctionItemId);

        if(auctionItem.isPresent())
            return  new ResponseEntity<>(auctionItem.get(),HttpStatus.OK);
        else
            return  new ResponseEntity<>(new MyException("Auction  is  not found with auctionItemId " + auctionItemId),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/auctionItems")
    public ResponseEntity<List<AuctionItem>> getAllAuctionItems() {
        List<AuctionItem> auctionItems = auctionItemService.findAllAuction();
        return new ResponseEntity<>(auctionItems, HttpStatus.OK);

    }
}
