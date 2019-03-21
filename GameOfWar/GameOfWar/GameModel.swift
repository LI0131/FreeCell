//
//  GameModel.swift
//  GameOfWar
//
//  Created by William McCann on 3/19/19.
//  Copyright © 2019 William McCann. All rights reserved.
//

import Foundation

class GameModel {
    
    var player_deck: Deck
    var comp_deck: Deck
    var player_score: Int
    var comp_score: Int
    
    init(deck: Deck) {
        (player_deck, comp_deck) = GameModel.dealDeck(deck: deck)
        player_score = 0
        comp_score = 0
    }
    
    class func dealDeck(deck:Deck) -> (Deck, Deck) {
        var counter = 0
        var player_deck = [Card]()
        var comp_deck = [Card]()
        for card in deck {
            if counter > 25 {
                player_deck.append(card)
            } else {
                comp_deck.append(card)
            }
            counter += 1
        }
        return (Deck(cardlist: player_deck), Deck(cardlist: comp_deck))
    }
    
    public func hasWinner() -> Bool {
        if player_deck.isEmpty() || comp_deck.isEmpty() {
            return true
        } else {
            return false
        }
    }
    
    public func getPlayerDeck() -> Deck {
        return player_deck
    }
    
    public func getCompDeck() -> Deck {
        return comp_deck
    }
    
    public func getPlayerScore() -> Int {
        return player_score
    }
    
    public func getCompScore() -> Int {
        return comp_score
    }
    
    
}
