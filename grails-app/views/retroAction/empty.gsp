<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta name="layout" content="main"/>
      <title>Retro Action Tracker</title>
      <g:set var="entityName" value="${message(code: 'retroAction.label', default: 'RetroAction')}"/>
      <r:require modules="common"/>
   </head>

   <body>
      <div class="nav">
         <span id="createAction"><g:link class="navLink" action="create">+ New Retro Action</g:link></span>
         <span id="allActionList"><g:link class="navLink" action="list">All Actions</g:link></span>
         <span id="normList"><g:link class="navLink" action="normList">List Norms</g:link></span>
      </div>

      <div class="body">
         <h1 id="homeTitle">Retro Action Tracker</h1>

         <p>No actions to display</p>
      </div>
   </body>
</html>

