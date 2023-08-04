package com.BoardAPI.BoardAPI.Service;

import com.BoardAPI.BoardAPI.Models.Cards;
import com.BoardAPI.BoardAPI.Repository.CardRepository;
import com.BoardAPI.BoardAPI.RequestObject.GetCardRequestObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetCardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    private static Map<Long,List<Cards>> cardsInBoard =new HashMap<>();

    //Create card
    public Cards cardsInBoard (Long card_id,Cards cards){
       cards.setSection("2");
       Cards saveCard =cardRepository.save(cards);
       return saveCard;
    }
//    unique card ID
    private static Long uniqueCardId(){
        return new Random().nextLong();

    }

    //    get all boards
    public List<Cards> getCards(){
        return cardRepository.findAll();
    }

    //    get by ID
    public GetCardResponseObject getById(Long card_id){
        Optional<Cards> optionalCards=cardRepository.findById(card_id);
        if (optionalCards.isPresent()){
            Cards cards=optionalCards.get();
            GetCardResponseObject response=new GetCardResponseObject();
            response.setDescription(cards.getDescription());
            response.setTitle(cards.getTitle());
            response.setSection(cards.getSection());
            return response;
        }
        return null;

    }
    //update
    public GetCardResponseObject updateCard(Long card_id, GetCardRequestObject updateCard) {
        Optional<Cards> optionalCards = cardRepository.findById(card_id);
        if (optionalCards.isPresent()) {
            Cards cards = optionalCards.get();
            cards.setTitle(updateCard.getTitle());            // Update title
            cards.setDescription(updateCard.getDescription());  // Update description
            cards.setSection(updateCard.getSection());          // Update section
            cardRepository.save(cards);
            return getById(card_id);
        }
        return null;
    }

    //    delete
    public  void deleteCard(Long card_id){cardRepository.deleteById(card_id);}



}
