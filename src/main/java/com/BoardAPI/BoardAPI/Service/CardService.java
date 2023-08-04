package com.BoardAPI.BoardAPI.Service;

import com.BoardAPI.BoardAPI.Models.Board;
import com.BoardAPI.BoardAPI.Models.Cards;
import com.BoardAPI.BoardAPI.Repository.BoardRepository;
import com.BoardAPI.BoardAPI.Repository.CardRepository;
import com.BoardAPI.BoardAPI.RequestObject.GetBoardRequestObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetBoardResponseObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetCardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    private static Map<Long,List<Cards>> cardsInBoard =new HashMap<>();

    //Create board
    public Cards addCards (Long card_id,Cards cards){
       cards.setSection("2");
       Cards saveCard =cardRepository.save(cards);
       return saveCard;
    }
//    unique card ID
    private static Long uniqueCardId(){
        return new Random().nextLong();

    }

    //    get all boards
    public List<Cards> getCard(){
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
    public GetCardResponseObject updateCard(Long card_id, GetBoardResponseObject updateCard){
        Optional<Cards> optionalCards=cardRepository.findById(card_id);
        if (optionalCards.isPresent()){
            Cards cards=optionalCards.get();
            cards.setTitle(cards.getTitle());
            cards.setDescription(cards.getDescription());
            cards.setSection(cards.getSection());
            cardRepository.save(cards);
            return getById(card_id);
        }
        return null;

    }
    //    delete
    public  void deleteCard(Long id){cardRepository.deleteById(id);}



}
