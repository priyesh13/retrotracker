modules = {

   colorbox{
       resource url: "css/colorbox.css"
       resource url: "js/jquery.colorbox.js"
   }

   common {
      dependsOn 'jquery-ui', 'colorbox'
      resource url: "css/main.css"
      resource url: "css/list.css"
      resource url: "css/form.css"
       resource url: "js/main.js"
      resource url: "js/textarea.js"
      resource url: "images/favicon.ico"
   }

   'twitter-bootstrap' {
      resource url: "css/bootstrap-button.css"
      resource url: "js/bootstrap-collapse.js"
   }

   autocomplete {
      resource url: "js/autocomplete.js"
      resource url: "css/autocomplete.css"
   }

}