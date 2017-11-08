$(function() {	
    $('#searchUser').click(getUser);   
});

function getUser() {
	
	$("#details").empty();
    var username = $("#username").val();    
    
    $.ajax("../user/get", {
		"type": "get",
		"data": {
        	"userName": username            
		}    
    }).done(displayUserInfo);
}

function displayUserInfo(data) {
	if(data.active === 000000){
		$("#error").text("Sorry " +data.name +"  is not existed in the system!!");
		alert("Sorry " +data.name +"  is not existed in the system!!");
	}
	else{
	$("#error").empty();
    var getUser = $('<fieldset>');
    
        getUser.append($('<legend>',{
        'attr' : {
        	class : 'text-info '
		},
        'html': 'This user role is ' +data.roles.role, 
        }));       
        
        getUser.append($('<div>', {
        'attr' : {
        	class : 'panel panel-default, panel-body'
        }
        }).append($('<tabel>', {
            'attr' : {
            	class : 'table table-striped, table table-condensed'
            }            
            }).append($('<tr>')
            		.append($('<th>',{
            			'html': 'UserId',
            			'css' : {
            				'padding':'0 25px 0 25px'
						}})).append($('<td>'))
            		.append($('<th>',{
            			'html': "UserName",
            			'css' : {
            				'padding':'0 25px 0 25px'
						}})).append($('<td>'))
            		.append( $('<th>',{
                		'html': "Role",
            			'css' : {
            				'padding':'0 25px 0 25px'
						}})))	
            	.append($('<tr>')
            			.append( $('<td>',{
                			'html': data.id,
                			'css' : {
                				'padding':'0 25px 0 25px'
    						}})).append($('<td>'))
                		.append($('<td>',{
                			'html': data.name,
                			'css' : {
                				'padding':'0 25px 0 25px'
    						}})).append($('<td>'))
                		.append( $('<td>',{
                    		'html':  data.roles.role ,
                			'css' : {
                				'padding':'0 25px 0 25px'
    						}})))));
       
        if (data.roles.role == 'FACULTY'){
        	getUser.append($('<a>', {
    			'attr' : {
    				class : 'form-inline,form-inline, glyphicon glyphicon-triangle-bottom'
    			},
    			'id' : 'lessDetails',
    			'html' : 'details',
    			'css' : {
    				'margin-left' : '10px',
    				'margin-bottom' : '10px'
    			},    			
    			'click' : function() {    				
    				$.ajax("../../user/getFaculty/", {
    					"type" : "Get",
    					'data' :{
    						'facultyId' : data.facultyId,
    						 'username' : data.name
    					}	
    				}).done(displayFacultyDetails);
    			}
    		}));
        	
        	getUser.append($('<a>', {
    			'attr' : {
    				class : 'pull-right, glyphicon glyphicon-edit'
    			},			
    			'html' : 'edit',
    			'css' : {
    				'margin-left' : '10px',
    				'margin-bottom' : '10px'
    			},
    			'href' : "../../user/editFaculty/" + data.facultyId +"/" +data.name 
        	}));
        } 
        else if (data.roles.role == 'STUDENT'){
        	getUser.append($('<a>', {
    			'attr' : {
    				class : 'form-inline,form-inline, glyphicon glyphicon-triangle-bottom'
    			},
    			'id' : 'lessDetails',
    			'html' : 'details',
    			'css' : {
    				'margin-left' : '10px',
    				'margin-bottom' : '10px'
    			},    			
    			'click' : function() {    				
    				$.ajax("../../user/getStudent/", {
    					"type" : "Get",
    					'data' :{
    						'studentId' : data.studentId,
    						 'username' : data.name
    					}	
    				}).done(displayStudentDetails);
    			}
    		}));
        	
        	getUser.append($('<a>', {
    			'attr' : {
    				class : 'pull-right, glyphicon glyphicon-edit'
    			},			
    			'html' : 'edit',
    			'css' : {
    				'margin-left' : '10px',
    				'margin-bottom' : '10px'
    			},
    			'href' : "../../user/editStudent/" + data.studentId +"/" +data.name 
        	}));
        }  
        
        
    $("#userInfo").html(getUser) ;  
	}
}

function displayFacultyDetails(data){
	 
	var details = $('<div>');
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'},
			'html' : "First Name: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'},
			'html' : data.firstName,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'},
			'html' : "Last Name: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'
		      },
			'html' : data.lastName,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'
		      },
			'html' : "Faculty Id: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'
		      },
			'html' : data.id,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	details.append($('<a>', {
		'attr' : {
			class : 'form-inline,form-inline, glyphicon glyphicon-triangle-top'
		},
		'html' : "close",
		'css' : {
			'margin-left' : '10px',
			'margin-bottom' : '10px'
		},    			
		'click' : function() {    				
			
	    	$("#details").empty();
			$("#userInfo").empty();
		}
	}));
	$("#details").html(details) ;
	
}
function displayStudentDetails(data){
	
	var details = $('<div>');
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'},
			'html' : "Student Id: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'},
			'html' : data.studentId,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'},
			'html' : "first Name: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'
		      },
			'html' : data.firstName,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'
		      },
			'html' : "Last Name: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'
		      },
			'html' : data.lastName,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'
		      },
			'html' : "Barcode: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'
		      },
			'html' : data.barcode,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'
		      },
			'html' : "Email Address: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'
		      },
			'html' : data.emailaddress,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline , pull-left'
		      },
			'html' : "Entry Date: " ,
			'css' : {			
				'margin-left' : '10px',
				'margin-bottom' : '10px',
				'font-weight': 'bold'
			}}));
	details.append($('<p>',{
		'attr' : {
			class : 'form-inline'
		      },
			'html' : data.entryDate,
			'css' : {			
				'margin-left' : '25px',
				'margin-bottom' : '10px'				
			}}));
	details.append($('<a>', {
		'attr' : {
			class : 'form-inline,form-inline, glyphicon glyphicon-triangle-top'
		},
		'html' : "close",
		'css' : {
			'margin-left' : '10px',
			'margin-bottom' : '10px'
		},    			
		'click' : function() {    				
			
	    	$("#details").empty();
			$("#userInfo").empty();
		}
	}));
	$("#details").html(details) ;
	
}