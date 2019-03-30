 



$(document).ready(function () {
	
	
	
	
  //your code here
    $( '#topheader .navbar-nav a' ).on( 'click', function () {
    $( '#topheader .navbar-nav' ).find( 'li.active' ).removeClass( 'active' );
	$( this ).parent( 'li' ).addClass( 'active' );
 });
    $('#traveller').mask('9');
    $( "#dep_date" ).datepicker({ minDate: 0 });
    $( "#dob" ).datepicker({ maxDate: +0 });
    
    $( function() {
        $( "#dep_date" ).datepicker({ minDate: 0 });
      } );
     
    $( "#dep_date" ).datepicker({ minDate: 0 });
    
    /*$("#dep_date").datepicker();
    $("#dob").datepicker();
    
    $("#return_date").hide();
	  $(".returnd").hide();

     if($('#one_way').is(':checked')){
	   $("#return_date").hide();
	  $(".returnd").hide();
	  
	
	  $('#dep_date').click(function(e) {
		  $("#dep_date").datepicker();
		});
	  
	
	};
    */
	  
	 /* $('#dep_date').blur(function(dateText) {
		  $( "#dep_date" ).datepicker({ minDate: new Date()}).val();
     });
	  */

  $('input[name="mobile"]').mask('9999999999');
  $('#cardNumber').mask('9999999999999999');
  $('#cvv').mask('999');
  $('#expirationMonth').mask('99');
  $('#expirationYear').mask('9999');
  if($( "#card_type option:selected" ).text()=='Aadhaar Card'){
	  $('input[name="card_number"]').val("");
	  $('input[name="card_number"]').mask('999999999999');
  }
  
  $('#card_type').on('change', function (e) {
	  if($( "#card_type option:selected" ).text()=='Aadhaar Card'){
		  $('input[name="card_number"]').val("");
		  $('input[name="card_number"]').mask('999999999999');
	  }
	  
	  if($( "#card_type option:selected" ).text()=='Pan Card'){
		  $('input[name="card_number"]').val("");
			$('input[name="card_number"]').mask('AAAAA9999A');
		}  
  });
  
  
  $("#round_trip").click(function() {
	  $(".returnd").show();
	  $("#return_date").show();
     $('#return_date').click(function(e) {
		  $("#return_date").datepicker("show");
		});
	  
  });
  
  $("#one_way").click(function() {
	  $("#return_date").hide();
	  $(".returnd").hide();
	  $('#dept_date').click(function(e) {
		  $("#dep_date").datepicker("show");
		  
	   });
  
  });

  
 $("#insert-more").click(function () {
     $("#mytable").each(function () {
         var tds = '<tr>';
         jQuery.each($('tr:last td', this), function () {
             tds += '<td>' + $(this).html() + '</td>';
         });
         tds += '</tr>';
         if ($('tbody', this).length > 0) {
             $('tbody', this).append(tds);
         } else {
             $(this).append(tds);
         }
     });
});	
	
	
 $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
  

  
 
 
 $("#show_password").click(function() {
		if($('#show_password').is(':checked')){
		                        $("#oldpassword,#newpassword").attr("type", "text");
		}else{
		       $("#oldpassword,#newpassword").attr("type", "password");
	    }
		 });
  
  

});




