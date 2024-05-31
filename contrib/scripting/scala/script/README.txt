Scala Script Engine

JSR 223 compliant Scala Scripting engine. The scripting engine supports
running in an OSGi container or as part of a stand alone application. 
It has optional support for compiling from/to a JCR repository. 

Getting Started
===============

This component uses a Maven 2 (http://maven.apache.org/) build
environment. It requires a Java 5 JDK (or higher) and Maven 
(http://maven.apache.org/) 2.2.1 or later. We recommend to use 
the latest Maven version.

If you have Maven 2 installed, you can compile and
package the jar using the following command:

    mvn package
    
To Install the package in a running Sling instance use:

    mvn sling:install

See the Maven 2 documentation for other build features.
