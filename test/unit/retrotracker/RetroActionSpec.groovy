package retrotracker

import grails.plugin.spock.UnitSpec
import spock.lang.Shared
import spock.lang.Unroll

class RetroActionSpec extends UnitSpec {
   @Shared RetroAction action

   def setup() {
      action = new RetroAction()
   }

   def 'Retro Action description should print out on toString'() {
      given:
      action.retroAction = "This is a retro action description"

      expect:
      action.toString() == "This is a retro action description"
   }

   @Unroll('Should convert #owners to #ownerString')
   def 'Should convert the list of owners into a comma separated string'() {
      given:
      action.owners = owners

      expect:
      action.joinOwners() == ownerString

      where:
      owners                   | ownerString
      ['PM', 'RW', 'JG', 'DN'] | "PM,RW,JG,DN"
      []                       | ""
      null                     | ""

   }

}
