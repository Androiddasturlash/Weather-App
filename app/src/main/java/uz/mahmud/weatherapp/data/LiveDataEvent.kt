package uz.mahmud.weatherapp.data

data class LiveDataEvent<out T>(private val content: T) {

    private var hasBeanHandled = false

    fun getContentIfNotHamdled(): T? {
        return if(hasBeanHandled) {
            null
        }else {
            hasBeanHandled = true
            content
        }
    }
}
