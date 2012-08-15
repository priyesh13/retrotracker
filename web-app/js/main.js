(function ($) {
    $(document).ready(function(){
        $('.selected').closest('.accordion-body').collapse('show');
        addIncompleteIconsToRows();

        addLightBoxToCreateOrEditLink();
    });

    $('.retroActionListCells td.completed').click(function(e){
        var element = $(e.target);
        var actionId = element.parent().data('id');
        var url = "/retrotracker/completeAction/"+actionId;
        $.post(url, function(data) {
            var className = (data == 'true')? 'complete' : 'incomplete';
            element.removeClass('incomplete').removeClass('complete').addClass(className);
            addIncompleteIconsToRows();

            if(data == 'true' && noMoreIncompletesInIteration(element)){
                removeIncompleteIconsFromRow(element);
            }
        });
    });

    function addLightBoxToCreateOrEditLink(){
        $('a.lightbox').click(function(event){
            var element = event.target;
            $(element).colorbox({href: element.href});
        });
    }

    function addIncompleteIconsToRows() {
        $.each($('.incomplete'), function(index, value) {
            var accordionGroup = $(value).closest('.accordion-group');
            var heading = accordionGroup.children('.accordion-heading').find('a');
            var incompleteWrapper =  heading.find('.incomplete-icon-wrapper');
            incompleteWrapper.remove();
            var wrapper = $('<div/>', {
                "class":"incomplete-icon-wrapper"
            }).appendTo(heading);

            $('<div/>', {
                "class":"incomplete-icon"
            }).appendTo(wrapper);
        });

        expandRowsWithIncompleteActions()
    }

    function noMoreIncompletesInIteration(element){
        var group = $(element).closest('.accordion-group');
        var incompletes = group.find('.incomplete');
        return (incompletes.length == 0);
    }

    function removeIncompleteIconsFromRow(element) {
        var heading = $(element).closest('.accordion-group').children('.accordion-heading').find('a');
        var incompleteWrapper =  heading.find('.incomplete-icon-wrapper');
        incompleteWrapper.remove();
    }

    function expandRowsWithIncompleteActions() {
        var incompleteRows = $('.incomplete-icon-wrapper').parents('.accordion-group').children('.accordion-body');
        incompleteRows.collapse('show');
    }
})(jQuery);





