package com.BoardAPI.BoardAPI.Controller;

import com.BoardAPI.BoardAPI.Models.Cards;
import com.BoardAPI.BoardAPI.RequestObject.GetCardRequestObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetCardResponseObject;
import com.BoardAPI.BoardAPI.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/{boardId}")
    public ResponseEntity<GetCardResponseObject> cardsInBoard(@PathVariable("boardId") Long boardId, @RequestBody GetCardRequestObject cardRequestObject) {
        Cards cards = new Cards();
        cards.setSection(cardRequestObject.getSection());
        cards.setTitle(cardRequestObject.getTitle());
        cards.setDescription(cardRequestObject.getDescription());
        Cards cardCreated = cardService.cardsInBoard(boardId, cards);
        GetCardResponseObject responseObject = new GetCardResponseObject();
        responseObject.setSection(cardCreated.getSection());
        responseObject.setTitle(cardCreated.getTitle());
        responseObject.setDescription(cardCreated.getDescription());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }
//    get cards
    @GetMapping
    public List<Cards>getCards(){
        return cardService.getCards();

    }

//    get by id
    @GetMapping("/{card_id}")
    public GetCardResponseObject getById (@PathVariable Long card_id ){
        return cardService.getById(card_id);
    }
//    Delete
    @DeleteMapping("/{card_id}")
    public void deleteCard (@PathVariable Long card_id){
        cardService.deleteCard(card_id);
    }

// update
    @PutMapping("/{card_id}")
    public ResponseEntity<GetCardResponseObject>updateCard(@PathVariable Long card_id,@RequestBody GetCardRequestObject updateCard){
        GetCardResponseObject responseObject=cardService.updateCard(card_id,updateCard);
        if (responseObject !=null){
            return ResponseEntity.ok(responseObject);

        }else {
            return ResponseEntity.notFound().build();
        }



    }

}
