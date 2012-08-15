<div class="formFields">
   <label for="iterationNumber">Iteration Number</label>
   <g:textField name="iterationNumber" value="${interationNumber}"/>
</div>

<div class="formFields">
   <label for="retroActionDate">Retro Action Date</label>
   <joda:datePicker name="retroActionDate" value="${retroActionInstance?.retroActionDate}"/>
</div>

<div class="formFields">
   <label for="norm">Norm</label>
   <g:checkBox name="norm" value="${retroActionInstance?.norm}"/>
</div>

<div class="formFields">
   <label for="completed">Completed</label>
   <g:checkBox name="completed" value="${retroActionInstance?.completed}"/>
</div>

<div class="formFields">
   <label for="retroAction" class="hidden">Retro Action</label>
   <g:textArea placeholder="Enter your action here..." name="retroAction" rows="12" cols="70" value="${retroActionInstance?.retroAction?.encodeAsHTML()}" escapeHtml="false"/>
</div>

<div class="formFields">
   <label for="owners">Owners</label>
   <p class="ownersDesc">Enter the initials of the people responsible for this action in a comma seperated list. If it's a Team action leave the box blank</p>
   <g:textField name="owners" value="${retroActionInstance?.joinOwners()}"/>
</div>


