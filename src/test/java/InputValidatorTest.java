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

import com.game.minesweeper.dto.BoardInfoDTO;
import com.game.minesweeper.validation.InputValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidatorTest {

    @Test
    public void testIsValidSquareAddress() {
        // Create a BoardInfoDTO with a grid size of 5
        BoardInfoDTO boardInfoDTO = new BoardInfoDTO();
        boardInfoDTO.setGridSize(5);

        // Test valid square address
        String validInput = "A1";
        assertTrue(InputValidator.isValidSquareAddress(validInput, boardInfoDTO));

        // Test invalid square address (out of grid)
        String invalidInputOutOfGrid = "F1";
        assertFalse(InputValidator.isValidSquareAddress(invalidInputOutOfGrid, boardInfoDTO));

        // Test invalid square address (wrong format)
        String invalidInputWrongFormat = "1A";
        assertFalse(InputValidator.isValidSquareAddress(invalidInputWrongFormat, boardInfoDTO));
    }

}
