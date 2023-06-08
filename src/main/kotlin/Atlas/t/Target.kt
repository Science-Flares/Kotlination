package t

import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.Target

/**
 * This meta-annotation indicates the kinds of code elements which
 * are possible targets of an annotation.
 * and this targets available in AnnotationTarget class.
 */
@Target(CLASS) // mean This meta-annotation using only for classes.
annotation class Target ()
