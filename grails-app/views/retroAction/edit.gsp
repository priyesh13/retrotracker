<!DOCTYPE html>
<html>
   <head>
   </head>
   <body>
      <div class="body edit">
         <h1>${retroActionInstance.id ? 'Edit' : 'Create'} Retro Action</h1>

         <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
         </g:if>
         <g:hasErrors bean="${retroActionInstance}">
            <div class="errors">
               <g:renderErrors bean="${retroActionInstance}" as="list"/>
            </div>
         </g:hasErrors>

         <g:form method="post" action="${retroActionInstance.id ? 'save' : 'save'}">
            <g:hiddenField name="id" value="${retroActionInstance?.id}"/>
            <g:hiddenField name="version" value="${retroActionInstance?.version}"/>
            <div class="dialog">
               <g:render template="form"/>
            </div>

            <div class="buttons">
               <span class="button"><g:actionSubmit name="save" class="save btn success large" action="${retroActionInstance.id ? 'update' : 'save'}" value="Save"/></span>
               <span class="button"><g:actionSubmit name="delete" class="delete btn success large" action="delete" value="Delete"/></span>
            </div>
         </g:form>
      </div>
      <r:script>
        addAutoComplete();
      </r:script>
   </body>
</html>