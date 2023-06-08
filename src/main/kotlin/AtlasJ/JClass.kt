package AtlasJ

import java.net.URL
import java.util.function.Consumer
import java.util.function.Supplier

class JClass {
    lateinit var some: Any

    init {
        some.javaClass.run {
            this.asSubclass(componentType)
            this.annotatedInterfaces
            this.annotatedSuperclass.run {
                this.type
                this.annotatedOwnerType
//                this.getDeclaredAnnotation() // TODO
//                this.getDeclaredAnnotationsByType() // TODO
            }
            this.annotations
            this.cast(object {})
            this.classes
            this.classLoader.run {
                this.definedPackages
                this.getDefinedPackage("").run {
                }
                this.isRegisteredAsParallelCapable
                this.parent.run {
                }
                this.unnamedModule.run {
                }
                this.clearAssertionStatus()
                this.resources("").run {
                }
                this.getResources("").run {
                    this.hasMoreElements()
                    this.nextElement()
                    this.asIterator()
                    this.asSequence()
                }
                this.loadClass("").run {
                }
                this.setClassAssertionStatus("", true)
                this.setDefaultAssertionStatus(true)
                this.setPackageAssertionStatus("", true)
            }
            this.canonicalName //
            this.componentType
            this.constructors
            this.declaredClasses
            this.declaringClass
            this.declaredConstructors
            this.declaredFields
            this.declaredMethods
            this.desiredAssertionStatus()
            this.declaredAnnotations
            this.enclosingClass
            this.enclosingConstructor.run {
                this.newInstance()
                this.annotatedExceptionTypes
                this.annotatedParameterTypes
                this.annotatedReceiverType.run {
                    this.type.typeName
                    this.annotatedOwnerType
                }
                this.annotatedReturnType.run {
                }
                this.declaringClass
                this.isVarArgs
//            this.isAnnotationPresent() // TODO
                this.exceptionTypes
                this.genericExceptionTypes
                this.genericParameterTypes
                this.toGenericString()
                this.parameterAnnotations
                this.parameterCount
                this.parameterTypes
                this.canAccess("")
                this.trySetAccessible()
            }
            this.enclosingMethod.run {
                this.defaultValue
                this.genericReturnType
                this.isBridge
                this.isDefault
                this.returnType
            }
            this.enumConstants
            this.fields
            this.getField("").run {
                this.annotatedType
                this.genericType
                this.isEnumConstant
                this.get("") // All the variables.
                this.set("", "") // All the variables.
            }
            this.getDeclaredField("").run {
            }
            this.getConstructor().run {
            }
            this.getDeclaredConstructor().run {
            }
            this.getMethod("").run {
            }
            this.getDeclaredMethod("").run {
            }
            this.getResource("").run {
                this.authority
                this.content
                this.defaultPort
                this.file
                this.host
                this.path
                this.port
                this.protocol
                this.query
                this.ref
                this.userInfo
                this.content
                this.openConnection().run {
                }
                this.openStream().run {
                }
                this.sameFile(URL(""))
                this.toExternalForm()
                this.toExternalForm()
                this.readBytes()
                this.readText()
            }
            this.getResourceAsStream("").run {
                /*...*/
            }
//            this.getAnnotation() // TODO
//            this.getAnnotationsByType() / TODO
            this.genericInterfaces
            this.genericSuperclass
            this.interfaces //
            this.isAnnotation
            this.isAnonymousClass
            this.isArray
            this.isEnum
            this.isLocalClass
            this.isMemberClass //
            this.isPrimitive
            this.isSynthetic
            this.isAssignableFrom(String::class.java)
            this.isInstance(String)
            this.isNestmateOf(String::class.java)
//            this.isAnnotationPresent() // TODO
            this.kotlin.run {
                /*...*/
            }
            this.methods
            this.modifiers
            this.module.run {
                this.name
                this.packages.run {
                }
                this.classLoader.run {
                    this.definedPackages
                    this.getDefinedPackage("")
                    this.isRegisteredAsParallelCapable
                    this.name
                    this.parent
                    this.unnamedModule
                    this.clearAssertionStatus()
                    this.getResource("")
                    this.getResourceAsStream("")
                    this.getResources("").run {
                    }
                    this.loadClass("")
                    this.resources("")
                    this.setClassAssertionStatus("",true)
                    this.setDefaultAssertionStatus(true)
                    this.setPackageAssertionStatus("",true)
                }
                this.descriptor.run {
                    this.isAutomatic
                    this.isOpen
                    this.exports()
                    this.mainClass().run {
                        this.isEmpty
                        this.isPresent
                        this.filter(null)
//                        this.flatMap() // TODO
                        this.ifPresent(Consumer {  })
                        this.ifPresentOrElse({  }, {  })
                        this.or(Supplier { null })
                        this.orElse("")
                        this.orElseGet(Supplier { "" })
                        this.orElseThrow()
//                        this.map() // TODO
                        this.stream()
                    }
                    this.modifiers()
                    this.name()
                    this.opens()
                    this.packages()
                    this.provides()
                    this.rawVersion().run {
                    }
                    this.requires()
                    this.toNameAndVersion()
                    this.uses()
                    this.version().run {
                    }
                }
                this.isNamed
                this.layer.run {
                }
                this.addExports("",module)
                this.addOpens("",module)
                this.addReads(module)
                this.addUses(null)
                this.canRead(module)
                this.canUse(null)
                this.getResourceAsStream("").run {
                }
                this.isExported("",module)
                this.isOpen("",module)
            }
            this.name
            this.nestHost
            this.nestMembers
            this.`package`.run {
                this.name
                this.implementationTitle
                this.implementationVendor
                this.implementationVersion
                this.isSealed
                this.specificationTitle
                this.specificationVendor
                this.specificationVersion
                this.isCompatibleWith("")
            }
            this.packageName
            this.protectionDomain.run {
                this.classLoader
                this.codeSource.run {
                    this.certificates
                    this.codeSigners
                    this.location.run {
                    }
                }
                this.permissions.run {
                    this.isReadOnly
                    this.setReadOnly()
                    this.add(null)
                    this.elements()
                    this.elementsAsStream()
                }
                this.principals
                this.implies(null)
                this.staticPermissionsOnly()
            }
            this.signers
            this.simpleName
            this.superclass
            this.toGenericString()
            this.typeName
            this.typeParameters
        }
    }
}