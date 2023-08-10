
package com.BoardAPI.BoardAPI.Service;

import com.BoardAPI.BoardAPI.Models.Cards;
import com.BoardAPI.BoardAPI.Repository.CardRepository;
import com.BoardAPI.BoardAPI.RequestObject.GetCardRequestObject;
import com.BoardAPI.BoardAPI.ResponseObject.GetCardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Cards createCard(Long boardId, Cards card) {
        // Set any board-specific properties here if needed
        // For example: card.setBoardId(boardId);
        return cardRepository.save(card);
    }

    public List<Cards> getCardsInBoard(Long boardId) {
        // Implement the logic to fetch cards within a specific board
        // For example: return cardRepository.findByBoardId(boardId);
        return cardRepository.findAll(); // Placeholder example
    }

    public GetCardResponseObject getById(Long cardId) {
        Optional<Cards> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            Cards card = optionalCard.get();
            return new GetCardResponseObject(card.getCard_id(), card.getSection(), card.getTitle(), card.getDescription());
        }
        return null;
    }

    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    public GetCardResponseObject updateCard(Long cardId, GetCardRequestObject updateCard) {
        Optional<Cards> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            Cards card = optionalCard.get();
            card.setSection(updateCard.getSection());
            card.setTitle(updateCard.getTitle());
            card.setDescription(updateCard.getDescription());
            cardRepository.save(card);
            return new GetCardResponseObject(card.getCard_id(), card.getSection(), card.getTitle(), card.getDescription());
        }
        return null;
    }
}

