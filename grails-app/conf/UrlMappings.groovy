class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"retroAction", action: "list")
      "500"(view:'/error')

      "/newRetro"(controller:"retroAction", action: "create")
      "/editRetro/$id"(controller: "retroAction", action: "edit")
      "/completeAction/$id"(controller: "retroAction", action: "completeAction")
      "/normList"(controller:"retroAction", action: "normList")


      "/json"(controller:"retroAction", action: "json")

	}
}
