/*
 * Copyright 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.minesweeper.injector;

import com.game.minesweeper.controller.MineSweeperController;
import com.game.minesweeper.controller.Controller;
import com.game.minesweeper.service.impl.CLIGameServiceImpl;
import com.game.minesweeper.service.impl.SimpleBoardServiceImpl;

/**
 * This class is an implementation of the GameServiceInjector interface.
 * It provides a method to get a GameController instance.
 * The GameController instance is created with a CLIGameServiceImpl instance,
 * which in turn is created with a SimpleBoardServiceImpl instance.
 * This setup represents the dependency injection for the application.
 *
 * @author Phuoc Dang
 * @version 1.0
 * @since 2024
 */
public class CLIGameServiceInjectorImpl implements GameServiceInjector {

    /**
     * This method creates and returns a GameController instance.
     * The GameController is created with a CLIGameServiceImpl instance,
     * which is created with a SimpleBoardServiceImpl instance.
     *
     * @return a GameController instance with the necessary dependencies
     */
    @Override
    public Controller getGameController() {
        return new MineSweeperController(new CLIGameServiceImpl(new SimpleBoardServiceImpl()));
    }
}
