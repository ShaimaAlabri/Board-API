package com.BoardAPI.BoardAPI.Controller;

import com.BoardAPI.BoardAPI.Models.Cards;
import com.BoardAPI.BoardAPI.RequestObject.GetCardRequestObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetCardResponseObject;
import com.BoardAPI.BoardAPI.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/cards")
@CrossOrigin("*")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<GetCardResponseObject> createCardInBoard(@PathVariable("boardId") Long boardId, @RequestBody GetCardRequestObject cardRequestObject) {
        Cards cards = new Cards();
        cards.setSection(cardRequestObject.getSection());
        cards.setTitle(cardRequestObject.getTitle());
        cards.setDescription(cardRequestObject.getDescription());
        Cards cardCreated = cardService.createCard(boardId, cards);

        GetCardResponseObject responseObject = new GetCardResponseObject();
        responseObject.setCardId(cardCreated.getCard_id());
        responseObject.setSection(cardCreated.getSection());
        responseObject.setTitle(cardCreated.getTitle());
        responseObject.setDescription(cardCreated.getDescription());

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping
    public List<Cards> getCardsInBoard(@PathVariable("boardId") Long boardId) {
        return cardService.getCardsInBoard(boardId);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<GetCardResponseObject> getCardById(@PathVariable Long cardId) {
        GetCardResponseObject card = cardService.getById(cardId);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<GetCardResponseObject> updateCard(@PathVariable Long cardId, @RequestBody GetCardRequestObject updateCard) {
        GetCardResponseObject updatedCard = cardService.updateCard(cardId, updateCard);
        if (updatedCard != null) {
            return ResponseEntity.ok(updatedCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
