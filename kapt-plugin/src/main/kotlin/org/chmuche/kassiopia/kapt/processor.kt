package org.chmuche.kassiopia.kapt

import sun.awt.FontConfiguration.loadProperties
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


/**
 * @author chmuchme
 * @since 0.1
 * on 30/09/17.
 */

class KassiopiaProcessor : AbstractProcessor() {

    private lateinit var logger: Logger

    override fun getSupportedSourceVersion(): SourceVersion {
        print("LOL1")
        return SourceVersion.latest()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        print("LOL2")
        return mutableSetOf("org.chmuche.kassiopia.api.Model")
    }

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)

        logger = Logger(processingEnv.messager, true)
        logger.debug("init")
        print("LOL")
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        for(ann in annotations){
            for(model in roundEnv.getElementsAnnotatedWith(ann)){
                logger.note(model)
            }
        }
        return true
    }
}

private class Logger(val messager: Messager, val debug: Boolean = true) {

    fun debug(msg: Any?) {
        if (debug) {
            messager.printMessage(Diagnostic.Kind.NOTE, Objects.toString(msg))
        }
    }

    fun note(msg: Any?) {
        messager.printMessage(Diagnostic.Kind.NOTE, Objects.toString(msg))
    }

    fun warn(msg: Any?) {
        messager.printMessage(Diagnostic.Kind.WARNING, Objects.toString(msg))
    }

    fun error(msg: Any?) {
        messager.printMessage(Diagnostic.Kind.ERROR, Objects.toString(msg))
    }
}
