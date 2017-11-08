
$('.attendance').on('click', function(e) {  
    /*var studentid = e.delegateTarget.tHead.rows[0].cells[this.cellIndex],*/
   // var studentid = e.delegateTarget.tHead.rows[0].cells[this.cellIndex],
	var dataDate = $(this).attr('data-date');
	  var dataStudent = $(this).attr('data-student');
    /*var studentid = this.parentNode.cells[0];*/
    var hdid=$(this).attr('data-id');
    var studentid=$(this).attr('id-student');
    var sessionid=$(this).attr('id-session');
    console.log(hdid);
  
    $('#'+hdid).toggleClass("hidden");
        
    document.getElementById(studentid).value = dataStudent;
    document.getElementById(sessionid).value = dataDate;
    console.log("Row : "+dataStudent+" , header : "+dataDate);
   
});