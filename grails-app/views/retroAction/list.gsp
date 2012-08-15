<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta name="layout" content="main"/>
      <title>Retro Action Tracker</title>
      <g:set var="entityName" value="${message(code: 'retroAction.label', default: 'RetroAction')}"/>
      <r:require modules="common, twitter-bootstrap, autocomplete"/>
   </head>

   <body>

      <div class="body">
         <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
         </g:if>

            <div class="list container">
                <div class="nav">
                     <h1 id="homeTitle">Retro Action Tracker</h1>

                     <span id="createAction"><g:link class="navLink lightbox" action="create">+ New Retro Action</g:link></span>
                     <span id="allActionList"><g:link class="navLink" action="list">All Actions</g:link></span>
                     <span id="normList"><g:link class="navLink" action="normList">List Norms</g:link></span>
                  </div>

                   <table id="retroActionList" class="iterationActionList center">
                     <tr>
                         <th class="action">Retro Action</th>
                         <th class="owners">Owners</th>
                         <th class="completed">Completed</th>
                      </tr>
                   </table>

                   <g:each in="${retroActionInstanceMap}" status="i" var="retroActionInstance">

                   <g:set var="listOfActions" value="${retroActionInstance.value}"/>
                   <g:set var="iterationDate" value="${listOfActions.get(0).retroActionDate}"/>
                   <div class="accordion-group">
                     <div class="accordion-heading">
                       <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse${i}">
                         Iteration: ${retroActionInstance.key}
                       </a>
                     </div>
                     <div style="height: 0px;" id="collapse${i}" class="accordion-body collapse">
                       <div class="accordion-inner">

                         <table class="retroActionListCells">
                            <g:each in="${listOfActions}" var="actionDetails">

                            <g:set var="normClass" value=""/>
                            <g:if test="${actionDetails.norm}">
                               <g:set var="normClass" value=" norm"/>
                            </g:if>

                             <tr>
                                <td class="action${normClass}  ${lastSelectedItem == actionDetails.id ? 'selected' : ''}">${actionDetails.retroAction}</td>
                                <td class="owners${normClass}"><g:getOwnersAvatars initials="${actionDetails.owners}"/></td>
                                <td class="completed${normClass}" data-id="${actionDetails.id}"><g:completeOrIncomplete completed="${actionDetails.completed}" norm="${actionDetails.norm}"/></td>
                                <td class="editLink last${normClass}"><g:link url="${[action:'edit', id:actionDetails.id]}" class="lightbox editLink">Edit</g:link></td>
                             </tr>
                         </g:each>
                         </table>
                       </div>
                     </div>
                   </div>
                  </g:each>
            </div>
      </div>
   </body>
</html>
