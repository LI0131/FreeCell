//
//  GameScene.swift
//  GameOfWar
//
//  Created by William McCann on 3/13/19.
//  Copyright © 2019 William McCann. All rights reserved.
//

import SpriteKit
import GameplayKit

enum Level: CGFloat {
    case top = 100
    case bottom = 0
}

class GameScene: SKScene {
    
    private var currentCard : SKSpriteNode?
    private var comp_currentCard: SKSpriteNode?
    private var player_deck : Deck?
    private var comp_deck: Deck?
    private var model: GameModel?
    private let played_loc : CGPoint = CGPoint(x: 150, y: -300)
    private let comp_played_loc: CGPoint = CGPoint(x: -150, y: 300)
    private var player_score: SKLabelNode?
    private var comp_score: SKLabelNode?
    private var new_game: SKLabelNode = SKLabelNode(text: "New Game")
    private var winner: SKLabelNode?
    private var gamelable: SKLabelNode = SKLabelNode(text: "Game Of War")
    
    override func didMove(to view: SKView) {
        
        let deck = Deck()
        self.model = GameModel(deck: deck)
        self.player_deck = self.model?.getPlayerDeck()
        self.comp_deck = model?.getCompDeck()
        for card in player_deck ?? deck {
            card.position = CGPoint(x: -150, y: -300)
            addChild(card)
        }
        for card in comp_deck ?? deck {
            card.position = CGPoint(x: 150, y: 300)
            addChild(card)
        }
        
        self.gamelable.position = CGPoint(x: 0, y: 500)
        self.gamelable.fontSize = 72
        addChild(self.gamelable)
        
        self.player_score = SKLabelNode(text: "Player Score: " + (self.model?.getPlayerScore().description)!)
        self.player_score!.position = CGPoint(x: -150, y: 0)
        addChild(self.player_score!)
        self.comp_score = SKLabelNode(text: "Computer Score: " + (self.model?.getCompScore().description)!)
        self.comp_score!.position = CGPoint(x: 150, y: 0)
        addChild(self.comp_score!)
        
        self.new_game.position = CGPoint(x: 0, y: -50)
        addChild(self.new_game)
        
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        for t in touches {
            let loc = t.location(in: self)
            if let card = atPoint(loc) as? Card {
                if (self.player_deck?.contains(card))! && card != atPoint(played_loc){
                    let topCard = self.player_deck?.getTopCard()
                    let compTopCard = self.comp_deck?.getTopCard()
                    
                    if topCard!.rank >= compTopCard!.rank {
                        self.model?.player_score += 1
                    } else {
                        self.model?.comp_score += 1
                    }
                    
                    topCard?.zPosition = Level.top.rawValue
                    compTopCard?.zPosition = Level.top.rawValue
                    
                    topCard?.position = self.played_loc
                    compTopCard?.position = self.comp_played_loc
                    
                    let lastCard = self.currentCard
                    let comp_lastCard = self.comp_currentCard
                    
                    lastCard?.zPosition = Level.bottom.rawValue
                    comp_lastCard?.zPosition = Level.bottom.rawValue
                    
                    self.currentCard = topCard
                    self.comp_currentCard = compTopCard
                    self.comp_score?.text = "Computer Score: " + (self.model?.getCompScore().description)!
                    self.player_score?.text = "Player Score: " + (self.model?.getPlayerScore().description)!
                }
            } else {
        
                let objects = nodes(at: loc)
                if objects.contains(self.new_game){
                    if let newScene = GameScene(fileNamed: "GameScene") {
                        let view = self.view! as SKView
                        view.ignoresSiblingOrder = true
                        newScene.scaleMode = .aspectFill
                        view.presentScene(newScene, transition: SKTransition.fade(withDuration: 1.0))
                        
                    }
                }
        
            }
            
        }
    }

    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
    }
    
    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
    }
    
    override func update(_ currentTime: TimeInterval) {
        if self.player_deck!.isEmpty() && self.childNode(withName: "winner") == nil {
            self.winner = SKLabelNode(text: "")
            self.winner?.name = "winner"
            self.winner?.position = CGPoint(x: 0, y: 50)
            if self.model!.getPlayerScore() > self.model!.getCompScore() {
                self.winner?.text = "You Win!!"
            } else if self.model!.getPlayerScore() == self.model!.getCompScore() {
                self.winner?.text = "It's a tie!!"
            } else {
                self.winner?.text = "You Lost!!"
            }
            addChild(self.winner!)
            self.new_game.fontColor = UIColor.yellow
        }
    }
}
