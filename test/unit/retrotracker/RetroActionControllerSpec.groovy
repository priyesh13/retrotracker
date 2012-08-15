package retrotracker

import grails.plugin.spock.ControllerSpec

class RetroActionControllerSpec extends ControllerSpec {

   def setup() {
      controller.metaClass.message = {args -> "" }
   }

   def 'Retro actions with the same iteration number should be grouped together for the list action'() {
      given:
      RetroAction action = new RetroAction(iterationNumber: 10)
      RetroAction action2 = new RetroAction(iterationNumber: 10)
      RetroAction action3 = new RetroAction(iterationNumber: 10)
      RetroAction action4 = new RetroAction(iterationNumber: 11)
      RetroAction action5 = new RetroAction(iterationNumber: 12)
      RetroAction action6 = new RetroAction(iterationNumber: 12)

      mockDomain RetroAction, [action, action2, action3, action4, action5, action6]

      when:
      def result = controller.list()

      then:
      result.retroActionInstanceMap.size() == 3
      result.retroActionInstanceTotal == 6
      result.retroActionInstanceMap.get(10).size == 3
      result.retroActionInstanceMap.get(11).size == 1
      result.retroActionInstanceMap.get(12).size == 2
   }

   def 'When saving convert the users string entry for owners into a list'() {
//      given:
//      RetroAction action = new RetroAction()
//
//      mockDomain RetroAction, [action]
//
//      when:
//      controller.params.owners = "PM,JT,JC"
//      controller.save()
//
//      then:
//      action.owners == ['PM', 'JT', 'JC']
   }

   def 'Should be able to filter actions by norms'() {
   }

   def 'Should be able to filter actions by owners'() {
   }

}
