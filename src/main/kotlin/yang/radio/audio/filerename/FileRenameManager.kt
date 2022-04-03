package yang.radio.audio.filerename

import org.springframework.stereotype.Service
import java.io.File

@Service
class FileRenameManager {

    fun fileRename() {

        var oldFileList = readOldFileNameList("C:\\download\\wonderful_old")

        for(oldFile in oldFileList) {

            var fileName = oldFile.absolutePath

            var newFileName = fileName.replace("leeseokhun", "leeseokhoon")
            newFileName = newFileName.replace("wonderful_old", "wonderful_new")

            println(newFileName)

            var newFile = File(newFileName)
            var isSuccess = oldFile.renameTo(newFile)
            if(isSuccess == false) {
                throw Exception("File to rename. ${newFileName}")
            }
        }
    }

    private fun readOldFileNameList(dir: String): List<File> {

        var dirFile = File(dir)
        return dirFile.listFiles().toList()
    }
}