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

package com.game.minesweeper.converter;

import static com.game.minesweeper.constants.Constants.CHAR_A;
import static com.game.minesweeper.constants.Constants.CHAR_Z;

public class NumberConverter {

    private NumberConverter() {}
    /**
     * Converts a character to an integer, ignoring case.
     * The character must be a letter between 'A' and 'Z' or 'a' and 'z' (inclusive).
     * The conversion is based on the ASCII value of the character, where 'A' or 'a' is converted to 0, 'B' or 'b' to 1, and so on.
     *
     * @param character the character to convert, must be a letter between 'A' and 'Z' or 'a' and 'z' (inclusive)
     * @return the integer representation of the character, where 'A' or 'a' is 0, 'B' or 'b' is 1, and so on
     * @throws IllegalArgumentException if the character is not a letter between 'A' and 'Z' or 'a' and 'z'
     */
    public static int convertCharToIntIgnoreCase(char character) {
        Character upperCaseChar = Character.toUpperCase(character);
        if (upperCaseChar >= CHAR_A && upperCaseChar <= CHAR_Z) {
            return upperCaseChar - CHAR_A;
        } else {
            throw new IllegalArgumentException("Character must be between A and Z");
        }
    }

    /**
     * Converts an integer to a character.
     * The integer must be between 0 and 25 (inclusive).
     * The conversion is based on the ASCII value of the character, where 0 is converted to 'A', 1 to 'B', and so on.
     *
     * @param integer the integer to convert, must be between 0 and 25 (inclusive)
     * @return the character representation of the integer, where 0 is 'A', 1 is 'B', and so on
     * @throws IllegalArgumentException if the integer is not between 0 and 25
     */
    public static char convertIntToChar(int integer) {
        if (integer >= 0 && integer <= 25) {
            return (char) (CHAR_A + integer);
        } else {
            throw new IllegalArgumentException("Integer must be between 0 and 25");
        }
    }
}
