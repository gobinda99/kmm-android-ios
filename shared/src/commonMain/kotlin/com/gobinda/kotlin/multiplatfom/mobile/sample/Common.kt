import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier


fun updateShareModuleLog(enable: Boolean) {
    if(enable) {
        Napier.base(DebugAntilog())
    }
}