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

package org.installmation.model.binary

import org.installmation.io.PathValidator
import java.io.File

abstract class AbstractJDK(usersJDKName:String, fullJDKPath: File) : JDK {

   // subclasses must define the OS and version specific values for these
   protected abstract val binaryDirectory: String
   protected abstract val javaExecutableName: String
   protected abstract val jpackageExecutableName: String
   protected abstract val jdepsExecutableName: String

   override val name = usersJDKName
   override val path = fullJDKPath

   override val supportsJPackage: Boolean
      get() = File(File(path, binaryDirectory), jpackageExecutableName).exists()

   override val javaExecutable: File
      get() = PathValidator.verifyFile(File(path, binaryDirectory), javaExecutableName)

   override val packageExecutable: File
      get() = PathValidator.verifyFile(File(path, binaryDirectory), jpackageExecutableName)

   override val jdepsExecutable: File
      get() = PathValidator.verifyFile(File(path, binaryDirectory), jdepsExecutableName)

   override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other !is AbstractJDK) return false

      if (operatingSystem != other.operatingSystem) return false
      if (path.absolutePath != other.path.absolutePath) return false

      return true
   }

   override fun hashCode(): Int {
      var result = operatingSystem.hashCode()
      result = 31 * result + path.hashCode()
      return result
   }

   override fun toString(): String {
      return name
   }
}