package retrotracker

import grails.converters.JSON

class RetroActionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        Map<RetroAction> retroActions = [:]
        def actionList = RetroAction.list(order: "desc", sort: "iterationNumber")

        actionList.each { action ->
            def iterationNumber = action.iterationNumber

            if (!retroActions.containsKey(iterationNumber)) {
                def actions = RetroAction.findAllByIterationNumber(iterationNumber)
                retroActions.put(action.iterationNumber, actions)
            }
        }

        [retroActionInstanceMap: retroActions, retroActionInstanceTotal: RetroAction.count(), lastSelectedItem: session['lastSelectedItem']]
    }

    def normList = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        Map<RetroAction> retroActions = [:]
        def actionList = RetroAction.list(order: "desc", sort: "iterationNumber")

        actionList.each { action ->
            def iterationNumber = action.iterationNumber

            if (!retroActions.containsKey(iterationNumber)) {
                def actions = RetroAction.findAllByIterationNumberAndNorm(iterationNumber, true)
                if (actions) {
                    retroActions.put(action.iterationNumber, actions)
                }
            }
        }

        if (retroActions) {
            render(view: "list", model: [retroActionInstanceMap: retroActions])
        } else {
            render(view: "empty")
        }
    }

    def create = {
        def retroActionInstance = new RetroAction()
        retroActionInstance.properties = params
        render view: 'edit', model: [retroActionInstance: retroActionInstance, interationNumber: retroActionInstance.iterationNumber == 0? returnCurrentIterationNumber()+1 : retroActionInstance.iterationNumber ]
    }

    def edit = {
        def retroActionInstance = RetroAction.get(params.id)
        if (!retroActionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), params.id])}"
            redirect(action: "list")
        }
        else {
            session['lastSelectedItem'] = retroActionInstance.id
            return [retroActionInstance: retroActionInstance, interationNumber: retroActionInstance.iterationNumber]
        }
    }

    def save = {
        def ownersEntry = params.owners
        if (ownersEntry) {
            params.owners = params.owners.tokenize(',')
        }

        def retroActionInstance = new RetroAction(params)
        if (retroActionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), retroActionInstance.id])}"
            redirect(action: "list", id: retroActionInstance.id)
        }
        else {
            render(view: "edit", model: [retroActionInstance: retroActionInstance])
        }
    }

    def show = {
        def retroActionInstance = RetroAction.get(params.id)
        if (!retroActionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), params.id])}"
            redirect(action: "list")
        }
        else {
            [retroActionInstance: retroActionInstance]
        }
    }

    def completeAction = {
        def retroActionInstance = RetroAction.get(params.id)
        retroActionInstance.completed = !retroActionInstance.completed
        render text: retroActionInstance.completed
    }

    def update = {
        def retroActionInstance = RetroAction.get(params.id)
        if (retroActionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (retroActionInstance.version > version) {

                    retroActionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'retroAction.label', default: 'Retro Action')] as Object[], "Another user has updated this RetroAction while you were editing")
                    render(view: "edit", model: [retroActionInstance: retroActionInstance])
                    return
                }
            }
            params.owners = params.owners.tokenize(',')
            retroActionInstance.properties = params
            if (!retroActionInstance.hasErrors() && retroActionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), retroActionInstance.id])}"
                redirect(action: "list", id: retroActionInstance.id)
            }
            else {
                render(view: "edit", model: [retroActionInstance: retroActionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def retroActionInstance = RetroAction.get(params.id)
        if (retroActionInstance) {
            try {
                retroActionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'retroAction.label', default: 'Retro Action'), params.id])}"
            redirect(action: "list")
        }
    }

    def json = {
        List retroActionList = []

        RetroAction.list().each {retAction ->
            if (!retAction.completed) {
                def entry = [id: retAction.id, iterationNumber: retAction.iterationNumber, retroAction: retAction.retroAction, retroActionDate: retAction.retroActionDate, owners: retAction.owners, completed: retAction.completed]
                retroActionList.add(entry)
            }
        }

        if (!retroActionList) {
            def error = ['error': "Retro Action Feed not found."]
            render error as JSON
        } else {
            render retroActionList as JSON
        }
    }

    def normJsonFeed = {
        def retroActionList = []

        RetroAction.list().each {retAction ->
            if (retAction.norm) {
                retroActionList.add(retAction.retroAction)
            }
        }

        if (!retroActionList) {
            retroActionList = ['error': "Retro Norm Feed not found."]
        }
        if (params.callback) {
            render "${params.callback}(${retroActionList as JSON})"
        } else {
            render retroActionList as JSON
        }
    }

    def returnCurrentIterationNumber() {
        def action = RetroAction.list().max {it.iterationNumber}
        return action.iterationNumber
    }

}

