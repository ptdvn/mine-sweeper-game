package com.game.minesweeper.injector;

import com.game.minesweeper.controller.Controller;

/**
 * This interface defines the contract for a service injector in the application.
 * It provides a method to get a GameController instance.
 * Implementations of this interface are responsible for setting up the dependencies
 * for the GameController, such as the GameService.
 *
 * @author Phuoc Dang
 * @version 1.0
 * @since 2024
 */
public interface GameServiceInjector {
    Controller getGameController();
}
