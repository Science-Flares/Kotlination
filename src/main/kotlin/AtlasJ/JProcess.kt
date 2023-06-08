package AtlasJ

import java.io.File

class JProcess {
    lateinit var process: Process
    lateinit var processBuilder: ProcessBuilder
    lateinit var processHandleInfo: ProcessHandle.Info
    init {
        with(process){
            this.errorStream.run {
            }
            this.inputStream.run {
            }
            this.outputStream.run {
            }
            this.isAlive
            this.children()
            this.descendants()
            this.destroy()
            this.destroyForcibly()
            this.exitValue()
            this.info()
            this.onExit()
            this.pid()
            this.supportsNormalTermination()
            this.toHandle()
            this.waitFor()
        }
        processBuilder.run {
            this.command()
            this.directory()
            this.environment()
            this.inheritIO()
            this.redirectError()
            this.redirectErrorStream()
            this.redirectInput()
            this.redirectOutput()
            this.start()
        }
        ProcessBuilder.Redirect.DISCARD
        ProcessBuilder.Redirect.INHERIT
        ProcessBuilder.Redirect.PIPE.run {
            this.file()
            this.type()
        }

        ProcessBuilder.Redirect.appendTo(File(""))

        ProcessBuilder.Redirect.from(File(""))

        ProcessBuilder.Redirect.Type.APPEND
        ProcessBuilder.Redirect.Type.INHERIT
        ProcessBuilder.Redirect.Type.PIPE
        ProcessBuilder.Redirect.Type.READ
        ProcessBuilder.Redirect.Type.WRITE

        ProcessBuilder.startPipeline(listOf()).run {
        }

        //
        processHandleInfo.run {
            this.arguments()
            this.command()
            this.commandLine()
            this.startInstant()
            this.totalCpuDuration()
            this.user()
        }
    }
}