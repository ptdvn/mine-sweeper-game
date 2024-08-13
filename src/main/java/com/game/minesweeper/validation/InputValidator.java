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

package com.game.minesweeper.validation;

import com.game.minesweeper.constants.Constants;
import com.game.minesweeper.converter.NumberConverter;
import com.game.minesweeper.dto.BoardInfoDTO;

public class InputValidator {

private InputValidator() {}

    public static boolean  isValidSquareAddress(String input, BoardInfoDTO boardInfoDTO) {
        try {
            if (input.length() != 2) {
                System.err.println("Invalid address length. Please try again.");
                return false;
            }
            int rowIndex = NumberConverter.convertCharToIntIgnoreCase(input.charAt(Constants.ROW_INDEX));
            int columnIndex = Integer.parseInt(String.valueOf(input.charAt(Constants.COLUMN_INDEX))) - 1;
            return isValidIndex(rowIndex, columnIndex, boardInfoDTO);
        } catch (IllegalArgumentException exception) {
            System.err.println("That's not a valid square. Please try again.");
            return false;
        }
    }

    private static boolean isValidIndex(int rowIndex, int columnIndex, BoardInfoDTO boardInfoDTO) {
        int maxIndex = boardInfoDTO.getGridSize() - 1;
        if ((0 > rowIndex || rowIndex > maxIndex)
                || (0 > columnIndex || columnIndex > maxIndex)
        ) {
            System.err.println("That's not a valid square index. Please try again.");
            return false;
        }
        return true;
    }


}
