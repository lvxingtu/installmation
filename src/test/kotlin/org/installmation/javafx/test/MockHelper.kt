/*
 * Copyright 2019 Serge Merzliakov
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

package org.installmation.javafx.test

import io.mockk.every
import org.installmation.ui.dialog.DialogResult
import org.installmation.ui.dialog.openFileDialog
import java.io.File

/**
 * Setup various mocks
 */
object MockHelper {

   /**
    * Mock wrapper around JFX file chooser ChooseFileDialog so we can mock
    * loading files from files system
    */
   fun mockOpenFileDialog(fileChosen: String) {
      every { openFileDialog(any(), any(), any(), any()) } returns DialogResult(true, File(fileChosen))
   }
}