var teamMembers = [
    "DN",
    "GC",
    "GF",
    "GM",
    "JA",
    "JC",
    "JG",
    "JT",
    "JW",
    "PM",
    "RF",
    "RW",
    "SO",
    "SW",
    "YG",
    "ME",
    "TL",
    "KB"
];

function split(val) {
    return val.split(/,\s*/);
}

function extractLast(term) {
    return split(term).pop();
}


function addAutoComplete() {
    $("#owners")
        // don't navigate away from the field on tab when selecting an item
        .bind("keydown", function (event) {
            if (event.keyCode === $.ui.keyCode.TAB &&
                $(this).data("autocomplete").menu.active) {
                event.preventDefault();
            }
        })
        .autocomplete({
            minLength:0,
            source:function (request, response) {
                console.log('.autocomplete');
                // delegate back to autocomplete, but extract the last term
                response($.ui.autocomplete.filter(
                    teamMembers, extractLast(request.term)));
            },
            focus:function () {
                // prevent value inserted on focus
                return false;
            },
            select:function (event, ui) {
                var terms = split(this.value);
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push(ui.item.value);
                // add placeholder to get the comma-and-space at the end
                terms.push("");
                this.value = terms.join(",");
                return false;
            }
        });
}