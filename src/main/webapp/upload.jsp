<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
    <head>
        <title>RichFaces File Upload</title>
    </head>

    <body>
        <f:view>
            <h:form enctype="multipart/form-data">
                <h:panelGrid columns="2" cellpadding="3" cellspacing="0" styleClass="sectionHeaderTable" id="fileupload">
                    <rich:fileUpload fileUploadListener="#{processDatFileBean.listener}" id="upload" acceptedTypes="jpg, gif, png, bmp"
                                    immediateUpload="false" maxFilesQuantity="#{processDatFileBean.uploadsAvailable}" noDuplicate="true"
                                    ontyperejected="alert('Only JPG, GIF, PNG and BMP files are accepted');"
                                    onerror="alert('Error in uploader');">
                        <a4j:support event="onclear" action="#{processDatFileBean.clearUploadData}" reRender="fileupload" />
                    </rich:fileUpload>
                </h:panelGrid>
            </h:form>
        </f:view>
     </body>
 </html>