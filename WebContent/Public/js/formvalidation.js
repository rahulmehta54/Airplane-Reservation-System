

	function confSubmit(form) {
		if (confirm("Are you sure you want to submit the form?")) {
		form.submit();
		}
	};

$(document).ready(function () {


	$("#name,#nameOnCard").keydown(function(event) {
		console.log(event.keyCode); 
		// Allow only backspace and delete
		if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode==32 || event.keyCode==190) {
			// let it happen, don't do anythi
		}
		else {
			// Ensure that it is a number and stop the keypress

			if (event.keyCode < 65 || event.keyCode > 90) {
				event.preventDefault(); 
			}   
		}
	});


	$('#loginform').submit(function(e) {
		var c=0;
		$(".error").remove();
		var email = $('#emaillog').val();
		var  password = $('#passwordlog').val();
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if (email.length < 1) {
			$('#emaillog').after('<span class="error" style="color:#1bf12d;">This field is required</span>');
			e.preventDefault();

		}else if(reg.test(email) == false) {
			$('#emaillog').after('<span class="error" style="color:#1bf12d;">Please Enter Valid Email</span>');
			e.preventDefault();

		}

		if (password.length < 1) {
			$('#passwordlog').after('<span class="error" style="color:#1bf12d;">This field is required</span>');
			e.preventDefault();

		}
	});


	
	
	
	
	
	
	
	
	
	
	
	
	
	$('#searchform').submit(function(e) {

		$(".error").remove();
		var from = $('#from').val();
		var to= $('#to').val();
		var dur=$('#dep_date').val();
		var traveller=$('#traveller').val();        
		var c=0;
		if(to==from && to.length>1 && from.length>1){
			$('#to').after('<span class="error" width="100%">Source and Destination cannot be same</span>');
			$('#from').after('<span class="error" width="100%">Source and Destination cannot be same</span>');

			c++;
		}


		if(to.length<1){
			$('#to').after('<span class="error" width="100%">This field is required</span>');
			c++;
		}

		if(from.length<1){
			$('#from').after('<span class="error" width="100%">This field is required</span>');
			c++
		}

		if(dur.length<1){
			$('#dep_date').after('<span class="error" width="100%">This field is required</span>');
			c++;
		}
		if(traveller.length<1){
			$('#traveller').after('<span class="error" width="100%">This field is required</span>');
			c++;
		}
		
		if(traveller>10){
			$('#traveller').after('<span class="error" width="100%">Maximum 10 travellers are allowed</span>');
			c++;
		}
		

		if(c>0){
			e.preventDefault();
		}

	});




	$('#password').blur(function() {
		$(".error").remove();
		var password=$('#password').val();
		var pas=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
		if (password.length < 1) {
			$('#password').after('<span class="error">This field is required</span>');

		}else if(pas.test(password) == false) {
			$('#password').after('<span class="error">Please Enter Minimum eight characters, at least one letter, one number and one special character</span>');

		}
	});

	$('#password_confirmation').blur(function() {
		$(".error").remove();
		var confirm=$('#password_confirmation').val();
		var password=$('#password').val();
		if(confirm.length<1){
			$('#password_confirmation').after('<span class="error">This field is required</span>');

		}
		else if(password!=confirm){
			$('#password_confirmation').after('<span class="error">Password and Confirm Password do not match</span>');

		}
	});



	$('#name').blur(function() {
		$(".error").remove();
		var first_name = $('#name').val();
		if (first_name.length < 1) {
			$('#name').after('<span class="error">This field is required</span>');

		}else if(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/.test(first_name) == false) {
			$('#name').after('<span class="error">Please Enter only Characters</span>');

		}
	});

/*	$('#email').blur(function() {
		$(".error").remove();
		var email_address= $('#email').val();
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

		if (email_address.length < 1) {
			$('#email').after('<span class="error">This field is required</span>');

		}else if(reg.test(email_address)==false) {
			$('#email').after('<span class="error">Please Enter Valid Email</span>');

		}
	});


*/



	$('#mob_num').blur(function() {
		$(".error").remove();
		var mobile_number=$('#mob_num').val();
		if(mobile_number.length<1){
			$('#mob_num').after('<span class="error">This field is required</span>');
			c++;
		}else if(mobile_number.length>10){
			$('#mob_num').after('<span class="error">please enter valid mobile number</span>');
			c++;
		}

	});

	$('#address').blur(function() {
		$(".error").remove();
		var address=$('#address').val();
		if(address.length<1){
			$('#address').after('<span class="error">This field is required</span>');

		}

	});

	
	  $('#mob_num').mask('9999999999');
  
	  $('#mob_num').blur(function() {
		  $(".error").remove();

		  if($('#mob_num').val().length!=10){
				$('#mob_num').after('<span class="error">Please Enter valid mobile number</span>');
				c++;
		  }
		  
	  });
	
	$('#signup').submit(function(e) {

		var first_name = $('#name').val();
		var email_address= $('#email').val();
		var password=$('#password').val();
		var confirm=$('#password_confirmation').val();
		var mobile_number=$('#mob_num').val();
		var gender=$('#gender').val();
		var card_type=$('#card_type').val();
		var card_number=$('#card_number').val();
		var c=0;

		$(".error").remove();

		if (first_name.length < 1) {
			$('#name').after('<span class="error">This field is required</span>');
			c++;
		}else if(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/.test(first_name) == false) {
			$('#name').after('<span class="error">Please Enter only Characters</span>');
			c++;
		}
		var reg="\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b" ;
		// = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if (email_address.length < 1) {
			$('#email').after('<span class="error">This field is required</span>');
			c++;  
		}else if(reg.test(email) == false ) {
			$('#email').after('<span class="error">Please Enter Valid Email</span>');
			c++; 
		}


		var pas=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
		if (password.length < 1) {
			$('#password').after('<span class="error">This field is required</span>');
			c++;  
		}else if(pas.test(password) == false) {
			$('#password').after('<span class="error">Please Enter Minimum eight characters, at least one letter, one number and one special character</span>');
			c++; 
		}

		if(confirm.length<1){
			$('#password_confirmation').after('<span class="error">This field is required</span>');
			c++;	
		}
		else if(password!=confirm){
			$('#password_confirmation').after('<span class="error">Password and Confirm Password do not match</span>');
			c++;
		}


		if(mobile_number.length<1){
			$('#mob_num').after('<span class="error">This field is required</span>');
			c++;
		}else if(mobile_number.length>10){
			$('#mob_num').after('<span class="error">please enter valid mobile number</span>');
			c++;
		}



		if(c>0){
			e.preventDefault();
		}

	});


	$('#updateProfile').submit(function(e) {
		var first_name = $('#name').val();
		var email_address= $('#email').val();
		var mobile_number=$('#mob_num').val();
		var gender=$('#gender').val();

		var c=0;

		$(".error").remove();


		if (first_name.length < 1) {
			$('#name').after('<span class="error">This field is required</span>');
			c++;
		}else if(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/.test(first_name) == false) {
			$('#name').after('<span class="error">Please Enter only Characters</span>');
			c++;
		}

		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if (email_address.length < 1) {
			$('#email').after('<span class="error">This field is required</span>');
			c++;  
		}else if(reg.test(email_address) == false && email_address.length > 40) {
			$('#email').after('<span class="error">Please Enter Valid Email</span>');
			c++; 
		}





		if(mobile_number.length<1){
			$('#mob_num').after('<span class="error">This field is required</span>');
			c++;
		}else if(mobile_number.length>10){
			$('#mob_num').after('<span class="error">please enter valid mobile number</span>');
			c++;
		}


		if(c>0){
			e.preventDefault();
		}
	});


	$('#card_number').blur(function() {
		$(".error").remove();
		$.ajax({
			type: 'POST',
			url : 'ValidateUser',
			data : {
				card_number : $('#card_number').val()
			},
			success : function(responseText) {
				console.log(responseText);
				if(responseText==null){
					$('#card_number').after('<span class="error">Card number Already Exist</span>');	
				}

			}
		});
	});



	$('#email').blur(function() {
		
		$(".error").remove();
		var email_address= $('#email').val();
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

		if (email_address.length < 1) {
			$('#email').after('<span class="error">This field is required</span>');

		}else if(reg.test(email_address)==false) {
			$('#email').after('<span class="error">Please Enter Valid Email</span>');

		}else{
		//$(".error").remove();
		$.ajax({
			type: 'POST',
			url : 'ValidateUser',
			data : {
				email : $('#email').val()
			},
			success : function(responseText) {
				/*console.log(responseText+"  ghgh");*/
				if(responseText!='Served at: /Airline_Reservation_System'){
					$('#email').after('<span class="error">Email Already Exist</span>');	
				}

			}
		
		});
	}
	});


	if($('#errmessage').val()!=null){
		swal("Invalid Details");
	}


	$('#card_number').blur(function() {
		var card_type=$('#card_type').val();
		var card_number=$('#card_number').val();

		if(card_type=='Aadhaar Card'){
			if(card_number.length<1){

				$('#card_number').after('<span class="error">This field is required</span>');

			}else if(card_number.length<12){
				$('#card_number').after('<span class="error">Please Enter 12 digits</span>');

			}else{
				var adhar="[0-9]{12}";
				var cn=$('#card_number').val();
				if(cn.test(adhar) == false) {
					$('#card_number').after('<span class="error">Please Enter only digit</span>');
				}
			}
		}else{
			if(card_number.length<1){
				$('#card_number').after('<span class="error">This field is required</span>');
			}else if(card_number.length<10){
				$('#card_number').after('<span class="error">Please Enter 10 characters</span>');

			}
		}
	});

	$('#signOut').click(function(e) {
		e.preventDefault();  
		swal({
			title: "Are you sure you want to log out?",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
		.then((willDelete) => {
			if (willDelete) {
				swal("you are successfully logged out", {
					icon: "success",
				});

				setTimeout(function(){ $(window).attr('location','signOut'); }, 2000);


			} else {
				e.preventDefault();
			}
		});
	});




	$('#paymentform').submit(function(e) {


		var nameOnCard = $('#nameOnCard').val();
		var cardType= $('#cardType').val();
		var cardNumber=$('#cardNumber').val();
		var cvv=$('#cvv').val();

		var expirationMonth= $('#expirationMonth').val();
		var expirationYear=$('#expirationYear').val();
		var d = new Date();
		var n = d.getFullYear();
		var m= d.getMonth();	
		var c=0;

		$(".error").remove();
		if (nameOnCard.length < 1) {
			$('#nameOnCard').after('<span class="error">This field is required</span>');
			c++;
		}else if(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/.test(nameOnCard) == false) {
			$('#nameOnCard').after('<span class="error">Please Enter Valid Name</span>');
			c++;
		}

		var debitcard="[0-9]{16}";
		if(cardNumber<1){
			$('#cardNumber').after('<span class="error">This field is required</span>');
		}else if(cardNumber.length>16){
			$('#cardNumber').after('<span class="error">Please Enter Valid Card Number</span>');
			c++;
		}

		if(cvv.length<1){
			$('#cvv').after('<span class="error">This field is required</span>');
			c++;
		}else if(cvv.length>3){
			$('#cvv').after('<span class="error">Please Enter Valid CVV Number</span>');
			c++;
		}



		if(expirationMonth>12 || expirationMonth<1){
			$('#expirationMonth').after('<span class="error">Please Valid Month</span>');
			c++;
		}

		if(expirationYear<n){
			$('#expirationYear').after('<span class="error">Please Valid Year</span>');
			c++;
		}
		
		

		
		
		if(expirationYear==n){
			
			if(expirationMonth<m){
				$('#expirationMonth').after('<span class="error">Please Valid Month</span>');
				c++;
			}
       }
		
		
		if(c>0){
			e.preventDefault();
		}
	});


	$("#cardNumber").keydown(function(event) {
		// Allow only backspace and delete
		if ( event.keyCode == 46 || event.keyCode == 8 ) {
			// let it happen, don't do anything
		}
		else {
			// Ensure that it is a number and stop the keypress
			if (event.keyCode < 48 || event.keyCode > 57 ) {
				event.preventDefault(); 
			}   
		}
	});

	
	
	
	
	
	
	$('#CancelTicket').on('submit',function(e){
	    e.preventDefault();
	    var form = $(this);
	    swal({
	        title: "Are you sure?",
	        text: "You will not be able to recover this imaginary file!",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes, delete it!",
	        closeOnConfirm: false
	    }, function(isConfirm){
	        if (isConfirm) form.submit();
	    });
	});
		  
		

});
