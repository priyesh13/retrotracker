import retrotracker.RetroAction
import org.joda.time.LocalDate
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication

class BootStrap {

   def init = { servletContext ->

      if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {

         def action1 = new RetroAction(retroActionDate: new LocalDate(),
                 retroAction: "CCC An example action from a retro that needs completing by the person in the owner column",
                 owners: ["PM", "JT"],
                 iterationNumber: 34)

         if (!action1.save()) {
            action1.errors.allErrors.each {error ->
               println "An error occured with action1: ${error}"
            }
         }

         def action2 = new RetroAction(retroActionDate: new LocalDate(),
                 retroAction: "BBB Some Retro Action 2. This is a really long retro. This is a really long retro. </br></br> This is a really long retro.This is a really long retro.This is a really long retro.",
                 completed: true,
                 owners: ["JC"],
                 iterationNumber: 35,
                 norm: true)


         if (!action2.save()) {
            action2.errors.allErrors.each {error ->
               println "An error occured with action2: ${error}"
            }
         }

         def action3 = new RetroAction(retroActionDate: new LocalDate(),
                 retroAction: "AAA Some Retro Action 3",
                 completed: true,
                 owners: ["RW"],
                 iterationNumber: 21)


         if (!action3.save()) {
            action2.errors.allErrors.each {error ->
               println "An error occured with action3: ${error}"
            }
         }

         def action4 = new RetroAction(retroActionDate: new LocalDate(),
                 retroAction: "DDD Some Retro Action 4",
                 completed: true,
                 owners: [""],
                 iterationNumber: 35)


         if (!action4.save()) {
            action4.errors.allErrors.each {error ->
               println "An error occured with action4: ${error}"
            }
         }

         def action5 = new RetroAction(retroActionDate: new LocalDate(),
                 retroAction: "EEE Some Retro Action 5",
                 completed: true,
                 owners: null,
                 iterationNumber: 33)


         if (!action5.save()) {
            action5.errors.allErrors.each {error ->
               println "An error occured with action5: ${error}"
            }
         }
      }
   }




   def destroy = {
   }
}
