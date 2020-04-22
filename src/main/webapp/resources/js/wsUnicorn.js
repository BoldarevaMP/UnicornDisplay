function reRenderDisplay(e) {
    $('.DISPLAY').find('tbody').empty();
    for (var i = 0; i < e.length; i++) {
        var eventObject = e[i];

        $('.DISPLAY').find('tbody').append(
            '<tr>' +
            '<td>' + eventObject.date + '</td>' +
            '<td>' + eventObject.name + '</td>' +
            '<td>' + eventObject.treatmentName + '</td>' +
            '<td>' + eventObject.dosage +' '+ eventObject.dosageForm + '</td>' +
            '<td>' + eventObject.status + '</td>' +
            '<td>' + eventObject.comment + '</td>'
            + '</tr>'
        )
    }
}
