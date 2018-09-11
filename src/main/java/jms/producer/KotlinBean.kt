package jms.producer

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware


class KotlinBean : ApplicationContextAware {

    private var context: ApplicationContext? = null
    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        this.context = applicationContext
    }
}