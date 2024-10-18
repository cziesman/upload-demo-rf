# Building the WAR file

You will need to have Maven installed to build the WAR file for deployment.

Use the following command to generate the WAR file:

    mvn clean install

The file `upload-demo-rf.war` will be created in the `target` directory, and can be deployed to EAP from there.

# Adding a module for Primefaces

Create a directory at `$EAP_HOME/modules/org/primefaces/main`

Create the following `module.xml` file

    <module xmlns="urn:jboss:module:1.3" name="org.richfaces">
    
        <resources>
            <resource-root path="atmosphere-runtime-2.2.0.jar"/>
            <resource-root path="cssparser-0.9.14.jar"/>
            <resource-root path="guava-18.0.jar"/>
            <resource-root path="richfaces-components-a4j-4.5.0.Final.jar"/>
            <resource-root path="richfaces-components-rich-4.5.0.Final.jar"/>
            <resource-root path="richfaces-core-4.5.0.Final.jar"/>
            <resource-root path="sac-1.3.jar"/>
        </resources>
    
        <dependencies>
            <module name="com.sun.jsf-impl" />
            <module name="javaee.api" />
            <module name="javax.faces.api" />
        </dependencies>
    </module>

Copy the three JAR files listed in `module.xml` to `$EAP_HOME/modules/org/primefaces/main`

Use the command line to add the new module to the EE subsystem.

    ./jboss-cli.sh -c
    
    [standalone@localhost:9990 /] /subsystem=ee:write-attribute(name=global-modules, value=[{"name"=>"org.primefaces","slot"=>"main"}])
    
    {"outcome" => "success"}

Restart the EAP server and deploy the WAR file.
