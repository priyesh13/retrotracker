package retrotracker

class RetroActionTagLib {

    def getOwnersAvatars = {attrs ->
        def owners = attrs.initials

        if (!owners) {
            out << "<img src=\"${grailsApplication.config.grails.serverURL}/images/team.png\" height=\"70px\"/>"
        } else {
            owners.each { owner ->
                if (owner == "") {
                    out << "<img src=\"${grailsApplication.config.grails.serverURL}/images/team.png\" height=\"70px\"/>"
                } else {
                    out << "<img src=\"http://afternoon-winter-41.heroku.com/avatar/$owner\" height=\"70px\"/>"
                }
            }
        }
    }

    def completeOrIncomplete = {attrs ->
        def completed = attrs.completed
        def norm = attrs.norm

        if (completed && !norm) {
            out << "<p class=\"complete\">Yes</p>"
        } else if (norm) {
            out << "<div class='norm'></div>"
            out << "<p class=\"norm\">Norm</p>"
        } else {
            out << "<p class=\"incomplete\">No</p>"
        }
    }
}