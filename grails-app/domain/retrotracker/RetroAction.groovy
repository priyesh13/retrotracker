package retrotracker

import org.joda.time.LocalDate

class RetroAction {

   int iterationNumber
   LocalDate retroActionDate
   String retroAction
   Boolean norm = false
   Boolean completed = false

   List owners = []
   static hasMany = [owners: String]

   String toString() {
      "$retroAction"
   }

   String joinOwners() {
      String ownerString = ""
      if(owners){
         ownerString = owners.collect {it}.join(',')
      }
      return ownerString
   }

}
